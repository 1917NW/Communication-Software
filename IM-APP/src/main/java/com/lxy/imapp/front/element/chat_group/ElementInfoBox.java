package com.lxy.imapp.front.element.chat_group;

import com.lxy.imapp.biz.file.FileManager;
import com.lxy.imapp.biz.threadPool.TaskExecutor;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.constant.ImFileConstants;
import com.lxy.imapp.front.util.AutoSizeTool;
import com.lxy.imapp.front.view.Chat;
import com.lxy.protocolpackage.constants.MsgType;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;

import java.io.File;

// 代表聊天框内的一条信息
public class ElementInfoBox {
    private Pane pane;

    private Pane head; //头像
    private Label nikeName; //内容箭头
    private Label infoContentArrow; //内容箭头
    private Label infoContent; // 内容

    public Pane left(String userId, String userNickName, String userHead, String msg, Integer msgType, Chat chat){
        double autoHeight = AutoSizeTool.getHeight(msg);
        double autoWidth = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500, 50 + autoHeight);
        if(MsgType.FILE_MSG.getMsgTypeCode().equals(msgType))
            pane.setPrefSize(500, 150);
        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();

        // 头像
        head = new Pane();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        // TODO 设置头像
        head.setStyle("-fx-background-color: blue");
        children.add(head);

        // 昵称
        nikeName = new Label();
        nikeName.setPrefSize(450, 20);
        nikeName.setLayoutX(75);
        nikeName.setLayoutY(5);
        nikeName.setText(userNickName);
        nikeName.getStyleClass().add("box_nikeName");
        children.add(nikeName);

        switch (msgType){
            case 0:
                // 内容
                infoContent = new Label();
                infoContent.setPrefWidth(autoWidth);
                infoContent.setPrefHeight(autoHeight);
                infoContent.setLayoutX(80);
                infoContent.setLayoutY(30);
                infoContent.setWrapText(true);

                ContextMenu contextMenu = new ContextMenu();
                MenuItem copyMenuItem = new MenuItem("复制");
                copyMenuItem.setOnAction(event -> {
                    // 将文本内容复制到剪贴板
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(infoContent.getText());
                    clipboard.setContent(content);
                });
                MenuItem deleteMenuItem = new MenuItem("删除");

                contextMenu.getItems().add(copyMenuItem);
                contextMenu.getItems().add(deleteMenuItem);


                // 将ContextMenu绑定到Label上
                infoContent.setContextMenu(contextMenu);

                infoContent.setText(msg);
                infoContent.getStyleClass().add("box_infoContent_left");
                children.add(infoContent);
                break;
            case 1:
                Label infoContentFace = new Label();
                infoContentFace.setPrefWidth(60);
                infoContentFace.setPrefHeight(40);
                infoContentFace.setLayoutX(80);
                infoContentFace.setLayoutY(30);
                infoContentFace.setStyle(String.format("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/%s.png');-fx-background-position: center center;-fx-background-repeat: no-repeat;-fx-background-color: #ffffff;-fx-border-width: 0 1px 1px 0;-fx-border-radius: 2px;-fx-background-radius: 2px;", msg));
                children.add(infoContentFace);
                break;
            case 2:

                Label infoContent = new Label();
                infoContent.setLayoutX(80);
                infoContent.setLayoutY(30);
                infoContent.setPrefWidth(300);
                infoContent.setPrefHeight(100);

                infoContent.setStyle("-fx-background-color: white; -fx-border-width: 2px;-fx-border-color: blue;");


                infoContent.setText("[文件] " + msg);
                infoContent.getStyleClass().add("box_infoContent_left");

                children.add(infoContent);
                infoContent.setOnMousePressed(event -> {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("另存为");
                    File selectedDirectory = directoryChooser.showDialog(chat);
                    String dirName = "";
                    if (selectedDirectory != null) {
                         dirName  = selectedDirectory.getAbsolutePath();
                         String filePath = dirName + "/" +  msg;
                         String objectName = userId + "/" +msg;
                        TaskExecutor.execTask(()->{
                            FileManager.downLoadFile(ImFileConstants.Bucket, objectName, filePath);
                        });
                    }
                });
                break;
        }


        return pane;

    }

    // 个人
    public Pane right(String userNickName, String userHead, String msg, Integer msgType){
        double autoHeight = AutoSizeTool.getHeight(msg);
        double autoWidth = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500, 50 + autoHeight);
        if(MsgType.FILE_MSG.getMsgTypeCode().equals(msgType))
            pane.setPrefSize(500, 150);
        pane.setLayoutX(853);
        pane.setLayoutY(0);
        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();

        // 头像
        head = new Pane();
        head.setPrefSize(50, 50);
        head.setLayoutX(730);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        // TODO:设置为照片
        head.setStyle("-fx-background-color: yellow");
        children.add(head);


        switch (msgType){
            case 0:
                // 内容
                VBox vBox = new VBox();
                vBox.setLayoutY(30);
                vBox.setPrefHeight(autoHeight);
                vBox.setPrefWidth(715);
                vBox.setAlignment(Pos.TOP_RIGHT);

                infoContent = new Label();
                infoContent.setPrefWidth(autoWidth);
                infoContent.setPrefHeight(autoHeight);
                infoContent.setWrapText(true);
                infoContent.setText(msg);
                infoContent.getStyleClass().add("box_infoContent_right");
                infoContent.setAlignment(Pos.TOP_LEFT);

                ContextMenu contextMenu = new ContextMenu();
                MenuItem copyMenuItem = new MenuItem("复制");
                copyMenuItem.setOnAction(event -> {
                    // 将文本内容复制到剪贴板
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(infoContent.getText());
                    clipboard.setContent(content);
                });
                MenuItem deleteMenuItem = new MenuItem("删除");

                contextMenu.getItems().add(copyMenuItem);
                contextMenu.getItems().add(deleteMenuItem);


                // 将ContextMenu绑定到Label上
                infoContent.setContextMenu(contextMenu);

                vBox.getChildren().add(infoContent);

                children.add(vBox);
                break;
            case 1:
                Label infoContentFace = new Label();
                infoContentFace.setPrefWidth(60);
                infoContentFace.setPrefHeight(40);
                infoContentFace.setLayoutX(715 - 60);
                infoContentFace.setLayoutY(30);
                infoContentFace.setStyle(String.format("-fx-background-image: url('file:src/main/resources/fxml/emotion/img/%s.png');-fx-background-position: center center;-fx-background-repeat: no-repeat;-fx-background-color: #9eea6a;-fx-border-width: 0 1px 1px 0;-fx-border-radius: 2px;-fx-background-radius: 2px;", msg));
                children.add(infoContentFace);
                break;
            case 2:


                Label infoContent = new Label();
                infoContent.setLayoutX(715 - 300);
                infoContent.setLayoutY(30);
                infoContent.setPrefWidth(300);
                infoContent.setPrefHeight(100);

                infoContent.setStyle("-fx-background-color: white; -fx-border-width: 2px;-fx-border-color: blue;");


                infoContent.setText("[文件] " + msg);
                infoContent.getStyleClass().add("box_infoContent_right");

                children.add(infoContent);
//                Label downloadLabel = new Label();
//                downloadLabel.setPrefWidth(45);
//                downloadLabel.setPrefHeight(30);
//                downloadLabel.setLayoutX(715 - 60);
//                downloadLabel.setLayoutY(95);
//                downloadLabel.getStyleClass().add("box_infoContent_right");
//                downloadLabel.setText("下载");
//                children.add(downloadLabel);
                break;
        }


        return pane;

    }

}
