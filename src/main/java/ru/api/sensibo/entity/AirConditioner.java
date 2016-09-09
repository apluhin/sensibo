package ru.api.sensibo.entity;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;
import ru.api.sensibo.json.ConditionerWork;
import ru.api.sensibo.json.impl.ConditionerWorkImpl;

import java.util.Map;

public class AirConditioner {

    private String id;
    private Measurment measurment;

    private final ConditionerWork conditioner = new ConditionerWorkImpl();

    public AirConditioner(String id) {
        this.id = id;

    }

    public static AirConditioner getAirCond(String id) {
        return new AirConditioner(id);
    }

    public  void parse(AirConditioner airConditioner) {
        Object[] obj = new Gson().fromJson(conditioner.getMeasurment(airConditioner.getId()), Object[].class);
        Map<String, Object> map = (Map<String, Object>) obj[0];
        Double secondAgo = (Double) ((Map<String, Object>) (map.get("time"))).get("secondsAgo");
        Double temp = (Double) map.get("temperature");
        Double humidity = (Double) map.get("humidity");
        airConditioner.measurment = new Measurment(secondAgo, temp, humidity);
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
}
