package com.kmsoft.firebase_contact;

public class DataModal {
    String name;
    String number;

    public DataModal(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public DataModal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "DataModal{" +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
