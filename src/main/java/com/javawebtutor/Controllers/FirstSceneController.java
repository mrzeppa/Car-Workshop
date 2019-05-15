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
