/**********************************************
 Workshop 6
 Course: JAC 444 - Summer 2022
 Last Name: Himanshu
 First Name: Himanshu
 ID: 146109202
 Section: ZBB
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature  Himanshu
 Date: 07/17/2022
 **********************************************/

package com.example.fx_project;

import javafx.beans.property.SimpleStringProperty;

public class Data {
    private final SimpleStringProperty bmi;
    private final SimpleStringProperty weightStatus;

    public Data(String bmi, String weightStatus) {
        this.bmi =  new SimpleStringProperty(bmi);
        this.weightStatus = new SimpleStringProperty(weightStatus);
    }

    public String getBmi() {
        return bmi.get();
    }

    public SimpleStringProperty bmiProperty() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi.set(bmi);
    }

    public String getWeightStatus() {
        return weightStatus.get();
    }

    public SimpleStringProperty weightStatusProperty() {
        return weightStatus;
    }

    public void setWeightStatus(String weightStatus) {
        this.weightStatus.set(weightStatus);
    }
}
