package com.javawebtutor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/FirstScene.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//
//        try{
//            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carworkshop?useSSL=false&serverTimezone=UTC", "root", "zaq1@WSX");
//            System.out.println("polaczylo");
//        }
//        catch(Exception exc){
//            exc.printStackTrace();
//        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
