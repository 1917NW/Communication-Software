package com.lxy.imapp.element.chat_group;

import com.lxy.imapp.util.AutoSizeTool;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

// 代表聊天框内的一条信息
public class ElementInfoBox {
    private Pane pane;

    private Pane head; //头像
    private Label nikeName; //内容箭头
    private Label infoContentArrow; //内容箭头
    private TextArea infoContent; // 内容

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
        infoContent = new TextArea();
        infoContent.setPrefWidth(autoWidth);
        infoContent.setPrefHeight(autoHeight);
        infoContent.setLayoutX(80);
        infoContent.setLayoutY(30);
        infoContent.setWrapText(true);
        infoContent.setEditable(false);
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
        infoContent = new TextArea();
        infoContent.setPrefWidth(autoWidth);
        infoContent.setPrefHeight(autoHeight);
        infoContent.setLayoutX(730 - autoWidth - 15);
        infoContent.setLayoutY(30);
        infoContent.setWrapText(true);
        infoContent.setText(msg);
        infoContent.getStyleClass().add("box_infoContent_right");
        infoContent.setEditable(false);
        children.add(infoContent);

        return pane;

    }

}
