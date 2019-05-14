package com.javawebtutor.Controllers;

import com.javawebtutor.Models.Cars;
import com.javawebtutor.Models.Repairs;
import com.javawebtutor.Models.RepairsState;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FirstSceneController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();

    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users user;
        user = session.get(Users.class, 1);
        String t = "LP. \t|\tMarka\t|\tModel\t|\tPowod naprawy\t|\tStan naprawy\t|\tCena\t|";
        for (Cars car : user.getCars()) {
            int i = 0;
            Repairs r1;
            RepairsState rs1;
            r1 = session.get(Repairs.class, car.getCars());
            rs1 = session.get(RepairsState.class, r1.getRepairId());
////            t = t + "\n" + i + "\t|\t" + user.getCar().get(i).getCarModels().getCarMarks().getMarkName() + "\t|\t" +
////                    user.getCar().get(i).getCarModels().getModelName() + "\t|\t" + user.getCar().get(i).getRepairs().get(i).getRepairCauses() + "\t|\t" +
////                    user.getCar().get(i).getRepairs().get(i).getRepairState().get(user.getCar().get(i).getRepairs().get(i).getRepairId()).getStates().getName() + "\t|\t" +
////                    user.getCar().get(i).getRepairs().get(i).getPrice();
//            System.out.println(car.getCourse());
            System.out.println(car.getCarModels().getCarMarks().getMarkName() + " " + car.getCarModels().getModelName() + " " + r1.getRepairCauses() + " " + rs1.getStates().getName());
            i++;
        }
    session.close();
    }


//


//        System.out.println(user.getCar().get(0).getRepairs().get(0).getRepairCauses());
//        sb.append()

//        relation tests ------- it works.
//        Session session = factory.getCurrentSession();
//        session.getTransaction().begin();
//        Users user;
//        user = session.get(Users.class, LogInController.loggedUserId);
//        System.out.println(user.getCar().get(0).getCarModels().getCarMarks().getMarkName());
//        session.close();
//    }

    public void logIn(ActionEvent event) throws IOException {
        this.changeScene(event, "/LogInScene.fxml");
    }

    public void register(ActionEvent event) throws IOException {
        this.changeScene(event, "/RegisterScene.fxml");
    }
}
