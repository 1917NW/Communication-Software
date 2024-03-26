package com.lxy.imapp.front.element.friend_group;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.awt.*;

/**
 * 创建群组面板
 */
public class ElementCreateGroup {

    private Pane pane;

    private Label head;  // 头像
    private Label name;  // 名称

    private Pane subPane; // 公众号面板

    private Label groupNameLabel;
    private TextField groupName;

    private Label groupHeadLabel;



    private Button createGroupButton;

    public ElementCreateGroup(){
        pane = new Pane();
        pane.setPrefSize(270, 70);
        pane.getStyleClass().add("elementFriendSubscription");
        ObservableList<Node> children = pane.getChildren();

        // 头像区域
        head = new Label();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(10);
        head.getStyleClass().add("elementFriendSubscription_head");
        children.add(head);

        // 名称区域
        name = new Label();
        name.setPrefSize(200,40);
        name.setLayoutX(80);
        name.setLayoutY(15);
        name.setText("创建群组");
        name.getStyleClass().add("elementFriendSubscription_name");
        children.add(name);

        // 初始化未装载
        subPane = new Pane();
        subPane.setPrefSize(808, 560);
        subPane.setStyle("-fx-background-color:white;");
        ObservableList<Node> subPaneChildren = subPane.getChildren();



        double groupNameX = 50;
        double groupNameY = 100;
        groupNameLabel = new Label();
        groupNameLabel.setFont(Font.font(25));
        groupNameLabel.setText("群组名称");
        groupNameLabel.setPrefSize(200, 40);
        groupNameLabel.setLayoutX(groupNameX);
        groupNameLabel.setLayoutY(groupNameY);
        subPaneChildren.add(groupNameLabel);

        groupName = new TextField();
        groupName.setPromptText("群组名称不超过18个字符");
        groupName.setLayoutX(groupNameX + 120);
        groupName.setLayoutY(groupNameY );
        groupName.setPrefSize(600, 50);
        subPaneChildren.add(groupName);

        createGroupButton = new Button();
        createGroupButton.setStyle("-fx-background-color: #99cc00");
        createGroupButton.setTextFill(Color.WHITE);
        createGroupButton.setLayoutX(groupNameX + 350);
        createGroupButton.setLayoutY(groupNameY + 100);
        createGroupButton.setPrefSize(90, 45);
        createGroupButton.setText("创建群组");

        subPaneChildren.add(createGroupButton);
    }

    public Pane pane() {
        return pane;
    }

    public Pane subPane() {
        return subPane;
    }

    public Button getCreateGroupButton() {
        return createGroupButton;
    }

    public TextField getGroupName() {
        return groupName;
    }
}
