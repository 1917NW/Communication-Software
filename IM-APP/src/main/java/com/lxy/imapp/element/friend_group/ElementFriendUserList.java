package com.lxy.imapp.element.friend_group;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class ElementFriendUserList {

    private Pane pane;
    private ListView<Pane> userListView; // 好友列表

    public ElementFriendUserList(){
        pane = new Pane();
        pane.setId("friendUserList");
        pane.setPrefWidth(314);
        pane.setPrefHeight(0);// 自动计算；userListView.setPrefHeight(70 * items.size() + 10);
        pane.setLayoutX(-10);
        pane.getStyleClass().add("elementFriendUserList");
        ObservableList<Node> children = pane.getChildren();

        userListView = new ListView<>();
        userListView.setId("userListView");
        userListView.setPrefWidth(314);
        userListView.setPrefHeight(0); // 自动计算；userListView.setPrefHeight(70 * items.size() + 10);
        userListView.setLayoutX(-10);
        userListView.getStyleClass().add("elementFriendUser_listView");
        children.add(userListView);
    }

    public Pane pane() {
        return pane;
    }

    public ListView<Pane> getUserListView(){
        return userListView;
    }
}