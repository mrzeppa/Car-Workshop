package com.javawebtutor.Controllers.ClientControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Controllers.LogInController;
import com.javawebtutor.Models.*;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientCheckCarRepairStateController extends Controller implements Initializable{
    @FXML private TableView<ClientCarCheck> tv1;
    @FXML private TableColumn<ClientCarCheck, String> tab1;
    @FXML private TableColumn<ClientCarCheck, String> tab2;
    @FXML private TableColumn<ClientCarCheck, String> tab3;
    @FXML private TableColumn<ClientCarCheck, String> tab4;
    @FXML private TableColumn<ClientCarCheck, String> tab5;
    SessionFactory factory = HibernateUtil.getSessionFactory();
    private ObservableList<ClientCarCheck> personData = FXCollections.observableArrayList();



    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users user;
        user = session.get(Users.class, LogInController.loggedUserId);
        tv1.setItems(personData);
        for (Cars car : user.getCars()) {
            Repairs r1;
            RepairsState rs1;
            r1 = session.get(Repairs.class, car.getCars());
            rs1 = session.get(RepairsState.class, r1.getRepairId());
            ClientCarCheck a = new ClientCarCheck(
                    car.getCarModels().getCarMarks().getMarkName(),
                    car.getCarModels().getModelName(),
                    r1.getRepairCauses(),
                    rs1.getStates().getName(),
                    r1.getPrice()
            );
//            System.out.println(car.getCarModels().getCarMarks().getMarkName() + " " + car.getCarModels().getModelName() + " " + r1.getRepairCauses() + " " +  rs1.getStates().getName() + " " + r1.getPrice());
            tab1.setCellValueFactory(new PropertyValueFactory<>("mark"));
            tab2.setCellValueFactory(new PropertyValueFactory<>("model"));
            tab3.setCellValueFactory(new PropertyValueFactory<>("repairCauses"));
            tab4.setCellValueFactory(new PropertyValueFactory<>("state"));
            tab5.setCellValueFactory(new PropertyValueFactory<>("price"));
            personData.add(a);
        }
        session.close();
    }


    public void backButton(ActionEvent event) throws IOException {
        this.changeScene(event, "/ClientMainScene.fxml");
    }
}
