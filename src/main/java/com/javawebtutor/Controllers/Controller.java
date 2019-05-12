package com.javawebtutor.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(newScene);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
