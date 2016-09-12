package ru.api.sensibo.script.condition;

import ru.api.sensibo.entity.AirConditioner;
import ru.api.sensibo.entity.Measurment;
import ru.api.sensibo.handler.HandlerAirCond;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Condition {

    private final HandlerAirCond handlerAirCond;
    private final AirConditioner airConditioner;


    private List<Predicate<Measurment>> forOn = new ArrayList<>();
    private List<Predicate<Measurment>> forOff = new ArrayList<>();

    public Condition(HandlerAirCond handlerAirCond, AirConditioner airConditioner) {
        this.handlerAirCond = handlerAirCond;
        this.airConditioner = airConditioner;
    }


    public Condition putPredicateOn(Predicate<Measurment> p) {
        forOn.add(p);
        return this;
    }

    public Condition putPredicateOff(Predicate<Measurment> p) {
        forOff.add(p);
        return this;
    }

    public boolean on() {
        return check(forOn);
    }


    public boolean off() {
        return check(forOff);
    }

    private boolean check(List<Predicate<Measurment>> list) {
        Measurment measurment = airConditioner.updateMeasure();
        for (Predicate predicate : list) {
            if (!predicate.test(measurment)) {
                return false;
            }
        }
        return true;
    }

    public HandlerAirCond getHandlerAirCond() {
        return handlerAirCond;
    }

    public AirConditioner getAirConditioner() {
        return airConditioner;
    }
}
