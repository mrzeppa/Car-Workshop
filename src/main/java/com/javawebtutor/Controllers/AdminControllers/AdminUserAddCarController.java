package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Controllers.EmployeeControllers.EmployeeCheckUsersController;
import com.javawebtutor.Models.*;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUserAddCarController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    @FXML
    private ChoiceBox carModel;
    @FXML private ChoiceBox carMark;
    @FXML private TextField course;
    @FXML private TextArea repairCauses;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCarMarks();
    }

    public void loadCarMarks(){
        Session session = factory.getCurrentSession();
        List<CarMarks> cmlist = loadAllData(CarMarks.class, session);
        for(CarMarks cm : cmlist){
            carMark.getItems().add(cm.getMarkName());
        }
        carMark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                carModel.getItems().clear();
//                String carMarkName = carMark.getValue().toString();
                Session session = factory.getCurrentSession();
                session.getTransaction().begin();
                CriteriaBuilder cb1 = session.getCriteriaBuilder();
                CriteriaQuery<CarMarks> cr1 = cb1.createQuery(CarMarks.class);
                Root<CarMarks> root1 = cr1.from(CarMarks.class);
                cr1.select(root1).where(cb1.like(root1.get("markName"), carMark.getValue().toString()));
                Query<CarMarks> q1 = session.createQuery(cr1);
                List<CarMarks> results1 = q1.getResultList();
                int carMarkId = results1.get(0).getCarMarkId();
                CarMarks cm = session.get(CarMarks.class, carMarkId);
                List<CarModels> cmod = cm.getCarModels();
                for(CarModels cmodList : cmod){
                    carModel.getItems().add(cmodList.getModelName());
                }
                session.close();
            }
        });
    }

    public void addClientCar(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users user;
        user = session.get(Users.class, AdminUsersController.userId);
        Cars car = new Cars();
        car.setUsers(user);
        car.setCourse(course.getText());
        CriteriaBuilder cb1 = session.getCriteriaBuilder();
        CriteriaQuery<CarMarks> cr1 = cb1.createQuery(CarMarks.class);
        Root<CarMarks> root1 = cr1.from(CarMarks.class);
        cr1.select(root1).where(cb1.like(root1.get("markName"), carMark.getValue().toString()));
        Query<CarMarks> q1 = session.createQuery(cr1);
        List<CarMarks> results1 = q1.getResultList();

        CriteriaBuilder cb2 = session.getCriteriaBuilder();
        CriteriaQuery<CarModels> cr2 = cb2.createQuery(CarModels.class);
        Root<CarModels> root2 = cr2.from(CarModels.class);
        cr2.select(root2).where(cb2.like(root2.get("modelName"), carModel.getValue().toString()));
        Query<CarModels> q2 = session.createQuery(cr2);
        List<CarModels> results2 = q2.getResultList();


        CarMarks cm = results1.get(0);
        CarModels cmo = results2.get(0);
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
        this.changeScene(event, "/AdminUsersScene.fxml");
    }
}
