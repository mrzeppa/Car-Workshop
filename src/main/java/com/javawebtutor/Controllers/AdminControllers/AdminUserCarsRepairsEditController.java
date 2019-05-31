package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Controllers.EmployeeControllers.EmployeeCheckCarRepairStateController;
import com.javawebtutor.Models.Cars;
import com.javawebtutor.Models.Repairs;
import com.javawebtutor.Models.RepairsState;
import com.javawebtutor.Models.States;
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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUserCarsRepairsEditController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();
    @FXML private ChoiceBox repairState;
    @FXML private TextField carModel;
    @FXML private TextField carMark;
    @FXML private TextField course;
    @FXML private TextArea repairCauses;
    public void buttonAction(){
        edit();
    }

    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    public void loadData(){
        List<States> s = Controller.loadAllData(States.class, session);
        System.out.println(s.size());
        session = factory.getCurrentSession();
        session.getTransaction().begin();
        System.out.println(session.isConnected());
        Cars car = session.get(Cars.class, AdminUserCarsController.carId);
        Repairs r1 = session.get(Repairs.class, car.getCars());
        RepairsState rs1 = session.get(RepairsState.class, r1.getRepairId());

        carModel.setText(car.getCarModels().getModelName());
        carMark.setText(car.getCarModels().getCarMarks().getMarkName());
        course.setText(car.getCourse());
        repairCauses.setText(rs1.getRepairId().getRepairCauses());

        for(States states : s){
            repairState.getItems().add(states.getName());
        }
        repairState.setValue(rs1.getStates().getName());
        repairState.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

            }
        });
//        test.setText(car.getCarModels().getModelName());
        session.close();
    }

    public void edit(){
        session = factory.getCurrentSession();
        session.getTransaction().begin();
        Cars car = session.get(Cars.class, AdminUserCarsController.carId);
        Repairs r1 = session.get(Repairs.class, car.getCars());
        RepairsState rs1 = session.get(RepairsState.class, r1.getRepairId());
        States s1 = session.get(States.class, 1);
        States s2 = session.get(States.class, 2);
        States s3 = session.get(States.class, 3);

        car.setCourse(course.getText());
        car.getCarModels().setModelName(carModel.getText());
        car.getCarModels().getCarMarks().setMarkName(carMark.getText());
        r1.setRepairCauses(repairCauses.getText());
        if(repairState.getValue().equals("Nierozpoczete"))
            rs1.setStates(s1);
        if(repairState.getValue().equals("W trakcie"))
            rs1.setStates(s2);
        if(repairState.getValue().equals("Zakonczone"))
            rs1.setStates(s3);
        session.getTransaction().commit();
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        this.changeScene(event, "/AdminUserCarsScene.fxml");
    }
}
