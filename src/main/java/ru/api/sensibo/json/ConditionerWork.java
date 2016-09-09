package ru.api.sensibo.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public interface ConditionerWork {

    List<String> getPods();

    JsonObject getPod(String id);

    JsonArray getState(String id);

    JsonElement getMeasurment(String id);

    JsonObject changeState(JsonObject newState, String id);



}
