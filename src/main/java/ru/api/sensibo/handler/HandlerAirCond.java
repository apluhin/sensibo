package ru.api.sensibo.handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ru.api.sensibo.entity.AirConditioner;
import ru.api.sensibo.json.ConditionerWork;
import ru.api.sensibo.json.impl.ConditionerWorkImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandlerAirCond {

    private final ConditionerWork conditionerWork;

    public HandlerAirCond() {
        this.conditionerWork = new ConditionerWorkImpl();
    }

    public List<AirConditioner> getConditioner() {
        List<AirConditioner> collect = conditionerWork.getPods().stream().map(AirConditioner::new).collect(Collectors.toList());
        collect.parallelStream().forEach((s) -> s.parse(s));
        return collect;
    }



}
