package com.lxy.imapp.element.friend_group;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElementFriendLuck {
    private Pane pane;

    private Label head;
    private Label name;

    private Pane friendLuckPane; // 用户面板
    private TextField friendLuckSearch; // 用户搜索
    private ListView<Pane> friendLuckListView; // 用户列表

    public ElementFriendLuck(){
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
        friendLuckPane = new Pane();
        friendLuckPane.setPrefSize(808, 560);
        friendLuckPane.getStyleClass().add("friendLuckPane");
        ObservableList<Node> friendLuckPaneChildren = friendLuckPane.getChildren();

        friendLuckSearch = new TextField();
        friendLuckSearch.setPrefSize(600, 50);
        friendLuckSearch.setLayoutX(100);
        friendLuckSearch.setLayoutY(25);
        friendLuckSearch.setPromptText("搜索");
        friendLuckSearch.setPadding(new Insets(10));
        friendLuckSearch.getStyleClass().add("friendLuckSearch");
        friendLuckPaneChildren.add(friendLuckSearch);

        // 用户列表框[初始化，未装载]
        friendLuckListView = new ListView<>();
        friendLuckListView.setId("friendLuckListView");
        friendLuckListView.setPrefSize(808, 460);
        friendLuckListView.setLayoutX(-30);
        friendLuckListView.setLayoutY(75);
        friendLuckListView.getStyleClass().add("friendLuckListView");
        friendLuckPaneChildren.add(friendLuckListView);

    }

    public Pane pane() {
        return pane;
    }

    public Pane friendLuckPane() {
        return friendLuckPane;
    }

    public TextField friendLuckSearch() {
        return friendLuckSearch;
    }

    public ListView<Pane> friendLuckListView() {
        return friendLuckListView;
    }

}
