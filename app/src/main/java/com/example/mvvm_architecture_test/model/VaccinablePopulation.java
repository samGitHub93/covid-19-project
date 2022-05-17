package com.example.mvvm_architecture_test.model;

public class VaccinablePopulation {

    private String area;
    private String region;
    private String ageRange;
    private int numberOfPerson;

    public VaccinablePopulation(){}

    public VaccinablePopulation(String area, String region, String ageRange, int numberOfPerson) {
        this.area = area;
        this.region = region;
        this.ageRange = ageRange;
        this.numberOfPerson = numberOfPerson;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public int getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }
}
