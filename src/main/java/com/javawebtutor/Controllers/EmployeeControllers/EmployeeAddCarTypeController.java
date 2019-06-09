package com.javawebtutor.Controllers.EmployeeControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.CarMarks;
import com.javawebtutor.Models.CarModels;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeAddCarTypeController extends Controller implements Initializable{
    SessionFactory factory = HibernateUtil.getSessionFactory();
    @FXML private TextField carMark;
    @FXML private TextField carModel;
    @FXML private ChoiceBox carMarkList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        List<CarMarks> cmlist = loadAllData(CarMarks.class, session);
        for(CarMarks cm : cmlist){
            carMarkList.getItems().add(cm.getMarkName());
        }

    }

    public void addCarMark(ActionEvent event) throws IOException {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CarMarks> cr = cb.createQuery(CarMarks.class);
        Root<CarMarks> root = cr.from(CarMarks.class);
        cr.select(root).where(cb.like(root.get("markName"), carMark.getText()));
        Query<CarMarks> q = session.createQuery(cr);
        List<CarMarks> results = q.getResultList();
        if(results.size() == 0) {
            CarMarks cm = new CarMarks(carMark.getText());
            session.save(cm);
            session.getTransaction().commit();
        }
        else{
            System.out.println("juz jest :/");
        }
        session.close();
        changeScene(event, "/EmployeeAddCarTypeScene.fxml");
    }

    public void addCar(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CarMarks> cr = cb.createQuery(CarMarks.class);
        Root<CarMarks> root = cr.from(CarMarks.class);
        cr.select(root).where(cb.like(root.get("markName"), carMarkList.getValue().toString()));
        Query<CarMarks> q = session.createQuery(cr);
        List<CarMarks> results = q.getResultList();
        CarMarks cm = session.get(CarMarks.class, results.get(0).getCarMarkId());
        CarModels cmo = new CarModels(carModel.getText(), cm);
        session.save(cmo);
        session.getTransaction().commit();
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/EmployeeMainScene.fxml");
    }
}
