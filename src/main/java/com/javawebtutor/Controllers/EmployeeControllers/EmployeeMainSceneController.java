package com.javawebtutor.Controllers.EmployeeControllers;

import java.io.*;


import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.javawebtutor.Controllers.Controller;
import javafx.event.ActionEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class EmployeeMainSceneController extends Controller {

    public void editAction(ActionEvent event) throws IOException {
        this.changeScene(event, "/EmployeeEditProfileScene.fxml");
    }

    public void addCar(ActionEvent event) throws  IOException{
        this.changeScene(event, "/ClientAddCarScene.fxml");
    }

    public void checkCarState(ActionEvent event) throws  IOException{
        this.changeScene(event, "/EmployeeCheckCarRepairStateScene.fxml");
    }

    public void logOutButton(ActionEvent event) throws IOException {
        this.logOut();
        this.changeScene(event, "/FirstScene.fxml");
    }

    public void checkUsers(ActionEvent event) throws IOException {
        this.changeScene(event, "/EmployeeCheckUsersScene.fxml");
    }

    public void addCarType(ActionEvent event) throws IOException {
        this.changeScene(event, "/EmployeeAddCarTypeScene.fxml");
    }

    public void generatePDF(ActionEvent event) throws IOException{
        try {


            String k = "" +
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
                    "nr " +
                    "<br>Data wystawienia: " + LocalDate.now() +

                    "</div>" +

                    "<div id='div3'><p>" +
                    "<div id='div3-1'>" +
                    "Nabywca</p>" +
                    "</div>" +
                    "<p>Imie nazwisko<br/>" +
                    "Adresik1</br>" +
                    "Adresik2</br>" +

                    "</p></div>" +

                    "<div id='div4'>" +
                    "<table>" +
                    "<tr>" +
                    "<th style='width:5%'>Lp.</th>" +
                    "<th style='width:50%'>Usluga</th>" +
                    "<th style='width:15%'>Stawka VAT</th>" +
                    "<th style='width:15%'>Cena netto</th>" +
                    "<th style='width:15%'>Cena brutto</th>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>1</td>" +
                    "<td>Naprawa samochodu</td>" +
                    "<td>23%</td>" +
                    "<td>1200</td>" +
                    "<td>1233</td>" +
                    "</tr>" +
                    "</table>" +
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
                    "</table>" +
                    "</div>" +
                    "<div id='div6'>" +
                    "<div id='div6-1'>...................................<br/>Podpis sprzedawcy</div>" +
                    "<div id='div6-2'>...................................<br/>Podpis nabywcy</div>" +
                    "</div>" +

                    "</body>" +
                    "</html>" +

                    "";
            OutputStream output = new FileOutputStream("testa.pdf");
            HtmlConverter.convertToPdf(k, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
