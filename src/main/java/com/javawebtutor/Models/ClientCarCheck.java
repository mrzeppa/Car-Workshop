package com.javawebtutor.Models;

public class ClientCarCheck {
    private String model;
    private String mark;
    private String repairCauses;
    private String state;
    private int price;

    public ClientCarCheck(String model, String mark, String repairCauses, String state, int price) {
        this.model = model;
        this.mark = mark;
        this.repairCauses = repairCauses;
        this.state = state;
        this.price = price;
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
