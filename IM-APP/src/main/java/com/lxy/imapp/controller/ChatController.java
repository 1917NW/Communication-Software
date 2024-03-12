package com.lxy.imapp.controller;

import com.lxy.imapp.data.RemindCount;
import com.lxy.imapp.element.chat_group.ElementTalk;
import com.lxy.imapp.util.CacheUtil;
import com.lxy.imapp.util.Ids;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.*;

public class ChatController {

    @FXML
    private Button profile;

    @FXML
    private Label barChat;

    @FXML
    private Label barFriend;

    @FXML
    private Label barFavorite;

    @FXML
    private Label barSetting;

    @FXML
    private Pane chatPane;

    @FXML
    private Pane friendPane;

    @FXML
    private ListView<Pane> talkList;

    private Map<Pane, Label> paneLabelMap;



    private Paint darkBlue = Color.web("#0099cc");

    private Paint lightBlue = Color.web("#03e9f4");

    public void initialize() {

       setProfilePhoto();
       setBarChatStyle();
       setBarFriendStyle();
       setBarFavoriteStyle();
       setBarSettingStyle();
       initPaneList();
       initTalkList();
    }


    private void initPaneList() {
        paneLabelMap = new HashMap<>();
        paneLabelMap.put(friendPane, barFriend);
        paneLabelMap.put(chatPane, barChat);
    }

    private void switchToPane(Pane pane){
        for(Pane currenPane : paneLabelMap.keySet()){
            if(currenPane != pane) {
                currenPane.setVisible(false);
            }
            else {
                currenPane.setVisible(true);
            }
        }
    }

    private void setBarSettingStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/chat.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE621';
        barSetting.setFont(Font.font(font.getFamily(), 20));
        barSetting.setText(Character.toString(unicode));
        barSetting.setTextAlignment(TextAlignment.CENTER);
    }

    private void setBarFavoriteStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/chat.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE65E';
        barFavorite.setFont(Font.font(font.getFamily(), 27));
        barFavorite.setText(Character.toString(unicode));
        barFavorite.setTextAlignment(TextAlignment.CENTER);
    }

    private void setBarFriendStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/chat.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE6E7';
        barFriend.setFont(Font.font(font.getFamily(), 27));
        barFriend.setText(Character.toString(unicode));
        barFriend.setTextAlignment(TextAlignment.CENTER);
    }

    private void setBarChatStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/chat.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE600';
        barChat.setFont(Font.font(font.getFamily(), 27));
        barChat.setText(Character.toString(unicode));
        barChat.setTextAlignment(TextAlignment.CENTER);
    }

    private void setProfilePhoto() {

        // 给Button设置背景图片
        Image image = new Image("file:src/main/resources/fxml/chat/img/dog.png");

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(50,50,true,true,true,true));

        Background background = new Background(backgroundImage);

        // 给Button设置背景
        profile.setBackground(background);
    }

    public void switchToChat(MouseEvent mouseEvent) {
        if(chatPane.isVisible())
            return;


        switchToPane(chatPane);

        barChat.setTextFill(lightBlue);
        barFriend.setTextFill(darkBlue);


    }

    public void switchToFriend(MouseEvent mouseEvent){
        if(friendPane.isVisible())
            return;

        switchToPane(friendPane);
        barFriend.setTextFill(lightBlue);
        barChat.setTextFill(darkBlue);


    }

    public void hoverChatLabel(MouseEvent mouseEvent) {
        barChat.setTextFill(lightBlue);
    }

    public void unhoverChatLabel(MouseEvent mouseEvent) {
        if(chatPane.isVisible())
            return;

        barChat.setTextFill(darkBlue);
    }

    public void hoverFriendLabel(MouseEvent mouseEvent) {
        barFriend.setTextFill(lightBlue);
    }

    public void unhoverFriendLabel(MouseEvent mouseEvent) {
        if(friendPane.isVisible())
            return;
        barFriend.setTextFill(darkBlue);
    }

    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) {

        // 判断会话框是否有该对象
        ElementTalk elementTalk = CacheUtil.talkMap.get(talkId);
        if (null != elementTalk) {
            Node talkNode = talkList.lookup("#" + Ids.ElementTalkId.createTalkPaneId(talkId));
            if (null == talkNode) {
                talkList.getItems().add(talkIdx, elementTalk.pane());
            }
            if (selected) {
                // 设置选中
                talkList.getSelectionModel().select(elementTalk.pane());
            }
            return;
        }

        // 初始化对话框元素
        ElementTalk talkElement = new ElementTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);

        CacheUtil.talkMap.put(talkId, talkElement);
        // 填充到对话框
        ObservableList<Pane> items = talkList.getItems();

        Pane talkElementPane = talkElement.pane();
        if (talkIdx >= 0) {
            items.add(talkIdx, talkElementPane);  // 添加到第一个位置
        } else {
            items.add(talkElementPane);           // 顺序添加
        }

        if (selected) {
            talkList.getSelectionModel().select(talkElementPane);
        }
        // 对话框元素点击事件
        talkElementPane.setOnMousePressed(event -> {
            System.out.println("点击对话框：" + talkName);
        });

        // 鼠标事件[移入/移出]
        talkElementPane.setOnMouseEntered(event -> {

            talkElement.delete().setVisible(true);
        });

        talkElementPane.setOnMouseExited(event -> {
            talkElement.delete().setVisible(false);
        });
        // 从对话框中删除
        talkElement.delete().setOnMouseClicked(event -> {
            System.out.println("删除对话框：" + talkName);
            talkList.getItems().remove(talkElementPane);
            talkElement.clearMsgSketch();
        });
    }

    private void initTalkList() {

        addTalkBox(-1, 0, "1000001", "小傅哥", "01_50", "我不是一个简单的男人", new Date(), true);
        addTalkBox(-1, 0, "1000002", "铁锤", "02_50", "有本事现时里扎一下", new Date(), false);
        addTalkBox(-1, 0, "1000003", "团团", "03_50", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), false);
        addTalkBox(-1, 0, "1000004", "哈尼克兔", "04_50", "你把爱放在我的心里", new Date(), false);
        addTalkBox(0, 1, "5307397", "虫洞 · 技术栈(1区)", "group_1", "小傅哥：吉祥健康、如意安康", new Date(), false);
        addTalkBox(-1, 0, "1000005", "小傅哥", "01_50", "我不是一个简单的男人", new Date(), true);
        addTalkBox(-1, 0, "1000006", "小傅哥", "01_50", "我不是一个简单的男人", new Date(), true);
        addTalkBox(-1, 0, "1000007", "小傅哥", "01_50", "我不是一个简单的男人", new Date(), true);
        addTalkBox(-1, 0, "1000008", "小傅哥", "01_50", "我不是一个简单的男人", new Date(), true);
    }

}




