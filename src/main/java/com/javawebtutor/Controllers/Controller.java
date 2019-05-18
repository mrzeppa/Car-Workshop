package com.javawebtutor.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.util.List;

public class Controller {

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(newScene);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public static <T> List<T> loadAllData(Class<T> type, Session session) {
        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }

    public void logOut(){
        LogInController.loggedUserId = 999999999;
    }
}
