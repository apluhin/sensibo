package ru.api.sensibo.json.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.api.sensibo.json.ConditionerWork;
import ru.api.sensibo.json.query.RequestServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConditionerWorkJsonImpl implements ConditionerWork {

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

    public JsonArray getState(String id) {

        try {
            JsonArray pods = query.getState(id);
            return pods;
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
