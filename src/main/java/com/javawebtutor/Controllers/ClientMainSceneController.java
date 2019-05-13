package com.javawebtutor.Controllers;


import javafx.event.ActionEvent;

import java.io.IOException;

public class ClientMainSceneController extends Controller {

    public void editAction(ActionEvent event) throws IOException {
        this.changeScene(event, "/ClientEditProfile.fxml");
    }

    public void addCar(ActionEvent event) throws  IOException{
        this.changeScene(event, "/ClientAddCarScene.fxml");
    }
}
