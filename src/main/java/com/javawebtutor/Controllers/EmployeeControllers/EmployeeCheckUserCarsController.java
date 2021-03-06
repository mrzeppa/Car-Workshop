package com.javawebtutor.Controllers.EmployeeControllers;


import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.Cars;
import com.javawebtutor.Models.Classes.UserCars;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeCheckUserCarsController extends Controller implements Initializable {
//
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();
    @FXML
    private TableView<UserCars> tv1;
    @FXML
    private TableColumn<UserCars, String> mark;
    @FXML
    private TableColumn<UserCars, String> model;
    private ObservableList<UserCars> personData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session.getTransaction().begin();
        Users u = session.get(Users.class, EmployeeCheckUsersController.userId);
        List<Cars> cars = u.getCars();
        tv1.setItems(personData);
        for(Cars c : cars){
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Users> cr = cb.createQuery(Users.class);
//            Root<Users> root = cr.from(Users.class);
//            cr.select(root).where(cb.like(root.get("name"), "Marcin"));
//            Query<Users> q = session.createQuery(cr);
//            List<Users> results = q.getResultList();
//            System.out.println(results.get(0).getLogin());
            UserCars uc = new UserCars(c.getCarModels().getCarMarks().getMarkName(), c.getCarModels().getModelName());
            mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
            model.setCellValueFactory(new PropertyValueFactory<>("model"));
            personData.add(uc);
        }
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/EmployeeCheckUsersScene.fxml");
    }
}
