package com.lxy.imapp.front.element.chat_group;

import com.lxy.imapp.front.util.AutoSizeTool;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

// 代表聊天框内的一条信息
public class ElementInfoBox {
    private Pane pane;

    private Pane head; //头像
    private Label nikeName; //内容箭头
    private Label infoContentArrow; //内容箭头
    private Label infoContent; // 内容

    public Pane left(String userNickName, String userHead, String msg){
        double autoHeight = AutoSizeTool.getHeight(msg);
        double autoWidth = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500, 50 + autoHeight);
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

        return pane;

    }

    // 个人
    public Pane right(String userNickName, String userHead, String msg){
        double autoHeight = AutoSizeTool.getHeight(msg);
        double autoWidth = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500, 50 + autoHeight);
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


        // 内容
        VBox vBox = new VBox();
        vBox.setLayoutY(30);
        vBox.setPrefHeight(autoHeight);
        vBox.setPrefWidth(715);
        vBox.setAlignment(Pos.TOP_RIGHT);

        infoContent = new Label();
        infoContent.setPrefWidth(autoWidth);
        infoContent.setPrefHeight(autoHeight);
        System.out.println("autoWidth:" + autoWidth + "autoHeight:" + autoHeight);
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

//        infoContent.setLayoutX(730 - autoWidth - 15);
//        infoContent.setLayoutY(30);
        vBox.getChildren().add(infoContent);

        children.add(vBox);

        return pane;

    }

}
