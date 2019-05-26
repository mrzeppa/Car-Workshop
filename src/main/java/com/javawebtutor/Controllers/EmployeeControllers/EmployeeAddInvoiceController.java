package com.javawebtutor.Controllers.EmployeeControllers;

import com.itextpdf.html2pdf.HtmlConverter;
import com.javawebtutor.Controllers.Controller;
import com.javawebtutor.Models.Cars;
import com.javawebtutor.Models.Classes.InvoiceCarTable;
import com.javawebtutor.Models.Invoices;
import com.javawebtutor.Models.RepairsOnInvoice;
import com.javawebtutor.Models.Users;
import com.javawebtutor.Utilities.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeAddInvoiceController extends Controller implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();
    @FXML
    private TableView<InvoiceCarTable> tv1;
    @FXML
    private TableColumn<InvoiceCarTable, String> mark;
    @FXML
    private TableColumn<InvoiceCarTable, String> model;
    @FXML
    private TableColumn<InvoiceCarTable, String> cb;
    @FXML
    private ObservableList<InvoiceCarTable> personData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    List<Cars> carSelectedList = new ArrayList<>();

    public void loadData() {
        session.getTransaction().begin();
        Users user = session.get(Users.class, EmployeeCheckUsersController.userId);
        List<Cars> cars = user.getCars();
        tv1.setItems(personData);
        for (Cars c : cars) {
            if (c.getRepairs().get(0).getRepairState().get(0).getStates().getStateId() == 3) {
                System.out.println(c.getCarModels().getModelName());
                CheckBox cb1 = new CheckBox();

                cb1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (!carSelectedList.contains(c))
                            carSelectedList.add(c);
                        else
                            carSelectedList.remove(c);
                    }
                });
                InvoiceCarTable ict = new InvoiceCarTable(c.getCarModels().getCarMarks().getMarkName(), c.getCarModels().getModelName(), cb1);
                mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
                model.setCellValueFactory(new PropertyValueFactory<>("model"));
                cb.setCellValueFactory(new PropertyValueFactory<>("cb"));

                personData.add(ict);
            }
        }
        session.close();
    }

    public void generatePDF() {
        Session session = factory.getCurrentSession();
        List <Invoices> invList = loadAllData(Invoices.class, session);
        session = factory.getCurrentSession();
        session.getTransaction().begin();
        Users user = session.get(Users.class, EmployeeCheckUsersController.userId);
        double netPriceSum = 0;
        double grossPriceSum = 0;
        Invoices in = new Invoices();
        in.setDateOfMake(new Date());
        in.setInvoiceNumber(Integer.toString(invList.size() + 1));
        for(Cars c : carSelectedList){
            RepairsOnInvoice ron = new RepairsOnInvoice();
            ron.setInvoiceId(in);
            ron.setRepairId(c.getRepairs().get(0));
            session.save(ron);
        }

        session.save(in);
        session.getTransaction().commit();

        try {

            StringBuilder sb = new StringBuilder();
            sb.append("" +
                    "" +
                    "<html>" +
                    "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"theme.css\">" +
                    "</head>" +
                    "<body>" +
                    "<div id='div1'><p>" +
                    "<div id='div1-1'>" +
                    "Dane wystawiajacego</p>" +
                    "</div>" +
                    "<p>Car Workshop<br/>" +
                    "Plac Wolnosci 2/17<br/>" +
                    "12-123 Gdańsk<br/>" +
                    "NIP: 1231231231" +
                    "</p></div>" +

                    "<div id='div2'>" +
                    "<font size=6>Faktura VAT</font><br>" +
                    "nr FAV-" + in.getInvoiceId() +
                    "<br>Data wystawienia: " + LocalDate.now() +

                    "</div>" +

                    "<div id='div3'><p>" +
                    "<div id='div3-1'>" +
                    "Nabywca</p>" +
                    "</div>" +
                    "<p>" + user.getName() + " " + user.getSurname() + "<br/>" + user.getAddress().getStreet() + " " + user.getAddress().getHomeNumber() +
                    "</br>" + user.getAddress().getPostCode() + " " + user.getAddress().getCity() +
                    "</br>" +

                    "</p></div>" +

                    "<div id='div4'>" +
                    "<table>" +
                    "<tr>" +
                    "<th style='width:5%'>Lp.</th>" +
                    "<th style='width:50%'>Usluga</th>" +
                    "<th style='width:15%'>Stawka VAT</th>" +
                    "<th style='width:15%'>Cena netto</th>" +
                    "<th style='width:15%'>Cena brutto</th>" +
                    "</tr>");
            int i = 1;
            for(Cars c : carSelectedList){
                sb.append("<tr>" +
                        "<td>" + i + "</td>" +
                        "<td>Naprawa samochodu " + c.getCarModels().getCarMarks().getMarkName() + " " + c.getCarModels().getModelName() + "</td>" +
                        "<td>23%</td>" +
                        "<td>" + c.getRepairs().get(0).getPrice() + "</td>" +
                        "<td>" + c.getRepairs().get(0).getPrice() * 1.23 + "</td>" +
                        "</tr>");
                i++;
                netPriceSum = netPriceSum + c.getRepairs().get(0).getPrice();
                grossPriceSum = grossPriceSum + (c.getRepairs().get(0).getPrice() * 1.23);
            }



                    sb.append("</table>" +
                    "<div id='div5'>" +
                    "Sposób płatnosci: przelew<br/>" +
                    "Termin płatności: " + (LocalDate.now().plusDays(7)) +
                    "<br/>Nazwa banku: mBank<br/>" +
                    "Numer konta: 12312231232132189979321" +
                    "</div>" +

                    "<table id='tab1'>" +
                    "<tr>" +
                    "<th colspan=2>Razem</th>" +
                    "</tr>" +
                    "<tr>" +
                    "<th>Cena netto</th>" +
                    "<th>Cena brutto</th>" +
                    "</tr>" +
                            "<th>" + netPriceSum + "</th>" +
                            "<th>" + grossPriceSum + "</th> " +
                    "</table>" +
                    "</div>" +
                    "<div id='div6'>" +
                    "<div id='div6-1'>...................................<br/>Podpis sprzedawcy</div>" +
                    "<div id='div6-2'>...................................<br/>Podpis nabywcy</div>" +
                    "</div>" +

                    "</body>" +
                    "</html>" +

                    "");
            OutputStream output = new FileOutputStream("testa.pdf");
            HtmlConverter.convertToPdf(sb.toString(), output);

            session.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
