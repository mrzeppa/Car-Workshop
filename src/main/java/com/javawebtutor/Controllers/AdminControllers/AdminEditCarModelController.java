package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
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

public class AdminEditCarModelController extends Controller implements Initializable {
    @FXML
    private TextField carModel;
    SessionFactory factory = HibernateUtil.getSessionFactory();
    CarModels cm = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        cm = session.get(CarModels.class, AdminCarMarksModelInfoController.carModelId);
        carModel.setText(cm.getModelName());
        session.close();
    }

    public void editButton(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        cm.setModelName(carModel.getText());
        session.update(cm);
        session.getTransaction().commit();
        session.close();

    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/AdminCarMarksModelInfoScene.fxml");
    }
}
