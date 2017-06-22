package br.com.stenioelson.ufcgsports.models;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;

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

    public static void get(String hostWithProtocol, String path, CallBack callBack) {
        WebTarget target = ClientBuilder.newClient().target(hostWithProtocol);

        target
                .path(path)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .headers(Credential.getHeaders())
                .async()
                .get(new InvocationCallback<String>() {
                    @Override
                    public void completed(String resultado) {
                        JsonObject jo = convertToJson(resultado);
                        callBack.setResultado("{success: true, result: " + jo.toString() + "}");
                        callBack.result();
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        callBack.setResultado("{success: false, result: " + throwable.getMessage() + "}");
                        callBack.result();
                    }
                });
    }

    public static void post(String hostWithProtocol, String path, String input, CallBack callBack) {
        WebTarget target = ClientBuilder.newClient().target(hostWithProtocol);

        target
                .path(path)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .headers(Credential.getHeaders())
                .async()
                .post(Entity.json(input), new InvocationCallback<String>() {
                    @Override
                    public void completed(String resultado) {
                        JsonObject jo = convertToJson(resultado);
                        callBack.setResultado("{success: true, result: " + jo.toString() + "}");
                        callBack.result();
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
