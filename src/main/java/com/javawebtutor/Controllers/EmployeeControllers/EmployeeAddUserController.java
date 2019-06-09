package com.javawebtutor.Controllers.EmployeeControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Controllers.PopUpController;
import com.javawebtutor.Models.Address;
import com.javawebtutor.Models.Roles;
import com.javawebtutor.Models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;


public class EmployeeAddUserController extends Controller {
    SessionFactory factory = HibernateUtil.getSessionFactory();

    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField street;
    @FXML private TextField homeNumber;
    @FXML private TextField postCode;
    @FXML private TextField city;
    @FXML private TextField login;
    @FXML private TextField email;
    @FXML private Label emailWarning;


    public void register() throws IOException {
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        if(checkLogin(login.getText()) == 0) {
            if(email.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {

                Address ad1 = new Address();
                Roles r = session.get(Roles.class, 1);
                ad1.setCity(city.getText());
                ad1.setHomeNumber(Integer.parseInt(homeNumber.getText()));
                ad1.setPostCode(Integer.parseInt(postCode.getText()));
                ad1.setStreet(street.getText());
                session.save(ad1);
                Users u1 = new Users();
                u1.setRoles(r);
                u1.setAddress(ad1);
                u1.setName(name.getText());
                u1.setSurname(surname.getText());
                u1.setLogin(login.getText());
                u1.setPassword("");
                u1.setPasswordActivated(0);
                u1.setEmail(email.getText());
                session.save(u1);
                session.getTransaction().commit();
                PopUpController pop = new PopUpController();
                PopUpController.content = "Dodano";
                pop.popUp();
            }
            else{
                PopUpController pop = new PopUpController();
                PopUpController.content = "Niepoprawny email";
                pop.popUp();
            }
        }
        else{
            session.getTransaction().rollback();
        }
        session.close();

    }

    public int checkLogin(String login){
        List<Users> list = factory.getCurrentSession()
                .createQuery("from " + Users.class.getName() + " WHERE login = '" + login + "'").list();
        return list.size();
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/EmployeeCheckUsersScene.fxml");
    }

}
