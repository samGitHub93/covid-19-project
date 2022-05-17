package com.example.mvvm_architecture_test.model;

import org.threeten.bp.LocalDate;

public class VaccinatedPerAge {

    private String ageRange;
    private int totalVaccinated;
    private int firstDose;
    private int secondDose;
    private int boosterDose;
    private int previousInfection;
    private LocalDate date;

    public VaccinatedPerAge(){}

    public VaccinatedPerAge(String ageRange, int totalVaccinated, int firstDose, int secondDose, int boosterDose, int previousInfection, LocalDate date) {
        this.ageRange = ageRange;
        this.totalVaccinated = totalVaccinated;
        this.firstDose = firstDose;
        this.secondDose = secondDose;
        this.boosterDose = boosterDose;
        this.previousInfection = previousInfection;
        this.date = date;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public int getTotalVaccinated() {
        return totalVaccinated;
    }

    public void setTotalVaccinated(int totalVaccinated) {
        this.totalVaccinated = totalVaccinated;
    }

    public int getFirstDose() {
        return firstDose;
    }

    public void setFirstDose(int firstDose) {
        this.firstDose = firstDose;
    }

    public int getSecondDose() {
        return secondDose;
    }

    public void setSecondDose(int secondDose) {
        this.secondDose = secondDose;
    }

    public int getBoosterDose() {
        return boosterDose;
    }

    public void setBoosterDose(int boosterDose) {
        this.boosterDose = boosterDose;
    }

    public int getPreviousInfection() {
        return previousInfection;
    }

    public void setPreviousInfection(int previousInfection) {
        this.previousInfection = previousInfection;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
