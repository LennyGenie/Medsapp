package com.example.drugapp;

import androidx.appcompat.app.AppCompatActivity;

public class MedData {
     private String name ;
     private int amount , frequency ;

    public MedData() {
    }

    public MedData(String name, int amount, int frequency) {
        this.name = name;
        this.amount = amount;
        this.frequency = frequency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "MedData{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", frequency=" + frequency +
                '}';
    }
}
