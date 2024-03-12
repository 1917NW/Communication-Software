package com.lxy.imapp;

import com.lxy.imapp.view.Chat;
import com.lxy.imapp.view.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IMApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Chat chat = new Chat();
        chat.show();
    }

    public static void main(String[] args) {
        launch();
    }
}