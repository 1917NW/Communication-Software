package com.lxy.imapp.front.view;

import com.lxy.imapp.front.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login extends Stage {

    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";

    private Parent root;

    private double xOffset;

    private double yOffset;


    public LoginController loginController;

    public Login(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(RESOURCE_NAME));
            root = fxmlLoader.load();
            loginController = fxmlLoader.getController();

        } catch (IOException e) {
           e.printStackTrace();
        }

        initEventHandler(root);

        loginController.setStage(this);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        setResizable(false);
        initStyle(StageStyle.TRANSPARENT);


        getIcons().add(new Image("file:src/main/resources/fxml/login/img/app.png"));

    }

    private void initEventHandler(Parent root) {
        root.setOnMousePressed(mouseEvent -> {
            xOffset = getX() - mouseEvent.getScreenX();
            yOffset = getY() - mouseEvent.getScreenY();
            root.setCursor(Cursor.CLOSED_HAND);
        });


        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });

        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }
}
