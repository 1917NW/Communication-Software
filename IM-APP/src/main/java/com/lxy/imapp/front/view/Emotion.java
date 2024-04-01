package com.lxy.imapp.front.view;

import com.lxy.imapp.biz.event.ChatEventHandler;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.imapp.front.controller.EmotionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Emotion extends Stage {

    private static final String RESOURCE_NAME = "/fxml/emotion/emotion.fxml";

    private Parent root;

    public EmotionController emotionController;

    public ChatEventHandler chatEventHandler;

    public ListView<Pane> talkList;

    public Emotion(ChatController chatController, ChatEventHandler chatEventHandler, ListView<Pane> talkList){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(RESOURCE_NAME));
            root = fxmlLoader.load();
            emotionController = fxmlLoader.getController();
            this.chatEventHandler = chatEventHandler;
            this.talkList = talkList;

            emotionController.setTalkList(talkList);
            emotionController.setChatEventHandler(chatEventHandler);
            emotionController.setChatController(chatController);

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
