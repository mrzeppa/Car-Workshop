package com.javawebtutor.Controllers.EmployeeControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Models.UsersCheck;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeCheckUsersController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();
    public static int userId;
    @FXML
    private TableView<UsersCheck> tv1;
    @FXML
    private TableColumn<UsersCheck, String> name;
    @FXML
    private TableColumn<UsersCheck, String> surname;
    @FXML
    private TableColumn<UsersCheck, String> button;
    private ObservableList<UsersCheck> personData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkUsers();
    }

    public void checkUsers(){
        List<Users> users = loadAllData(Users.class, session);
        tv1.setItems(personData);
        for(Users u : users) {
            Button b1 = new Button("text");
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        userId = u.getUserId();
                        changeScene(event, "/EmployeeCheckUserCarsScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            UsersCheck uc = new UsersCheck(u.getName(), u.getSurname(), b1);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            button.setCellValueFactory(new PropertyValueFactory<>("button"));

            personData.add(uc);
        }
    }

}
