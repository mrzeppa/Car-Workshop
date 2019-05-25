package com.javawebtutor.Controllers.EmployeeControllers;

import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Controllers.LogInController;
import com.javawebtutor.Models.Invoices;
import com.javawebtutor.Models.Repairs;
import com.javawebtutor.Models.RepairsOnInvoice;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeEditProfileController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    @FXML
    private TextField name;
    @FXML private TextField surname;
    @FXML private TextField street;
    @FXML private TextField homeNumber;
    @FXML private TextField postCode;
    @FXML private TextField city;
    @FXML private TextField login;
    @FXML private PasswordField password;

    public void initialize(URL location, ResourceBundle resources) {
//        relation tests ------- it works.
//        Session session = factory.getCurrentSession();
//        session.getTransaction().begin();
//        Users user;
//        user = session.get(Users.class, LogInController.loggedUserId);
//        System.out.println(user.getCar().get(0).getCarModels().getCarMarks().getMarkName());
//        session.close();
        loadData();
    }

    public void loadData(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        List<Users> list = factory.getCurrentSession()
                .createQuery("from " + Users.class.getName() + " WHERE userId = " + LogInController.loggedUserId).list();
        name.setText(list.get(0).getName());
        surname.setText(list.get(0).getSurname());
        street.setText(list.get(0).getAddress().getStreet());
        homeNumber.setText(Integer.toString(list.get(0).getAddress().getHomeNumber()));
        postCode.setText(Integer.toString(list.get(0).getAddress().getPostCode()));
        city.setText(list.get(0).getAddress().getCity());
        login.setText(list.get(0).getLogin());
//
//        Invoices ron1;
//        ron1 = session.get(Invoices.class, 1);
//        List<RepairsOnInvoice> a = ron1.getRON();
//        System.out.println(a.get(1).getRepairId().getRepairCauses());

        session.close();
    }

    public void editData(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users user;
        user = session.get(Users.class, LogInController.loggedUserId);
        user.setName(name.getText());
        user.setSurname(surname.getText());
        user.getAddress().setStreet(street.getText());
        user.getAddress().setHomeNumber(Integer.parseInt(homeNumber.getText()));
        user.getAddress().setPostCode(Integer.parseInt(postCode.getText()));
        user.getAddress().setCity(city.getText());
        if(!password.getText().equals(""))
            user.setPassword(password.getText());
        session.getTransaction().commit();
        session.close();
    }

    public void backButton(ActionEvent event) throws IOException {
        this.changeScene(event, "/EmployeeMainScene.fxml");
    }
}
