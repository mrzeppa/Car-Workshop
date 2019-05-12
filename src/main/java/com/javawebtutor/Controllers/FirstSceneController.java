package com.javawebtutor.Controllers;

import javafx.event.ActionEvent;
import java.io.IOException;


public class FirstSceneController extends Controller{

    public void logIn(ActionEvent event) throws IOException {
        this.changeScene(event, "/LogInScene.fxml");
    }

    public void register(ActionEvent event) throws IOException {
        this.changeScene(event, "/RegisterScene.fxml");
    }
}
