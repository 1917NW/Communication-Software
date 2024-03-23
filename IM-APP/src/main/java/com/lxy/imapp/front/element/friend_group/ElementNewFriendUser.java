package com.lxy.imapp.front.element.friend_group;

import com.lxy.protocolpackage.constants.FriendState;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ElementNewFriendUser {
    private Pane pane;    // 用户底板
    private Label idLabel;    // 展示用户ID
    private Label headLabel;  // 头像区域
    private Label nameLabel;  // 名称区域
    private Label agreeLabel;// 状态；0添加/1接受

    private Label rejectLabel;
    private Label line;       // 底线

    /**
     * 构造函数
     *
     * @param userId       用户ID
     * @param userNickName 用户昵称
     * @param userHead     用户头像
     */
    public ElementNewFriendUser(String userId, String userNickName, String userHead) {
        pane = new Pane();
        pane.setUserData(userId);
        pane.setPrefWidth(250);
        pane.setPrefHeight(70);
        pane.getStyleClass().add("elementFriendLuckUser");
        ObservableList<Node> children = pane.getChildren();
        // 头像区域
        headLabel = new Label();
        headLabel.setPrefSize(50, 50);
        headLabel.setLayoutX(125);
        headLabel.setLayoutY(10);
        headLabel.getStyleClass().add("elementFriendLuckUser_head");
        headLabel.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(headLabel);

        // 名称区域
        nameLabel = new Label();
        nameLabel.setPrefSize(200, 30);
        nameLabel.setLayoutX(190);
        nameLabel.setLayoutY(10);
        nameLabel.setText(userNickName);
        nameLabel.getStyleClass().add("elementFriendLuckUser_name");
        children.add(nameLabel);

        // ID区域
        idLabel = new Label();
        idLabel.setPrefSize(200, 20);
        idLabel.setLayoutX(190);
        idLabel.setLayoutY(40);
        idLabel.setText(userId);
        idLabel.getStyleClass().add("elementFriendLuckUser_id");
        children.add(idLabel);

        // 底线
        line = new Label();
        line.setPrefSize(582,1);
        line.setLayoutX(125);
        line.setLayoutY(50);
        line.getStyleClass().add("elementFriendLuck_line");
        children.add(line);

        // 状态区域
        agreeLabel = new Label();
        agreeLabel.setPrefSize(56, 30);
        agreeLabel.setLayoutX(580);
        agreeLabel.setLayoutY(20);

        agreeLabel.setText("同意");
        agreeLabel.getStyleClass().add("elementNewFriend_agree");
        children.add(agreeLabel);

        // 状态区域
        rejectLabel = new Label();
        rejectLabel.setPrefSize(56, 30);
        rejectLabel.setLayoutX(650);
        rejectLabel.setLayoutY(20);

        rejectLabel.setText("拒绝");
        rejectLabel.getStyleClass().add("elementNewFriend_reject");
        children.add(rejectLabel);



    }

    public Pane pane() {
        return pane;
    }

    // 添加按钮
    public Label agreeLabel() {
        return agreeLabel;
    }

    public Label rejectLabel(){
        return rejectLabel;
    }
}
