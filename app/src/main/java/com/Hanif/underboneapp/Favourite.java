package com.Hanif.underboneapp;

public class Favourite {
    private String name;
    private String brand;
    private String EngineType;
    private String EngineCooling;
    private int displacment;
    private double fuelTankCapacity;
    private String startingSystem;
    private String frameType;
    private double maxPower;
    private String transmission;
    private String imageUrl;


    public Favourite(){
        //empty
    }

    public Favourite(String name, String brand, String engineType, String engineCooling, int displacment, double fuelTankCapacity, String startingSystem, String frameType, double maxPower, String transmission, String imageUrl) {
        this.name = name;
        this.brand = brand;
        EngineType = engineType;
        EngineCooling = engineCooling;
        this.displacment = displacment;
        this.fuelTankCapacity = fuelTankCapacity;
        this.startingSystem = startingSystem;
        this.frameType = frameType;
        this.maxPower = maxPower;
        this.transmission = transmission;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEngineType() {
        return EngineType;
    }

    public void setEngineType(String engineType) {
        EngineType = engineType;
    }

    public String getEngineCooling() {
        return EngineCooling;
    }

    public void setEngineCooling(String engineCooling) {
        EngineCooling = engineCooling;
    }

    public int getDisplacment() {
        return displacment;
    }

    public void setDisplacment(int displacment) {
        this.displacment = displacment;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public String getStartingSystem() {
        return startingSystem;
    }

    public void setStartingSystem(String startingSystem) {
        this.startingSystem = startingSystem;
    }

    public double getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }
}
