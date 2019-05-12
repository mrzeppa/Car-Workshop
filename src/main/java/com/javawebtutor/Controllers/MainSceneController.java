package com.javawebtutor.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MainSceneController extends Controller {
    @FXML private Label l11;
    @FXML private Button b11;

    public void a(){
        l11.setText(Integer.toString(LogInController.loggedUserId));
    }

}
