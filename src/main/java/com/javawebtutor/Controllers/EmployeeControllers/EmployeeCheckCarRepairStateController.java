package com.javawebtutor.Controllers.EmployeeControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.*;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeCheckCarRepairStateController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();
    @FXML
    private TableView<EmployeeCarCheck> tv1;
    @FXML private TableColumn<ClientCarCheck, String> tab1;
    @FXML private TableColumn<ClientCarCheck, String> tab2;
    @FXML private TableColumn<ClientCarCheck, String> tab3;
    @FXML private TableColumn<ClientCarCheck, String> tab4;
    @FXML private TableColumn<ClientCarCheck, String> tab5;
    private ObservableList<EmployeeCarCheck> personData = FXCollections.observableArrayList();


    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(this.loadAllData(Cars.class, session).size());
        List<Cars> carList = this.loadAllData(Cars.class, session);
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        tv1.setItems(personData);
        for(Cars car : carList){
            Repairs r1;
            RepairsState rs1;
            r1 = session.get(Repairs.class, car.getCars());
            rs1 = session.get(RepairsState.class, r1.getRepairId());
            EmployeeCarCheck a = new EmployeeCarCheck(
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
}
