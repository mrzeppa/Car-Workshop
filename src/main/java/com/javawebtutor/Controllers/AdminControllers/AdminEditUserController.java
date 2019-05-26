package com.javawebtutor.Controllers.AdminControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.CarMarks;
import com.javawebtutor.Models.Roles;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminEditUserController extends Controller implements Initializable {
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
        loadData();
    }

    public void loadData(){
        Session session = factory.getCurrentSession();
        List<Roles> r = loadAllData(Roles.class, session);
        session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users u = session.get(Users.class, AdminUsersController.userId);
        name.setText(u.getName());
        surname.setText(u.getSurname());
        street.setText(u.getAddress().getStreet());
        homeNumber.setText(Integer.toString(u.getAddress().getHomeNumber()));
        postCode.setText(Integer.toString(u.getAddress().getPostCode()));
        city.setText(u.getAddress().getCity());
        login.setText(u.getLogin());
        for(Roles rl : r){
            cbx.getItems().add(rl.getRoleName());
        }
        cbx.setValue(u.getRoles().getRoleName());
        session.close();
    }

    public void editData(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users user;
        user = session.get(Users.class, AdminUsersController.userId);
        user.setName(name.getText());
        user.setSurname(surname.getText());
        user.getAddress().setStreet(street.getText());
        user.getAddress().setHomeNumber(Integer.parseInt(homeNumber.getText()));
        user.getAddress().setPostCode(Integer.parseInt(postCode.getText()));
        user.getAddress().setCity(city.getText());
        user.setLogin(login.getText());

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Roles> cr = cb.createQuery(Roles.class);
        Root<Roles> root = cr.from(Roles.class);
        cr.select(root).where(cb.like(root.get("roleName"), cbx.getValue().toString()));
        Query<Roles> q = session.createQuery(cr);
        List<Roles> results = q.getResultList();

        user.setRoles(results.get(0));

        session.getTransaction().commit();
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        changeScene(event, "/AdminUsersScene.fxml");
    }
}
