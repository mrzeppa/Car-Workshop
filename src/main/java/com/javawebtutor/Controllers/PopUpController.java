package com.javawebtutor.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUpController implements Initializable {
    @FXML
    private Button btn;
    public static String content;

    public PopUpController() {
    }

    public void close(){
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void popUp() throws IOException {
        Parent nn = FXMLLoader.load(Controller.class.getResource("/PopUpScene.fxml"));
        Scene scene = new Scene(nn);
        Stage window = new Stage();
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.show();
        window.setTitle("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn.setStyle("-fx-alignment: center;-fx-hgap: 10; -fx-vgap: 10;");
        btn.setText(content);
    }
}
