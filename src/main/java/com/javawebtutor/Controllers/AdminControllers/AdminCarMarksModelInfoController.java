package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.CarMarks;
import com.javawebtutor.Models.CarModels;
import com.javawebtutor.Models.Classes.CarModelsView;
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

public class AdminCarMarksModelInfoController extends Controller implements Initializable {
    public static int carModelId;
    SessionFactory factory = HibernateUtil.getSessionFactory();
    @FXML
    private TableView<CarModelsView> tv1;
    @FXML
    private TableColumn<CarModelsView, String> mark;
    @FXML
    private TableColumn<CarModelsView, String> button1;
    @FXML
    private TableColumn<CarModelsView, String> button2;
    @FXML
    private TableColumn<CarModelsView, String> button3;
    private ObservableList<CarModelsView> personData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        CarMarks cm = session.get(CarMarks.class, AdminCarMarksController.carMarkId);
        List<CarModels> cmod = cm.getCarModels();
        tv1.setItems(personData);
        for(CarModels c : cmod){
            Button b1 = new Button("hehe");
            Button b2 = new Button("hehe");
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    carModelId = c.getCarModelId();
                    try {
                        changeScene(event, "/AdminEditCarModelScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            b2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    delete(c);
                    try {
                        changeScene(event, "/AdminCarMarksModelInfoScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            CarModelsView cmv = new CarModelsView(c.getModelName(), b1, b2);
            mark.setCellValueFactory(new PropertyValueFactory<>("carModelName"));
            button1.setCellValueFactory(new PropertyValueFactory<>("button1"));
            button2.setCellValueFactory(new PropertyValueFactory<>("button2"));
            button3.setCellValueFactory(new PropertyValueFactory<>("button3"));

            personData.add(cmv);
        }
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/AdminCarMarksScene.fxml");
    }

    public void delete(CarModels cm) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        session.delete(cm);
        session.getTransaction().commit();
        session.close();
    }
//    AdminCarMarksScene
}
