package ru.api.sensibo;



import ru.api.sensibo.handler.HandlerAirCond;

import java.io.*;


public class Sensibo {



    public static void main(String[] args) throws IOException {



        HandlerAirCond handlerAirCond = new HandlerAirCond();
        System.out.println(handlerAirCond.getConditioner());

//        String requesttest = "{'on': false}";
//        JsonObject jsonElements = new JsonParser().parse(requesttest).getAsJsonObject();
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.add("acState", jsonElements);
//        System.out.println(jsonObject);
//        new Thread(() -> {System.out.println(conditionerWork.getMeasurment(s.get(0)));}).start();
//        new Thread(() -> {System.out.println(conditionerWork.changeState(jsonObject, s.get(1)));}).start();





    }



}
