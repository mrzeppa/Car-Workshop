package com.javawebtutor.Controllers;

import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FirstSceneController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void logIn(ActionEvent event) throws IOException {
        this.changeScene(event, "/LogInScene.fxml");
    }

    public void register(ActionEvent event) throws IOException {
        this.changeScene(event, "/RegisterScene.fxml");
    }
}
