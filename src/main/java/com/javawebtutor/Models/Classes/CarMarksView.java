package com.javawebtutor.Models.Classes;

import javafx.scene.control.Button;

public class CarMarksView {
    public String carMarkName;
    public Button button1;
    public Button button2;
    public Button button3;

    public CarMarksView(String carMarkName, Button button1, Button button2, Button button3) {
        this.carMarkName = carMarkName;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
    }

    public String getCarMarkName() {
        return carMarkName;
    }

    public void setCarMarkName(String carMarkName) {
        this.carMarkName = carMarkName;
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

    public Button getButton3() {
        return button3;
    }

    public void setButton3(Button button3) {
        this.button3 = button3;
    }
}
