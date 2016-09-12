package ru.api.sensibo.handler;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.api.sensibo.entity.AirConditioner;
import ru.api.sensibo.entity.State;
import ru.api.sensibo.enums.StateWorking;
import ru.api.sensibo.json.ConditionerWork;
import ru.api.sensibo.json.impl.ConditionerWorkJsonImpl;

import java.util.List;
import java.util.stream.Collectors;

public class HandlerAirCond {

    private final ConditionerWork conditionerWorkJson;

    public HandlerAirCond() {
        this.conditionerWorkJson = new ConditionerWorkJsonImpl();
    }

    public List<AirConditioner> getConditioner() {
        List<AirConditioner> collect = conditionerWorkJson.getPods().stream().map(AirConditioner::new).collect(Collectors.toList());
        collect.parallelStream().forEach(AirConditioner::updateMeasure);
        return collect;
    }

    public void changeState(AirConditioner airConditioner, State newState) {
        on(airConditioner);
        State state = new State(null, newState.getMode(), newState.getFanLevel(), newState.getTargetTemperature());
        setState(airConditioner, state);
    }

    private void setState(AirConditioner airConditioner, State newState) {
        JsonElement jsonElement = new Gson().toJsonTree(newState);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("acState", jsonElement);
        conditionerWorkJson.changeState(jsonObject, airConditioner.getId());

    }

    public void on(AirConditioner airConditioner) {
        State state = new State(StateWorking.On);
        setState(airConditioner, state);
    }

    public void off(AirConditioner airConditioner) {
        State state = new State(StateWorking.Off);
        setState(airConditioner, state);

    }





}

