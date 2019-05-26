package com.javawebtutor.Models.Classes;

import javafx.scene.control.Button;

public class UsersCheck {
    public String name;
    public String surname;
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;

    public UsersCheck(String name, String surname, Button button1, Button button2, Button button3) {
        this.name = name;
        this.surname = surname;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
    }

    public UsersCheck(String name, String surname, Button button1, Button button2, Button button3, Button button4) {
        this.name = name;
        this.surname = surname;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
        this.button4 = button4;
    }

    public Button getButton4() {
        return button4;
    }

    public void setButton4(Button button4) {
        this.button4 = button4;
    }

    public Button getButton3() {
        return button3;
    }

    public void setButton3(Button button3) {
        this.button3 = button3;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
