package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.CarMarks;
import com.javawebtutor.Models.CarModels;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminEditCarMarkController extends Controller implements Initializable {
    @FXML
    private TextField carMark;
    SessionFactory factory = HibernateUtil.getSessionFactory();
    CarMarks cm = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        cm = session.get(CarMarks.class, AdminCarMarksController.carMarkId);
        carMark.setText(cm.getMarkName());
        session.close();
    }

    public void editButton(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        cm.setMarkName(carMark.getText());
        session.update(cm);
        session.getTransaction().commit();
        session.close();

    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/AdminCarMarksScene.fxml");
    }
}
