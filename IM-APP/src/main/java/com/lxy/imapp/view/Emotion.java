package com.lxy.imapp.view;

import com.lxy.imapp.controller.ChatController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Emotion extends Stage {

    private static final String RESOURCE_NAME = "/fxml/emotion/emotion.fxml";

    private Parent root;

    public Emotion(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(RESOURCE_NAME));
            root = fxmlLoader.load();

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);

            setResizable(false);
            initStyle(StageStyle.TRANSPARENT);
            initModality(Modality.APPLICATION_MODAL);
            initEventHanlder();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initEventHanlder() {
        root.setOnMouseExited(mouseEvent -> {
            hide();
        });
    }

    public void doShowFace(double x, double y) {
        setX(x); // 设置位置X
        setY(y); // 设置位置Y
        show(); // 展示窗口
    }

}
