package ru.api.sensibo;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.api.sensibo.impl.ConditionerWorkImpl;

import java.io.*;
import java.util.List;


public class Sensibo {



    public static void main(String[] args) throws IOException {


        ConditionerWork conditionerWork = new ConditionerWorkImpl();
        List<String> s  = conditionerWork.getPods();

        String requesttest = "{'on': false}";
        JsonObject jsonElements = new JsonParser().parse(requesttest).getAsJsonObject();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("acState", jsonElements);
        System.out.println(jsonObject);
        new Thread(() -> {System.out.println(conditionerWork.changeState(jsonObject, s.get(0)));}).start();
        new Thread(() -> {System.out.println(conditionerWork.changeState(jsonObject, s.get(1)));}).start();




    }



}
