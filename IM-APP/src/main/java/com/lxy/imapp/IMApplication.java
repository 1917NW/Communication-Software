package com.lxy.imapp;

import com.lxy.imapp.front.view.Chat;
import com.lxy.imapp.front.view.Login;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.util.SerializationUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class IMApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Chat login = new Chat();
        login.show();
    }

}