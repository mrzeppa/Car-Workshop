package com.javawebtutor.Models.Classes;

import javafx.scene.control.Button;

public class EmployeeCarCheck {
    private String model;
    private String mark;
    private String repairCauses;
    private String state;
    private int price;
    private Button button1;
    private Button button2;


    public EmployeeCarCheck(String model, String mark, String repairCauses, String state, int price, Button button1, Button button2) {
        this.model = model;
        this.mark = mark;
        this.repairCauses = repairCauses;
        this.state = state;
        this.price = price;
        this.button1 = button1;
        this.button2 = button2;
    }

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    public Button getButton2() {
        return button2;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }

    public Button getButton() {
        return button1;
    }

    public void setButton(Button button) {
        this.button1 = button;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getRepairCauses() {
        return repairCauses;
    }

    public void setRepairCauses(String repairCauses) {
        this.repairCauses = repairCauses;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
