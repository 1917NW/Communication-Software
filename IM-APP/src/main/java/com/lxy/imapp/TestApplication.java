package com.lxy.imapp;

import com.lxy.imapp.biz.event.ChatEventHandler;
import com.lxy.imapp.biz.event.LoginEventHandler;
import com.lxy.imapp.front.view.Chat;
import com.lxy.imapp.front.view.Login;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import org.checkerframework.checker.units.qual.C;

public class TestApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Chat chat = new Chat(new ChatEventHandler());
        chat.show();
    }
}
