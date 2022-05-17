package com.example.mvvm_architecture_test.model;

import org.threeten.bp.LocalDateTime;

public class PlagueDay {

    private LocalDateTime date;
    private int buffers;
    private int contagions;
    private int contagionsVariation;
    private int deaths;
    private int hospitalizedWithSymptoms;
    private int intensiveCare;
    private int totalHospitalized;
    private int newContagions;
    private int homeIsolation;
    private int healed;
    private int totalPositiveMolecularTest;
    private int totalPositiveRapidTest;
    private int molecularTestBuffers;
    private int rapidTestBuffers;

    public PlagueDay(LocalDateTime date, int buffers, int contagions, int contagionsVariation, int deaths, int hospitalizedWithSymptoms, int intensiveCare, int totalHospitalized, int newContagions, int homeIsolation, int healed, int totalPositiveMolecularTest, int totalPositiveRapidTest, int molecularTestBuffers, int rapidTestBuffers) {
        this.date = date;
        this.buffers = buffers;
        this.contagions = contagions;
        this.contagionsVariation = contagionsVariation;
        this.deaths = deaths;
        this.hospitalizedWithSymptoms = hospitalizedWithSymptoms;
        this.intensiveCare = intensiveCare;
        this.totalHospitalized = totalHospitalized;
        this.newContagions = newContagions;
        this.homeIsolation = homeIsolation;
        this.healed = healed;
        this.totalPositiveMolecularTest = totalPositiveMolecularTest;
        this.totalPositiveRapidTest = totalPositiveRapidTest;
        this.molecularTestBuffers = molecularTestBuffers;
        this.rapidTestBuffers = rapidTestBuffers;
    }

    public PlagueDay(PlagueDay dayOfPlague){
        this.date = dayOfPlague.getDate();
        this.buffers = dayOfPlague.getBuffers();
        this.contagions = dayOfPlague.getContagions();
        this.contagionsVariation = dayOfPlague.getContagionsVariation();
        this.deaths = dayOfPlague.getDeaths();
        this.hospitalizedWithSymptoms = dayOfPlague.getHospitalizedWithSymptoms();
        this.intensiveCare = dayOfPlague.getIntensiveCare();
        this.totalHospitalized = dayOfPlague.getTotalHospitalized();
        this.newContagions = dayOfPlague.getNewContagions();
        this.homeIsolation = dayOfPlague.getHomeIsolation();
        this.healed = dayOfPlague.getHealed();
        this.totalPositiveMolecularTest = dayOfPlague.getTotalPositiveMolecularTest();
        this.totalPositiveRapidTest = dayOfPlague.getTotalPositiveRapidTest();
        this.molecularTestBuffers = dayOfPlague.getMolecularTestBuffers();
        this.rapidTestBuffers = dayOfPlague.getRapidTestBuffers();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getBuffers() {
        return buffers;
    }

    public void setBuffers(int buffers) {
        this.buffers = buffers;
    }

    public int getContagions() {
        return contagions;
    }

    public void setContagions(int contagions) {
        this.contagions = contagions;
    }

    public int getContagionsVariation() {
        return contagionsVariation;
    }

    public void setContagionsVariation(int contagionsVariation) {
        this.contagionsVariation = contagionsVariation;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getHospitalizedWithSymptoms() {
        return hospitalizedWithSymptoms;
    }

    public void setHospitalizedWithSymptoms(int hospitalizedWithSymptoms) {
        this.hospitalizedWithSymptoms = hospitalizedWithSymptoms;
    }

    public int getIntensiveCare() {
        return intensiveCare;
    }

    public void setIntensiveCare(int intensiveCare) {
        this.intensiveCare = intensiveCare;
    }

    public int getTotalHospitalized() {
        return totalHospitalized;
    }

    public void setTotalHospitalized(int totalHospitalized) {
        this.totalHospitalized = totalHospitalized;
    }

    public int getNewContagions() {
        return newContagions;
    }

    public void setNewContagions(int newContagions) {
        this.newContagions = newContagions;
    }

    public int getHomeIsolation() {
        return homeIsolation;
    }

    public void setHomeIsolation(int homeIsolation) {
        this.homeIsolation = homeIsolation;
    }

    public int getHealed() {
        return healed;
    }

    public void setHealed(int healed) {
        this.healed = healed;
    }

    public int getTotalPositiveMolecularTest() {
        return totalPositiveMolecularTest;
    }

    public void setTotalPositiveMolecularTest(int totalPositiveMolecularTest) {
        this.totalPositiveMolecularTest = totalPositiveMolecularTest;
    }

    public int getTotalPositiveRapidTest() {
        return totalPositiveRapidTest;
    }

    public void setTotalPositiveRapidTest(int totalPositiveRapidTest) {
        this.totalPositiveRapidTest = totalPositiveRapidTest;
    }

    public int getMolecularTestBuffers() {
        return molecularTestBuffers;
    }

    public void setMolecularTestBuffers(int molecularTestBuffers) {
        this.molecularTestBuffers = molecularTestBuffers;
    }

    public int getRapidTestBuffers() {
        return rapidTestBuffers;
    }

    public void setRapidTestBuffers(int rapidTestBuffers) {
        this.rapidTestBuffers = rapidTestBuffers;
    }
}
