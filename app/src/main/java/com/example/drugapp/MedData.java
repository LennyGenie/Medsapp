package com.example.drugapp;

import androidx.appcompat.app.AppCompatActivity;

public class MedData {
    private int id;
    private String name ;
    private int amount ;
    private int frequency ;

    public MedData() {
    }

    public MedData(int id, String name, int amount, int frequency) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.frequency = frequency;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", frequency=" + frequency +
                '}';
    }
}