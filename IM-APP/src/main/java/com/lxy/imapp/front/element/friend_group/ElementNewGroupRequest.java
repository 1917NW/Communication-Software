package com.lxy.imapp.front.element.friend_group;

import com.lxy.imapp.biz.socket.po.UserGroupRequest;
import com.lxy.protocolpackage.protocol.friend.dto.UserDto;
import com.lxy.protocolpackage.protocol.group.dto.GroupDto;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ElementNewGroupRequest {
    private Pane pane;    // 用户底板

    // 用户信息
    private Label idLabel;    // 展示用户ID
    private Label headLabel;  // 头像区域
    private Label nameLabel;  // 名称区域

    private Label applyLabel;  // 申请区域
    // 群组信息
    private Label groupIdLabel;    // 群组ID
    private Label groupHeadLabel;  // 群组区域
    private Label groupNameLabel;  // 群组名称区域

    private Label agreeLabel;// 状态；0添加/1接受
    private Label rejectLabel;
    private Label line;       // 底线


    public ElementNewGroupRequest(UserDto userDto, GroupDto groupDto) {
        String userId = userDto.getUserId();
        String userHead = userDto.getUserHead();
        String userNickName = userDto.getUserNickName();

        pane = new Pane();
        pane.setUserData(new UserGroupRequest(userDto.getUserId(), groupDto.getGroupId()));
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

        groupHeadLabel = new Label();
        groupHeadLabel.setPrefSize(50,50);
        groupHeadLabel.setLayoutX(400);
        groupHeadLabel.setLayoutY(10);
        groupHeadLabel.setStyle("-fx-background-color: red");
        children.add(groupHeadLabel);

        // 申请区域
        applyLabel = new Label();
        applyLabel.setText("申请加入");
        applyLabel.setLayoutX(270);
        applyLabel.setLayoutY(30);
        applyLabel.setTextFill(Color.BLACK);
        children.add(applyLabel);

        // 名称区域
        nameLabel = new Label();
        nameLabel.setPrefSize(200, 30);
        nameLabel.setLayoutX(190);
        nameLabel.setLayoutY(10);
        nameLabel.setText(userNickName);
        nameLabel.getStyleClass().add("elementFriendLuckUser_name");
        children.add(nameLabel);

        groupNameLabel = new Label();
        groupNameLabel.setPrefSize(200, 30);
        groupNameLabel.setLayoutX(465);
        groupNameLabel.setLayoutY(10);
        groupNameLabel.setText(groupDto.getGroupName());
        groupNameLabel.getStyleClass().add("elementFriendLuckUser_name");
        children.add(groupNameLabel);

        // ID区域
        idLabel = new Label();
        idLabel.setPrefSize(200, 20);
        idLabel.setLayoutX(190);
        idLabel.setLayoutY(40);
        idLabel.setText(userId);
        idLabel.getStyleClass().add("elementFriendLuckUser_id");
        children.add(idLabel);

        groupIdLabel = new Label();
        groupIdLabel.setPrefSize(200, 20);
        groupIdLabel.setLayoutX(465);
        groupIdLabel.setLayoutY(40);
        groupIdLabel.setText(userId);
        groupIdLabel.getStyleClass().add("elementFriendLuckUser_id");
        children.add(groupIdLabel);

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
