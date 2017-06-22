package br.com.stenioelson.ufcgsports.models;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

/**
 * Created by stenio on 6/21/2017.
 */
public class Credential {
    private static String token;
    private static String host;
    public static final String REMOTE_HOST = "http://arrumai.stenioelson.com.br:9000";
    public static final String LOCAL_HOST = "http://localhost:9000";
    public static final String AUTH_PATH = "/auth/local";
    public static final String RECURSOS_PATH = "/api/recursos";
    public static final String X_APPLICATION_ID = "5538a255bcec4a702a24bb59";
    public static final String X_API_KEY = "003d8ed40432044e7394131e09f8ad9fc57cd55d";
    public static final String LOGIN_JSON_CREDENTIALS = "{\"email\": \"admin@admin.com\", \"password\": \"secret\"}";

    private Credential() {}

    private class Token {
        public String token;
    }

    public static String getHost() {
        return host;
    }

    synchronized public static void setHost(String newHost) {
        host = newHost;
    }

    synchronized public static String getToken() {
        if (token == null) {
            Response res = RestClient.postSync(host, AUTH_PATH, LOGIN_JSON_CREDENTIALS);
            String resToken = res.readEntity(String.class);
            token = resToken.split("\"")[3];
        }

        return token;
    }

    public static MultivaluedHashMap getHeaders() {
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();

        map.putSingle("Authorization", "Bearer " + getToken());
        map.putSingle("X-Application-Id", X_APPLICATION_ID);
        map.putSingle("X-API-Key", X_API_KEY);

        return map;
    }
}
