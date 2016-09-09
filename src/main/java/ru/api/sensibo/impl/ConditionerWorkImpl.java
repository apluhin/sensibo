package ru.api.sensibo.impl;

import com.google.gson.JsonArray;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.api.sensibo.ConditionerWork;
import ru.api.sensibo.query.RequestServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConditionerWorkImpl implements ConditionerWork {

    RequestServer query = new RequestServer();

    public List<String> getPods() {
        List<String> list = new ArrayList<>();
        try {
            JsonArray pods = query.getPods();
            for (int i = 0; i < pods.size(); i++) {
                list.add(pods.get(i).getAsJsonObject().get("id").getAsString());
            }

        } catch (IOException e) {
            throw new RuntimeException("Error during get Pod");
        }
        return list;
    }

    public JsonObject getPod(String id) {
        try {
            return query.getPod(id);
        } catch (IOException e) {
            throw new RuntimeException("Error during get current pod", e);
        }

    }

    public List<JsonArray> getState(String id) {
        List<JsonArray> jsonArr = new ArrayList<>();
        try {
            JsonArray pods = query.getState(id);
            System.out.println(pods);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonElement getMeasurment(String id) {


        try {
            return query.getMeasurements(id);

        } catch (IOException e) {
            throw new RuntimeException("Error during get measure from pod " + id, e);
        }

    }

    public JsonObject changeState(JsonObject newState, String id) {
        try {
            return query.changeState(newState, id);
        } catch (IOException e) {
            throw new RuntimeException("Error during get change state pod ", e);
        }
    }
}
