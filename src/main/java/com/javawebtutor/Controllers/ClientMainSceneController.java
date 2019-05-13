package com.javawebtutor.Controllers;


import javafx.event.ActionEvent;

import java.io.IOException;

public class ClientMainSceneController extends Controller {

    public void buttonAction(ActionEvent event) throws IOException {
        editProfile();
        this.changeScene(event, "/ClientEditProfile.fxml");
    }

    public void editProfile(){

    }
}
