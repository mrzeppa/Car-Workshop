package com.javawebtutor.Controllers;

import com.javawebtutor.Models.*;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientAddCarController extends Controller {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    @FXML private TextField carModel;
    @FXML private TextField carMark;
    @FXML private TextField course;
    @FXML private TextArea repairCauses;

    public void clientAddCar(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users user;
        user = session.get(Users.class, LogInController.loggedUserId);
        Cars car = new Cars();
        car.setUsers(user);
        car.setCourse(course.getText());
        CarMarks cm = new CarMarks(carMark.getText());
        CarModels cmo = new CarModels(carMark.getText(), cm);
        Repairs repairs = new Repairs(repairCauses.getText(), car);
        car.setCarModels(cmo);
        session.save(cm);
        session.save(cmo);
        session.save(car);
        session.save(repairs);
        session.getTransaction().commit();


    }
}
