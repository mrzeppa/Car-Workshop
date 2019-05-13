package com.javawebtutor.Controllers;

import com.javawebtutor.Models.Address;
import com.javawebtutor.Models.Users;
import javafx.fxml.FXML;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class RegisterController extends Controller {
    SessionFactory factory = HibernateUtil.getSessionFactory();

    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField street;
    @FXML private TextField homeNumber;
    @FXML private TextField postCode;
    @FXML private TextField city;
    @FXML private TextField login;
    @FXML private PasswordField password;


    public void register(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        if(checkLogin(login.getText()) == 0) {

            Address ad1 = new Address();
            ad1.setCity(city.getText());
            ad1.setHomeNumber(Integer.parseInt(homeNumber.getText()));
            ad1.setPostCode(Integer.parseInt(postCode.getText()));
            ad1.setStreet(street.getText());
            session.save(ad1);
            Users u1 = new Users();
            u1.setAddress(ad1);
            u1.setName(name.getText());
            u1.setSurname(surname.getText());
            u1.setLogin(login.getText());
            u1.setPassword(password.getText());
            session.save(u1);
            session.getTransaction().commit();
        }
        else{
            System.out.println("login zajrty");
            session.getTransaction().rollback();
        }
        session.close();

//
    }

    public int checkLogin(String login){
        List<Users> list = factory.getCurrentSession()
        .createQuery("from " + Users.class.getName() + " WHERE login = '" + login + "'").list();
        return list.size();
    }

}
