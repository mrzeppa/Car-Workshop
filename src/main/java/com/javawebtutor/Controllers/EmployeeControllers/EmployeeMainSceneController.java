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

}
