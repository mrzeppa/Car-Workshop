package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.Cars;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Models.Classes.UsersCheck;
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

public class AdminUsersController extends Controller implements Initializable {
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
    private TableColumn<UsersCheck, String> button1;
    @FXML
    private TableColumn<UsersCheck, String> button2;
    @FXML
    private TableColumn<UsersCheck, String> button3;
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
            Button b2 = new Button("car");
            Button b3 = new Button("invoice");
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    delete(u);
                    try {
                        changeScene(event, "/AdminUsersScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            b2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        userId = u.getUserId();
                        changeScene(event, "/AdminEditUserScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            b3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        userId = u.getUserId();
                        changeScene(event, "/EmployeeAddInvoiceScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            UsersCheck uc = new UsersCheck(u.getName(), u.getSurname(), b1, b2, b3);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            button1.setCellValueFactory(new PropertyValueFactory<>("button1"));
            button2.setCellValueFactory(new PropertyValueFactory<>("button2"));
            button3.setCellValueFactory(new PropertyValueFactory<>("button3"));

            personData.add(uc);
            if(u.getCars().size() > 0)
                tv1.setStyle("-fx-selection-bar: red;");
        }
    }

    public void addUser(ActionEvent event) throws IOException {
        changeScene(event, "/AdminAddUserScene.fxml");
    }

    public void delete(Users user) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/AdminMainScene.fxml");
    }

}
