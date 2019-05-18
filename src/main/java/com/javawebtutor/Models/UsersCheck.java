package com.javawebtutor.Models;

import javafx.scene.control.Button;

public class UsersCheck {
    public String name;
    public String surname;
    public Button button;

    public UsersCheck(String name, String surname, Button button) {
        this.name = name;
        this.surname = surname;
        this.button = button;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
