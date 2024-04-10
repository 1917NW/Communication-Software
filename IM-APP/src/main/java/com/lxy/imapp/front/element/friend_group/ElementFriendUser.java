package com.lxy.imapp.front.element.friend_group;

import com.lxy.imapp.biz.event.ChatEventHandler;
import com.lxy.imapp.front.cache.ParentNodeCache;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.checkerframework.checker.units.qual.A;

import java.util.Optional;

public class ElementFriendUser {

    private Pane pane;    // 用户底板(存储用户ID)

    private Label headLabel;  // 头像区域
    private Label nameLabel;  // 名称区域

    public ElementFriendUser(String userId, String userNickName, String userHead){
        // 用户底板(存储用户ID)
        pane = new Pane();
        pane.setUserData(userId);
        pane.setId(userId);
        pane.setPrefWidth(250);
        pane.setPrefHeight(70);
        pane.getStyleClass().add("elementFriendUser");

        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("删除好友");
        deleteMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("删除好友");

            alert.setHeaderText( "您确定要删除好友:"+userNickName + "?");
            alert.setContentText("危险操作!!!");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){
                ParentNodeCache.removeFriend(userId);
                new ChatEventHandler().doDeleteFriend(userId);
            }
        });
        contextMenu.getItems().add(deleteMenuItem);
        pane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(pane, event.getScreenX(), event.getScreenY());
                event.consume(); // 防止默认的右键菜单显示
            }
        });

        ObservableList<Node> children = pane.getChildren();
        // 头像区域
        headLabel = new Label();
        headLabel.setPrefSize(50, 50);
        headLabel.setLayoutX(15);
        headLabel.setLayoutY(10);
        headLabel.getStyleClass().add("elementFriendUser_head");
        headLabel.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(headLabel);
        // 名称区域
        nameLabel = new Label();
        nameLabel.setPrefSize(200, 40);
        nameLabel.setLayoutX(80);
        nameLabel.setLayoutY(15);
        nameLabel.setText(userNickName);
        nameLabel.getStyleClass().add("elementFriendUser_name");
        children.add(nameLabel);
    }

    public Pane pane() {
        return pane;
    }

}