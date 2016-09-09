package ru.api.sensibo;


import ru.api.sensibo.entity.AirConditioner;
import ru.api.sensibo.handler.HandlerAirCond;

import java.io.IOException;
import java.util.List;


public class Sensibo {

    public static void main(String[] args) throws IOException {

        HandlerAirCond handlerAirCond = new HandlerAirCond();
        List<AirConditioner> conditioner = handlerAirCond.getConditioner();
        System.out.println(handlerAirCond);


//       handlerAirCond.changeState(conditioner.get(0), new State(StateWorking.On, StateMode.Fan, FanLevel.Auto, 35));
//       handlerAirCond.changeState(conditioner.get(1), new State(StateWorking.On, null, null, null));

    }


}
