package br.com.stenioelson.ufcgsports.models;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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
            .async()
            .get(new InvocationCallback<String>() {
                @Override
                public void completed(String resultado) {
                    callBack.setResultado("{success: true}");
                    callBack.result();
                }

                @Override
                public void failed(Throwable throwable) {
                    callBack.setResultado("{success: false}");
                    callBack.result();
                }
            });
    }
}
