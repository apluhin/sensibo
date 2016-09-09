package ru.api.sensibo.json.query;


import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class RequestServer {

    private static String SERVER = "https://home.sensibo.com/api/v2/";
    private  static final String KEY;

    static {
        String resourceName = "key.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        KEY = props.getProperty("key");
    }


    public JsonArray getPods() throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) buildQuery("/users/me/pods");
        JsonObject jsonObject = getJsonObjectFromString(urlConnection);

        return (JsonArray) jsonObject.get("result");
    }

    public JsonObject changeState(JsonObject newState, String podUid) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) buildQuery("/pods/" + podUid + "/acStates");
        return postJsonObject(httpURLConnection, newState);

    }

    public JsonObject getPod(String id) throws IOException {
        HttpURLConnection httpCon = (HttpURLConnection) buildQuery("/pods/" + id);
        JsonObject jsonObject = getJsonObjectFromString(httpCon);

        return (JsonObject) jsonObject.get("result");

    }

    public JsonArray getState(String id) throws IOException {
        HttpURLConnection httpCon = (HttpURLConnection) buildQuery("/pods/" + id + "/acStates");
        JsonObject jsonObject = getJsonObjectFromString(httpCon);

        return (JsonArray) jsonObject.get("result");
    }

    public JsonElement getMeasurements(String id) throws IOException {
        HttpURLConnection httpCon = (HttpURLConnection) buildQuery("/pods/" + id + "/measurements");
        JsonObject jsonObject = getJsonObjectFromString(httpCon);

        return jsonObject.get("result");
    }

    private String getResponse(HttpURLConnection urlConnection) throws IOException {
        InputStream is = urlConnection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }

    private URLConnection buildQuery(String param) {
        StringBuilder builder = new StringBuilder(SERVER + param);
        builder.append(delimeter());
        builder.append(addParam("apiKey", KEY));
        try {
            return new URL(builder.toString()).openConnection();
        } catch (IOException e) {
            throw new RuntimeException("Error open connection", e);
        }
    }

    private String delimeter() {
        return "?";
    }

    private String addParam(String id, String value) {
        return id + "=" + value;
    }

    private JsonObject getJsonObjectFromString(HttpURLConnection httpCon) throws IOException {
        httpCon.setRequestMethod("GET");

        String response = getResponse(httpCon);
        JsonObject jsonObject;
        jsonObject = getJsonObjectFromString(response);
        return jsonObject;
    }

    private JsonObject postJsonObject(HttpURLConnection httpURLConnection, JsonObject newState) throws IOException {
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();

        outputStream.write(newState.toString().getBytes());
        outputStream.flush();

        String response = getResponse(httpURLConnection);
        outputStream.close();
        return getJsonObjectFromString(response);
    }


    private JsonObject getJsonObjectFromString(String response) {
        JsonElement jelement = new JsonParser().parse(response);
        return jelement.getAsJsonObject();
    }
}
