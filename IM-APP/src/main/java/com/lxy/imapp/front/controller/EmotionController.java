package com.lxy.imapp.front.controller;

import com.lxy.imapp.biz.event.ChatEventHandler;
import com.lxy.imapp.front.cache.ParentNodeCache;
import com.lxy.imapp.front.data.TalkBoxData;
import com.lxy.imapp.front.view.Chat;

import com.lxy.protocolpackage.constants.MsgType;
import com.lxy.protocolpackage.constants.TalkType;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Pane;

import java.util.Date;

public class EmotionController {

    @FXML
    private Pane emotionPane;

    private ListView<Pane> talkList;

    private ChatEventHandler chatEventHandler;

    private ChatController chatController;

    private String imgPathPrefix = "file:src/main/resources/fxml/emotion/img/";

    public void initialize() {

        loadEmotion();
    }

    public void setTalkList(ListView<Pane> talkList){
        this.talkList = talkList;
    }

    public void setChatController(ChatController chatController){
        this.chatController = chatController;
    }

    public void setChatEventHandler(ChatEventHandler chatEventHandler){
        this.chatEventHandler = chatEventHandler;
    }

    public void loadEmotion(){
        ObservableList<Node> children = emotionPane.getChildren();

        Label f01 = new Label();
        f01.setUserData("f_01");
        f01.setLayoutX(20);
        f01.setLayoutY(20);
        f01.setPrefWidth(30);
        f01.setPrefHeight(30);
        f01.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_01.png')");
        f01.getStyleClass().add("look");

        Label f02 = new Label();
        f02.setUserData("f_02");
        f02.setLayoutX(60);
        f02.setLayoutY(20);
        f02.setPrefWidth(30);
        f02.setPrefHeight(30);
        f02.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_02.png')");
        f02.getStyleClass().add("look");

        Label f03 = new Label();
        f03.setUserData("f_03");
        f03.setLayoutX(100);
        f03.setLayoutY(20);
        f03.setPrefWidth(30);
        f03.setPrefHeight(30);
        f03.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_03.png')");
        f03.getStyleClass().add("look");

        Label f04 = new Label();
        f04.setUserData("f_04");
        f04.setLayoutX(140);
        f04.setLayoutY(20);
        f04.setPrefWidth(30);
        f04.setPrefHeight(30);
        f04.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_04.png')");
        f04.getStyleClass().add("look");

        Label f05 = new Label();
        f05.setUserData("f_05");
        f05.setLayoutX(180);
        f05.setLayoutY(20);
        f05.setPrefWidth(30);
        f05.setPrefHeight(30);
        f05.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_05.png')");
        f05.getStyleClass().add("look");

        Label f11 = new Label();
        f11.setUserData("f_11");
        f11.setLayoutX(20);
        f11.setLayoutY(70);
        f11.setPrefWidth(30);
        f11.setPrefHeight(30);
        f11.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_11.png')");
        f11.getStyleClass().add("look");

        Label f12 = new Label();
        f12.setUserData("f_12");
        f12.setLayoutX(60);
        f12.setLayoutY(70);
        f12.setPrefWidth(30);
        f12.setPrefHeight(30);
        f12.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_12.png')");
        f12.getStyleClass().add("look");

        Label f13 = new Label();
        f13.setUserData("f_13");
        f13.setLayoutX(100);
        f13.setLayoutY(70);
        f13.setPrefWidth(30);
        f13.setPrefHeight(30);
        f13.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_13.png')");
        f13.getStyleClass().add("look");

        Label f14 = new Label();
        f14.setUserData("f_14");
        f14.setLayoutX(140);
        f14.setLayoutY(70);
        f14.setPrefWidth(30);
        f14.setPrefHeight(30);
        f14.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_14.png')");
        f14.getStyleClass().add("look");

        Label f15 = new Label();
        f15.setUserData("f_15");
        f15.setLayoutX(180);
        f15.setLayoutY(70);
        f15.setPrefWidth(30);
        f15.setPrefHeight(30);
        f15.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_15.png')");
        f15.getStyleClass().add("look");

        Label f21 = new Label();
        f21.setUserData("f_21");
        f21.setLayoutX(20);
        f21.setLayoutY(120);
        f21.setPrefWidth(30);
        f21.setPrefHeight(30);
        f21.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_21.png')");
        f21.getStyleClass().add("look");

        Label f22 = new Label();
        f22.setUserData("f_22");
        f22.setLayoutX(60);
        f22.setLayoutY(120);
        f22.setPrefWidth(30);
        f22.setPrefHeight(30);
        f22.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_22.png')");
        f22.getStyleClass().add("look");

        Label f23 = new Label();
        f23.setUserData("f_23");
        f23.setLayoutX(100);
        f23.setLayoutY(120);
        f23.setPrefWidth(30);
        f23.setPrefHeight(30);
        f23.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_23.png')");
        f23.getStyleClass().add("look");

        Label f24 = new Label();
        f24.setUserData("f_24");
        f24.setLayoutX(140);
        f24.setLayoutY(120);
        f24.setPrefWidth(30);
        f24.setPrefHeight(30);
        f24.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_24.png')");
        f24.getStyleClass().add("look");

        Label f25 = new Label();
        f25.setUserData("f_25");
        f25.setLayoutX(180);
        f25.setLayoutY(120);
        f25.setPrefWidth(30);
        f25.setPrefHeight(30);
        f25.setStyle("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/f_25.png')");
        f25.getStyleClass().add("look");

        children.addAll(f01, f02, f03, f04, f05, f11, f12, f13, f14, f15, f21, f22, f23, f24, f25);

        for (Node next : children) {
            next.setOnMouseClicked(event -> {
                Pane selectedItem = talkList.getSelectionModel().getSelectedItem();
                TalkBoxData talkBoxData = (TalkBoxData)selectedItem.getUserData();

                if(TalkType.PRIVATE_MESSAGE.getTalkTypeCode().equals(talkBoxData.getTalkType()) && !ParentNodeCache.isFriend(talkBoxData.getTalkId())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Send Result");
                    alert.setHeaderText("Send Failed!");
                    alert.setContentText("你们目前还不是好友哦");
                    alert.showAndWait();
                    return;
                }
                if(TalkType.GROUP_MESSAGE.getTalkTypeCode().equals(talkBoxData.getTalkType()) && !ParentNodeCache.isInGroup(talkBoxData.getTalkId())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Send Result");
                    alert.setHeaderText("Send Failed!");
                    alert.setContentText("你目前不在该群组哟");
                    alert.showAndWait();
                    return;
                }
                Date msgDate = new Date();
                String faceId = (String)next.getUserData();

                chatController.addTalkMsgRight(talkBoxData.getTalkId(), faceId, MsgType.EMOTION_MSG.getMsgTypeCode(), msgDate, true,true,false);

                chatEventHandler.doEventSendMsg(chatController.currentUserId, chatController.currentUserNickName, chatController.currentUserHead,talkBoxData.getTalkId(), talkBoxData.getTalkType(), faceId, MsgType.EMOTION_MSG.getMsgTypeCode(), msgDate);
                System.out.println("表情：" + faceId);
            });
        }
    }
}
