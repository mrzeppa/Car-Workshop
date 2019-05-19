package com.javawebtutor.Controllers;

import com.javawebtutor.Models.Users;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class ActivatePasswordController extends Controller{
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();
    @FXML
    private PasswordField password;


    public void activatePassword(ActionEvent event) throws IOException {
        System.out.println(password.getText());
//        session.getTransaction().begin();
        Users u = session.get(Users.class, LogInController.loggedUserId);
        u.setPasswordActivated(1);
        u.setPassword(password.getText());
        System.out.println(u.toString());
        session.getTransaction().commit();

        session.close();
        logOut();
        changeScene(event, "/LogInScene.fxml");
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/LogInScene.fxml");

    }
}
