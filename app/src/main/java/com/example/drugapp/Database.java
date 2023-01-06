package com.example.drugapp;

import androidx.appcompat.app.AppCompatActivity;

public class Database {
    //Atributes
    int medAmount, medFrequency;
    String medName;

    @Override
    public String toString() {
        return "MedData{" +
                "medAmount=" + medAmount +
                ", medFrequency=" + medFrequency +
                ", medName='" + medName + '\'' +
                '}';
    }

    // Konstruktoren
    public Database(){}
    public Database(int medAmount, int medFrequency, String medName) {
        this.medAmount = medAmount;
        this.medFrequency = medFrequency;
        this.medName = medName;
    }

    // setter und Getter

    public void setMedAmount(int medAmount) {
        this.medAmount = medAmount;
    }

    public void setMedFrequency(int medFrequency) {
        this.medFrequency = medFrequency;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getMedAmount() {
        return medAmount;
    }

    public int getMedFrequency() {
        return medFrequency;
    }

    public String getMedName() {
        return medName;
    }
}
