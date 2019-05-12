package com.javawebtutor.Controllers;

import com.javawebtutor.Models.Address;
import com.javawebtutor.Models.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;


public class LogInController extends Controller {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    public static int loggedUserId;
    @FXML private TextField login;
    @FXML private PasswordField password;
    @FXML private Button logInButton;

    public void logIn(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        if(checkLoggingIn(login.getText(), password.getText()) == 1) {
            System.out.println("zalogowano");
            logInButton.setOnAction(e ->{
                try {
                    changeScene(e, "/MainScene.fxml");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }

        else{
            System.out.println("zle dane logowania");
            session.getTransaction().rollback();
        }
        session.close();

//
    }

    public int checkLoggingIn(String login, String password){
        List<Users> list = factory.getCurrentSession()
                .createQuery("from " + Users.class.getName() + " WHERE login = '" + login + "' AND password = '" + password + "'").list();
        if(list.size() > 0)
            loggedUserId = list.get(0).getUserId();
        return list.size();
    }

}
