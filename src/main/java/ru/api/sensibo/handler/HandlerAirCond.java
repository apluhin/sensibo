package ru.api.sensibo.handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.api.sensibo.entity.AirConditioner;
import ru.api.sensibo.entity.State;
import ru.api.sensibo.enums.StateWorking;
import ru.api.sensibo.json.ConditionerWork;
import ru.api.sensibo.json.impl.ConditionerWorkImpl;

import java.util.List;
import java.util.stream.Collectors;

public class HandlerAirCond {

    private final ConditionerWork conditionerWork;

    public HandlerAirCond() {
        this.conditionerWork = new ConditionerWorkImpl();
    }

    public List<AirConditioner> getConditioner() {
        List<AirConditioner> collect = conditionerWork.getPods().stream().map(AirConditioner::new).collect(Collectors.toList());
        collect.parallelStream().forEach((s) -> s.parse(s));
        collect.parallelStream().forEach(this::putLastState);
        return collect;
    }

    public void changeState(AirConditioner airConditioner, State newState) {
        if (!airConditioner.getState().isOn()) on(airConditioner);
        setStateAndSend(airConditioner, newState);

    }

    private void setStateAndSend(AirConditioner airConditioner, State newState) {
        JsonElement jsonElement = new Gson().toJsonTree(newState);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("acState", jsonElement);

        JsonObject newObj = conditionerWork.changeState(jsonObject, airConditioner.getId());
        // TODO: 10.09.16 realize merge current state and new state
        airConditioner.mergeState(new Gson().fromJson(newObj.get("result"), State.class));
    }

    public void on(AirConditioner airConditioner) {
        State state = new State(StateWorking.On);
        setStateAndSend(airConditioner, state);
    }

    public void off(AirConditioner airConditioner) {
        State state = new State(StateWorking.Off);
        setStateAndSend(airConditioner, state);

    }

    public void putLastState(AirConditioner airConditioner) {
        JsonArray state = conditionerWork.getState(airConditioner.getId());
        JsonObject acState = state.get(0).getAsJsonObject().get("acState").getAsJsonObject();
        airConditioner.putCurrentValue(acState);

    }



}
