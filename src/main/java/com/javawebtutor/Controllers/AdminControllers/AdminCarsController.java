package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Controllers.EmployeeControllers.EmployeeCheckUsersController;
import com.javawebtutor.Models.Cars;
import com.javawebtutor.Models.Classes.UserCars;
import com.javawebtutor.Models.Roles;
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

public class AdminCarsController extends Controller implements Initializable {

    SessionFactory factory = HibernateUtil.getSessionFactory();
    @FXML
    private TableView<UserCars> tv1;
    @FXML
    private TableColumn<UserCars, String> mark;
    @FXML
    private TableColumn<UserCars, String> model;
    private ObservableList<UserCars> personData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        List<Cars> cars = loadAllData(Cars.class, session);
        session = factory.getCurrentSession();
        session.getTransaction().begin();
        tv1.setItems(personData);
        for(Cars c : cars){
            UserCars uc = new UserCars(c.getCarModels().getCarMarks().getMarkName(), c.getCarModels().getModelName());
            mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
            model.setCellValueFactory(new PropertyValueFactory<>("model"));
            personData.add(uc);
        }
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/AdminMainScene.fxml");
    }
}
