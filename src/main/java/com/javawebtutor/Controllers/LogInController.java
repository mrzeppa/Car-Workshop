package com.javawebtutor.Controllers;

import com.javawebtutor.Models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;


public class LogInController extends Controller {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();
    public static int loggedUserId;
    public static int loggedUserRoleId;
    public static int passwordActivated;
    @FXML private TextField login;
    @FXML private PasswordField password;
    @FXML private Button logInButton;

    public void buttonAction(ActionEvent event) throws IOException {
        logIn();
        System.out.println(passwordActivated);
        if(passwordActivated == 0 && checkLoggingIn(login.getText(), password.getText()) == 1){
            changeScene(event, "/ActivatePasswordScene.fxml");
        }
        else {
            if (logIn() && this.loggedUserRoleId == 1)
                this.changeScene(event, "/ClientMainScene.fxml");
            if (logIn() && this.loggedUserRoleId == 2)
                this.changeScene(event, "/EmployeeMainScene.fxml");
            if (logIn() && this.loggedUserRoleId == 3)
                this.changeScene(event, "/ClientMainScene.fxml");
        }
    }


    public boolean logIn(){

        if(checkLoggingIn(login.getText(), password.getText()) == 1) {
//            changeScene(event, "/ActivatePasswordScene.fxml");
            System.out.println("zalogowano");
            session.close();
            return true;

        }
        else{
            System.out.println("zle dane logowania");
//            session.getTransaction().rollback();
        }
        session.close();
        return false;
//
    }

    public int checkLoggingIn(String login, String password){
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Users> cr = cb.createQuery(Users.class);
        Root<Users> root = cr.from(Users.class);
        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.like(root.get("login"), login);
        predicates[1] = cb.like(root.get("password"), password);
        cr.select(root).where(predicates);
        Query<Users> q = session.createQuery(cr);
        List<Users> results = q.getResultList();
        session.close();
//        System.out.println(results.get(0).toString());

        if(results.size() > 0) {
            loggedUserId = results.get(0).getUserId();
            loggedUserRoleId = results.get(0).getRoles().getRoleId();
            passwordActivated = results.get(0).getPasswordActivated();
//            System.out.println(passwordActivated);
        }
        else
            return 0;
        return results.size();
    }


}
