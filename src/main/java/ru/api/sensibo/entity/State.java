package ru.api.sensibo.entity;

import ru.api.sensibo.enums.FanLevel;
import ru.api.sensibo.enums.StateMode;
import ru.api.sensibo.enums.StateWorking;

public class State {

    private Boolean on;
    private String mode;
    private String fanLevel;
    private Integer targetTemperature;

    public State(StateWorking stateOn, StateMode mode, FanLevel fanLevel, int targetTemperature) {
        this.on = putOn(stateOn);
        this.mode = putMode(mode);
        this.fanLevel = putFanLevel(fanLevel);
        this.targetTemperature = putTemperature(targetTemperature);
    }

    public State(Boolean on, String mode, String fanLevel, Integer targetTemperature) {
        this.on = on;
        this.mode = mode;
        this.fanLevel = fanLevel;
        this.targetTemperature = targetTemperature;
    }

    public State(StateWorking stateWorking) {
        this.on = putOn(stateWorking);
    }

    private int putTemperature(int targetTemperature) {
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
        if (stateOn == null) return false;
        return stateOn == StateWorking.On;

    }

    public boolean isOn() {
        if (on == null) return false;
        return on;
    }

    public String getMode() {
        return mode;
    }

    public String getFanLevel() {
        return fanLevel;
    }

    public int getTargetTemperature() {
        return targetTemperature;
    }

    public State mergeState(State state) {
        State newState = new State(
                this.on == null ? state.on : this.on,
                this.mode == null ? state.mode : this.mode,
                this.fanLevel == null ? state.fanLevel : this.fanLevel,
                this.targetTemperature == null ? state.targetTemperature : this.targetTemperature
        );
        return newState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (on != null ? !on.equals(state.on) : state.on != null) return false;
        if (mode != null ? !mode.equals(state.mode) : state.mode != null) return false;
        if (fanLevel != null ? !fanLevel.equals(state.fanLevel) : state.fanLevel != null) return false;
        return targetTemperature != null ? targetTemperature.equals(state.targetTemperature) : state.targetTemperature == null;

    }

    @Override
    public int hashCode() {
        int result = on != null ? on.hashCode() : 0;
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        result = 31 * result + (fanLevel != null ? fanLevel.hashCode() : 0);
        result = 31 * result + (targetTemperature != null ? targetTemperature.hashCode() : 0);
        return result;
    }
}
