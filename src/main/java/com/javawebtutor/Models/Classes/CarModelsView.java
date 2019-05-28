package com.javawebtutor.Models.Classes;

import javafx.scene.control.Button;

public class CarModelsView {
    public String carModelName;
    public Button button1;
    public Button button2;

    public CarModelsView(String carModelName, Button button1, Button button2) {
        this.carModelName = carModelName;
        this.button1 = button1;
        this.button2 = button2;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
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
}
