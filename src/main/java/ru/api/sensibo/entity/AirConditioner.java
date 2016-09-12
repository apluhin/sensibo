package ru.api.sensibo.entity;

import com.google.gson.Gson;
import ru.api.sensibo.json.ConditionerWork;
import ru.api.sensibo.json.impl.ConditionerWorkJsonImpl;

import java.util.Map;

public class AirConditioner {

    private final ConditionerWork conditioner = new ConditionerWorkJsonImpl();
    private String id;
    private Measurment measurment;


    public AirConditioner(String id) {
        this.id = id;

    }


    public static AirConditioner getAirCond(String id) {
        return new AirConditioner(id);
    }

    public Measurment updateMeasure() {
        Object[] obj = new Gson().fromJson(conditioner.getMeasurment(this.getId()), Object[].class);
        Map<String, Object> map = (Map<String, Object>) obj[0];
        Double secondAgo = (Double) ((Map<String, Object>) (map.get("time"))).get("secondsAgo");
        Double temp = (Double) map.get("temperature");
        Double humidity = (Double) map.get("humidity");
        this.measurment = new Measurment(secondAgo, temp, humidity);
        return measurment;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AirConditioner{" +
                "id='" + id + '\'' +
                ", measurment=" + measurment +

                '}';
    }


    public Measurment getMeasurment() {
        return measurment;
    }
}
