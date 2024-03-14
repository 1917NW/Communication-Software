package com.lxy.imapp.element.friend_group;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class ElementFriendGroupList {
    private Pane pane;
    private ListView<Pane> groupListView;

    public ElementFriendGroupList(){
        pane = new Pane();
        pane.setId("friendGroupList");
        pane.setPrefWidth(314);
        pane.setPrefWidth(0); 
        pane.setLayoutX(-10);
        pane.getStyleClass().add("elementFriendGroupList");
        ObservableList<Node> children = pane.getChildren();

        groupListView = new ListView<>();
        groupListView.setId("groupListView");
        groupListView.setPrefWidth(314);
        groupListView.setPrefHeight(0);
        groupListView.setLayoutX(-10);
        groupListView.getStyleClass().add("elementFriendGroupList_listView");
        children.add(groupListView);

    }

    public Pane pane(){
        return pane;
    }

    public ListView<Pane> getGroupListView(){
        return groupListView;
    }
}
