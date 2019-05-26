package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminMainController extends Controller {
    public void users(ActionEvent event) throws IOException {
        this.changeScene(event, "/AdminUsersScene.fxml");
    }
}
