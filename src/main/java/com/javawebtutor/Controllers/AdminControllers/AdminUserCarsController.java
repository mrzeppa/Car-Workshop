package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Controllers.EmployeeControllers.EmployeeCheckUsersController;
import com.javawebtutor.Models.Cars;
import com.javawebtutor.Models.Classes.EmployeeCarCheck;
import com.javawebtutor.Models.Classes.UserCars;
import com.javawebtutor.Models.Repairs;
import com.javawebtutor.Models.RepairsState;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Utilities.HibernateUtil;
import com.sun.org.apache.xml.internal.security.Init;
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

public class AdminUserCarsController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();
    @FXML
    private TableView<EmployeeCarCheck> tv1;
    @FXML
    private TableColumn<EmployeeCarCheck, String> tab1;
    @FXML
    private TableColumn<EmployeeCarCheck, String> tab2;
    @FXML
    private TableColumn<EmployeeCarCheck, String> tab3;
    @FXML
    private TableColumn<EmployeeCarCheck, String> tab4;
    @FXML
    private TableColumn<EmployeeCarCheck, String> tab5;
    @FXML
    private TableColumn<EmployeeCarCheck, String> tab6;
    @FXML
    private TableColumn<EmployeeCarCheck, String> tab7;
    public static int carId;
    private ObservableList<EmployeeCarCheck> personData = FXCollections.observableArrayList();


    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users u = session.get(Users.class, AdminUsersController.userId);
        List<Cars> carList = u.getCars();
        System.out.println(carList.size());
        tv1.setItems(personData);
        for (Cars car : carList) {
            Repairs r1;
            RepairsState rs1;
            Button b1 = new Button("Usun");
            Button b2 = new Button("Edytuj");
            r1 = session.get(Repairs.class, car.getCars());
            rs1 = session.get(RepairsState.class, r1.getRepairId());
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    delete(car);
                    session.close();
                    try {

                        changeScene(event, "/AdminUserCarsScene.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            b2.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    session.close();
                    try {
                        edit(car, event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            EmployeeCarCheck a = new EmployeeCarCheck(
                    car.getCarModels().getCarMarks().getMarkName(),
                    car.getCarModels().getModelName(),
                    r1.getRepairCauses(),
                    rs1.getStates().getName(),
                    r1.getPrice(),
                    b1,
                    b2
            );
            tab1.setCellValueFactory(new PropertyValueFactory<>("mark"));
            tab2.setCellValueFactory(new PropertyValueFactory<>("model"));
            tab3.setCellValueFactory(new PropertyValueFactory<>("repairCauses"));
            tab4.setCellValueFactory(new PropertyValueFactory<>("state"));
            tab5.setCellValueFactory(new PropertyValueFactory<>("price"));
            tab6.setCellValueFactory(new PropertyValueFactory<>("button1"));
            tab7.setCellValueFactory(new PropertyValueFactory<>("button2"));
            personData.add(a);
        }
        session.close();

    }

    public void delete(Cars car) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        session.delete(car);
        session.getTransaction().commit();
        session.close();
    }

    public void edit(Cars car, ActionEvent event) throws IOException {
        carId = car.getCars();
        this.changeScene(event, "/AdminUserCarsRepairsEditScene.fxml");

    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/AdminUsersScene.fxml");
    }
}
