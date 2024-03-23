package com.lxy.imapp.front.element.friend_group;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElementNewFriend {
    private Pane pane;

    private Label head;
    private Label name;

    private Pane newFriendPane; // 用户面板

    private ListView<Pane> newFriendListView; // 用户列表

    public ElementNewFriend(){
        pane = new Pane();
        pane.setId("elementFriendLuck");
        pane.setPrefSize(270,70);
        pane.getStyleClass().add("elementFriendLuck");
        ObservableList<Node> children = pane.getChildren();

        // 头像区域
        head = new Label();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(10);

        head.getStyleClass().add("elementFriendLuck_head");
        children.add(head);

        // 名称区域
        name = new Label();
        name.setPrefSize(200, 40);
        name.setLayoutX(80);
        name.setLayoutY(15);
        name.setText("新的朋友");
        name.getStyleClass().add("elementFriendLuck_name");
        children.add(name);
        
        // 初始化框体区域[搜索好友框、展现框]
        newFriendPane = new Pane();
        newFriendPane.setPrefSize(808, 560);
        newFriendPane.getStyleClass().add("friendLuckPane");
        ObservableList<Node> friendLuckPaneChildren = newFriendPane.getChildren();



        // 用户列表框[初始化，未装载]
        newFriendListView = new ListView<>();
        newFriendListView.setId("friendLuckListView");
        newFriendListView.setPrefSize(808, 460);
        newFriendListView.setLayoutX(-30);
        newFriendListView.setLayoutY(10);
        newFriendListView.getStyleClass().add("friendLuckListView");
        friendLuckPaneChildren.add(newFriendListView);

    }

    public Pane pane() {
        return pane;
    }

    public Pane getNewFriendPane() {
        return newFriendPane;
    }



    public ListView<Pane> newFriendListView() {
        return newFriendListView;
    }

}
