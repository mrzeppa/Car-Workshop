package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.Address;
import com.javawebtutor.Models.Roles;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminAddUserController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();

    @FXML
    private TextField name;
    @FXML private TextField surname;
    @FXML private TextField street;
    @FXML private TextField homeNumber;
    @FXML private TextField postCode;
    @FXML private TextField city;
    @FXML private TextField login;
    @FXML private ChoiceBox cbx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = factory.getCurrentSession();
        List<Roles> r = loadAllData(Roles.class, session);
        for(Roles rl : r){
            cbx.getItems().add(rl.getRoleName());
        }
    }


    public void register(){
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Roles> cr = cb.createQuery(Roles.class);
        Root<Roles> root = cr.from(Roles.class);
        cr.select(root).where(cb.like(root.get("roleName"), cbx.getValue().toString()));
        Query<Roles> q = session.createQuery(cr);
        List<Roles> results = q.getResultList();

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
            u1.setPassword("");
            u1.setPasswordActivated(0);
            u1.setRoles(results.get(0));
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
