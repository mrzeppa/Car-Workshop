package com.javawebtutor.Models.Classes;

import javafx.scene.control.CheckBox;

public class InvoiceCarTable {
    public String mark;
    public String model;
    public CheckBox cb;

    public InvoiceCarTable(String mark, String model, CheckBox cb) {
        this.mark = mark;
        this.model = model;
        this.cb = cb;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CheckBox getCb() {
        return cb;
    }

    public void setCb(CheckBox cb) {
        this.cb = cb;
    }
}
