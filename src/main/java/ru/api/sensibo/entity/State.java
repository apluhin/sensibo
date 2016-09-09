package ru.api.sensibo.entity;

import ru.api.sensibo.enums.FanLevel;
import ru.api.sensibo.enums.StateMode;
import ru.api.sensibo.enums.StateWorking;

public class State {

    private boolean on;
    private String mode;
    private String fanLevel;
    private Double targetTemperature;

    public State(StateWorking stateOn, StateMode mode, FanLevel fanLevel, double targetTemperature) {
        this.on = putOn(stateOn);
        this.mode = putMode(mode);
        this.fanLevel = putFanLevel(fanLevel);
        this.targetTemperature = putTemperature(targetTemperature);
    }

    public State(StateWorking stateWorking) {
        this.on = putOn(stateWorking);
    }

    private double putTemperature(double targetTemperature) {
        if (targetTemperature > 30) return 30;
        if (targetTemperature < 16) return 16;
        return targetTemperature;
    }

    private String putFanLevel(FanLevel fanLevel) {
        if (fanLevel == FanLevel.Auto) return "auto";
        if (fanLevel == FanLevel.High) return "high";
        if (fanLevel == FanLevel.Medium) return "medium";
        if (fanLevel == FanLevel.Low) return "low";
        return null;
    }

    private String putMode(StateMode mode) {
        if (mode == StateMode.Fan) return "fan";
        if (mode == StateMode.Heat) return "heat";
        if (mode == StateMode.Cool) return "cool";
        return null;
    }

    private boolean putOn(StateWorking stateOn) {
        return (stateOn == StateWorking.On);
    }

    public boolean isOn() {
        return on;
    }

    public String getMode() {
        return mode;
    }

    public String getFanLevel() {
        return fanLevel;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }
}
