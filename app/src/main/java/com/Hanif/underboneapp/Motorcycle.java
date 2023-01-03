package com.Hanif.underboneapp;

public class Motorcycle {
    //private String id;
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
    private String search;
   // private int likes;
    private boolean isExpanded;

    public Motorcycle(){
        //empty
    }

    public Motorcycle( String name, String brand, String engineType, String engineCooling, int displacment, double fuelTankCapacity, String startingSystem, String frameType, double maxPower, String transmission,String imageUrl) {
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
        //this.search = search;
        //this.likes = likes;
        //this.id = id;

    }

//    public String getSearch() {
//        return search;
//    }

//    public void setSearch(String search) {
//        this.search = search;
//    }
    //    public int getLikes() {
//        return likes;
//    }
//
//    public void setLikes(int likes) {
//        this.likes = likes;
//    }
    //    public  String getId() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id = id;
//    }

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

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
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

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

 //   @Override
//    public String toString() {
//        return "Motorcycle{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", brand='" + brand + '\'' +
//                ", EngineType='" + EngineType + '\'' +
//                ", EngineCooling='" + EngineCooling + '\'' +
//                ", displacment=" + displacment +
//                ", fuelTankCapacity=" + fuelTankCapacity +
//                ", startingSystem='" + startingSystem + '\'' +
//                ", frameType='" + frameType + '\'' +
//                ", maxPower=" + maxPower +
//                ", transmission='" + transmission + '\'' +
//                ", imageUrl='" + imageUrl + '\'' +
//                ", isExpanded=" + isExpanded +
//                '}';
//    }
}
