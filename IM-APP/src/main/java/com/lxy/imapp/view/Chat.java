package com.lxy.imapp.view;

import com.lxy.imapp.controller.ChatController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Chat extends Stage {
    private static final String RESOURCE_NAME = "/fxml/chat/chat.fxml";

    private Parent root;

    public Chat(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(RESOURCE_NAME));
            root = fxmlLoader.load();
            ChatController controller = fxmlLoader.getController();

            controller.setStage(this);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);
            setResizable(false);
            initStyle(StageStyle.TRANSPARENT);


            getIcons().add(new Image("file:src/main/resources/fxml/login/img/app.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
