package com.javawebtutor.Controllers.ClientControllers;


import com.javawebtutor.Controllers.Controller;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ClientMainSceneController extends Controller {

    public void editAction(ActionEvent event) throws IOException {
        this.changeScene(event, "/ClientEditProfile.fxml");
    }

    public void addCar(ActionEvent event) throws  IOException{
        this.changeScene(event, "/ClientAddCarScene.fxml");
    }

    public void checkCarState(ActionEvent event) throws  IOException{
        this.changeScene(event, "/ClientCheckCarRepairStateScene.fxml");
    }

    public void logOutButton(ActionEvent event) throws IOException {
        this.logOut();
        this.changeScene(event, "/FirstScene.fxml");
    }


}
