package ru.api.sensibo.entity;

public class Measurment {

    private double secondAgo;
    private double currentTemperature;
    private double humindity;

    public Measurment(double secondAgo, double currentTemperature, double humindity) {
        this.currentTemperature = currentTemperature;
        this.secondAgo = secondAgo;
        this.humindity = humindity;
    }

    @Override
    public String toString() {
        return "Measurment{" +
                "secondAgo=" + secondAgo +
                ", currentTemperature=" + currentTemperature +
                ", humindity=" + humindity +
                '}';
    }

    public double getSecondAgo() {
        return secondAgo;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getHumindity() {
        return humindity;
    }
}
