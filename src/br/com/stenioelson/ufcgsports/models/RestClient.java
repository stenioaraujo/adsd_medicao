package br.com.stenioelson.ufcgsports.models;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.json.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.Date;

/**
 * Created by stenio on 11/06/17.
 */
public class RestClient {

    public static JsonObject convertToJson(String string) {
        if (string.startsWith("[")) {
            string = string.substring(1, string.length()-1);
        }
        JsonReader jsonReader = Json.createReader(new StringReader(string));
        return jsonReader.readObject();
    }

    private static void handleCompleted(String resultado, String path, String method, long startTime, String categoryRequests, CallBack callBack) {
        JsonObject jo = convertToJson(resultado);
        JsonObjectBuilder joNeeded = Json.createObjectBuilder();

        joNeeded.add("path", path);
        joNeeded.add("method", method);
        joNeeded.add("categoryRequests", categoryRequests);
        joNeeded.add("requestStart", startTime);
        joNeeded.add("requestEnd", new Date().getTime());
        joNeeded.add("dbTime", jo.getJsonArray("dbTime"));

        callBack.setResultado("{\"success\": \"true\", \"result\": " + joNeeded.build().toString() + "}");
        callBack.result();
    }

    public static void get(String hostWithProtocol, String path, String categoryRequests, CallBack callBack) {
        ClientConfig configuration = new ClientConfig();
        configuration.property(ClientProperties.CONNECT_TIMEOUT, 2000);
        configuration.property(ClientProperties.READ_TIMEOUT, 2000);
        WebTarget target = ClientBuilder.newClient().target(hostWithProtocol);

        target
                .path(path)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .headers(Credential.getHeaders())
                .async()
                .get(new InvocationCallback<String>() {
                    private long startTime = new Date().getTime();

                    @Override
                    public void completed(String resultado) {
                        handleCompleted(resultado, path, "GET", startTime, categoryRequests, callBack);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        callBack.setResultado("{\"success\": \"false\", \"result\": " + throwable.getMessage() + "}");
                        callBack.result();
                    }
                });
    }

    public static void post(String hostWithProtocol, String path, String input, String categoryRequests, CallBack callBack) {
        ClientConfig configuration = new ClientConfig();
        configuration.property(ClientProperties.CONNECT_TIMEOUT, 2000);
        configuration.property(ClientProperties.READ_TIMEOUT, 2000);
        WebTarget target = ClientBuilder.newClient().target(hostWithProtocol);

        long startTime = new Date().getTime();

        target
                .path(path)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .headers(Credential.getHeaders())
                .async()
                .post(Entity.json(input), new InvocationCallback<String>() {
                    @Override
                    public void completed(String resultado) {
                        handleCompleted(resultado, path, "POST", startTime, categoryRequests, callBack);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        callBack.setResultado("{success: false, result: " + throwable.getMessage() + "}");
                        callBack.result();
                    }
                });
    }

    public static Response postSync(String hostWithProtocol, String path, String input) {
        WebTarget target = ClientBuilder.newClient().target(hostWithProtocol);

        Response res = target
                .path(path)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .buildPost(Entity.json(input))
                .invoke();

        return res;
    }
}
