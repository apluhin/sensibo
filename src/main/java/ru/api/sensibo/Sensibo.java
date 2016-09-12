package ru.api.sensibo;


import ru.api.sensibo.entity.AirConditioner;
import ru.api.sensibo.handler.HandlerAirCond;
import ru.api.sensibo.script.condition.Condition;

import java.io.IOException;
import java.util.List;


public class Sensibo {

    public static void main(String[] args) throws IOException, InterruptedException {

        HandlerAirCond handlerAirCond = new HandlerAirCond();
        List<AirConditioner> conditioner = handlerAirCond.getConditioner();
        AirConditioner airConditioner = conditioner.get(0);

        Condition condition = new Condition(handlerAirCond, airConditioner);

        condition.putPredicateOn(measurment -> measurment.getCurrentTemperature() > 20)
                .putPredicateOn(measurment -> measurment.getCurrentTemperature() < 22);
        condition.putPredicateOff(measurment -> measurment.getCurrentTemperature() > 25);





    }


}
