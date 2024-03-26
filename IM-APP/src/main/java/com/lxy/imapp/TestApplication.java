package com.lxy.imapp;

import com.lxy.imapp.biz.event.ChatEventHandler;
import com.lxy.imapp.front.view.Chat;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Chat chat = new Chat(new ChatEventHandler());
        chat.show();
    }
}
