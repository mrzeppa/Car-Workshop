package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.CarMarks;
import com.javawebtutor.Models.Classes.CarMarksView;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCarMarksController extends Controller implements Initializable {
    public static int carMarkId;
    SessionFactory factory = HibernateUtil.getSessionFactory();
    @FXML
    TextField carMarkTextField;
    @FXML
    private TableView<CarMarksView> tv1;
    @FXML
    private TableColumn<CarMarksView, String> mark;
    @FXML
    private TableColumn<CarMarksView, String> button1;
    @FXML
    private TableColumn<CarMarksView, String> button2;
    @FXML
    private TableColumn<CarMarksView, String> button3;
    private ObservableList<CarMarksView> personData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        List<CarMarks> cars = loadAllData(CarMarks.class, session);
        session = factory.getCurrentSession();
        session.getTransaction().begin();
        tv1.setItems(personData);
        for(CarMarks cm : cars){
            Button b1 = new Button("Modele");
            Button b2 = new Button("Usun");
            Button b3 = new Button("Edytuj");
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {

                        carMarkId = cm.getCarMarkId();
                        changeScene(event, "/AdminCarMarksModelInfoScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            b2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    carMarkId = cm.getCarMarkId();
                    delete(cm);
                    try {
                        changeScene(event, "/AdminCarMarksScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            b3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    carMarkId = cm.getCarMarkId();
                    try {
                        changeScene(event, "/AdminEditCarMarkScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            CarMarksView cmv = new CarMarksView(cm.getMarkName(), b1, b2, b3);
            mark.setCellValueFactory(new PropertyValueFactory<>("carMarkName"));
            button1.setCellValueFactory(new PropertyValueFactory<>("button1"));
            button2.setCellValueFactory(new PropertyValueFactory<>("button2"));
            button3.setCellValueFactory(new PropertyValueFactory<>("button3"));
            personData.add(cmv);
        }
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/AdminMainScene.fxml");
    }

    public void addCarMark(ActionEvent event) throws IOException {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        CarMarks cm = new CarMarks(carMarkTextField.getText());
        session.save(cm);
        session.getTransaction().commit();
        session.close();
        changeScene(event, "/AdminCarMarksScene.fxml");

    }

    public void delete(CarMarks cm) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        session.delete(cm);
        session.getTransaction().commit();
        session.close();
    }
}
