package com.lxy.imapp.front.element.friend_group;

import com.lxy.imapp.biz.event.ChatEventHandler;
import com.lxy.imapp.front.cache.ParentNodeCache;
import com.lxy.imapp.front.data.GroupsData;
import com.lxy.imapp.front.util.Ids;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;

import java.util.Optional;

/**
 * 群组具体信息面板
 */
public class ElementFriendGroup {
    private Pane groupPane;

    public ElementFriendGroup(String groupId, String groupName, String groupHead){
        groupPane = new Pane();
        groupPane.setId(Ids.ElementTalkId.createFriendGroupId(groupId));
        groupPane.setUserData(new GroupsData(groupId, groupName, groupHead));
        groupPane.setPrefWidth(250);
        groupPane.setPrefHeight(70);
        groupPane.getStyleClass().add("elementFriendGroup");

        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("退出群组");
        deleteMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("退出群组");

            alert.setHeaderText( "您确定要退出群组:"+ groupName + "?");
            alert.setContentText("危险操作!!!");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){
                ParentNodeCache.removeGroup(groupId);
                new ChatEventHandler().doExitGroup(groupId);
            }
        });
        contextMenu.getItems().add(deleteMenuItem);
        groupPane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(groupPane, event.getScreenX(), event.getScreenY());
                event.consume(); // 防止默认的右键菜单显示
            }
        });


        ObservableList<Node> children = groupPane.getChildren();

        // 头像区域
        Label groupHeadLabel = new Label();
        groupHeadLabel.setPrefSize(50,50);
        groupHeadLabel.setLayoutX(15);
        groupHeadLabel.setLayoutY(10);
        groupHeadLabel.getStyleClass().add("elementFriendGroup_head");
        groupHeadLabel.setStyle("-fx-background-color: purple");
        children.add(groupHeadLabel);

        // 名称区域
        Label groupNameLabel = new Label();
        groupNameLabel.setPrefSize(200,40);
        groupNameLabel.setLayoutX(80);
        groupNameLabel.setLayoutY(15);
        groupNameLabel.setText(groupName);
        groupNameLabel.getStyleClass().add("elementFriendGroup_name");
        children.add(groupNameLabel);

    }

    public Pane pane(){
        return groupPane;
    }
}
