package br.com.stenioelson.ufcgsports.models;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by stenio on 11/06/17.
 */
public class RestClient {

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
                        callBack.setResultado("{success: true, result: " + resultado + "}");
                        callBack.result();
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        callBack.setResultado("{success: false}");
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
                        callBack.setResultado("{success: true, result: " + resultado + "}");
                        callBack.result();
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        callBack.setResultado("{success: false}");
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
