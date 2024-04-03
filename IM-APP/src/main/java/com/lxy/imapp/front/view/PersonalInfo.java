package com.lxy.imapp.front.view;

import com.lxy.imapp.biz.event.LoginEventHandler;
import com.lxy.imapp.front.controller.LoginController;
import com.lxy.imapp.front.controller.PersonalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PersonalInfo extends Stage {

    private static final String RESOURCE_NAME = "/fxml/personal/personal.fxml";

    private Parent root;

    public PersonalInfo(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(RESOURCE_NAME));
            root = fxmlLoader.load();
            PersonalController controller = fxmlLoader.getController();
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initEventHandler(root);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        setResizable(false);
        initStyle(StageStyle.TRANSPARENT);

        getIcons().add(new Image("file:src/main/resources/fxml/login/img/app.png"));

    }

    public void doShow(double x, double y) {
        setX(x); // 设置位置X
        setY(y); // 设置位置Y
        show(); // 展示窗口
    }

    private double xOffset;

    private double yOffset;
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
