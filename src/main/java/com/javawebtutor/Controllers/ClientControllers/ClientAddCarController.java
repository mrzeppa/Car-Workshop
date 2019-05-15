package com.javawebtutor.Controllers.ClientControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Controllers.LogInController;
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
import java.util.Date;
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
        CarModels cmo = new CarModels(carModel.getText(), cm);
        Repairs repairs = new Repairs(repairCauses.getText(), car);
        States st;
        st = session.get(States.class, 1);
        RepairsState rs1 = new RepairsState(repairs, new Date(), new Date(), st);
        car.setCarModels(cmo);
        session.save(cm);
        session.save(cmo);
        session.save(car);
        session.save(repairs);
        session.save(rs1);
        session.getTransaction().commit();
        session.close();

    }

    public void backButton(ActionEvent event) throws IOException {
        this.changeScene(event, "/ClientMainScene.fxml");
    }
}
