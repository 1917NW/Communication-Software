package com.lxy.imapp.front.element.friend_group;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

/**
 * 加入群组 面板
 */
public class ElementJoinGroup {

    private Pane pane;

    private Label head;  // 头像
    private Label name;  // 名称

    private Pane groupPane; // 群组面板
    private TextField groupSearch; // 群组搜索
    private ListView<Pane> groupListView; // 群组列表

    public ElementJoinGroup(){
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
        name.setText("加入群组");
        name.getStyleClass().add("elementFriendSubscription_name");
        children.add(name);

        // 初始化框体区域[搜索好友框、展现框]
        groupPane = new Pane();
        groupPane.setPrefSize(808, 560);
        groupPane.getStyleClass().add("friendLuckPane");
        ObservableList<Node> friendLuckPaneChildren = groupPane.getChildren();

        groupSearch = new TextField();
        groupSearch.setPrefSize(600, 50);
        groupSearch.setLayoutX(100);
        groupSearch.setLayoutY(25);
        groupSearch.setPromptText("搜索群组");
        groupSearch.setPadding(new Insets(10));
        groupSearch.getStyleClass().add("friendLuckSearch");
        friendLuckPaneChildren.add(groupSearch);

        // 用户列表框[初始化，未装载]
        groupListView = new ListView<>();
        groupListView.setId("friendLuckListView");
        groupListView.setPrefSize(808, 460);
        groupListView.setLayoutX(-30);
        groupListView.setLayoutY(75);
        groupListView.getStyleClass().add("friendLuckListView");
        friendLuckPaneChildren.add(groupListView);

    }

    public Pane pane() {
        return pane;
    }

    public Pane subPane() {
        return groupPane;
    }

    public TextField getGroupSearch() {
        return groupSearch;
    }

    public ListView<Pane> getGroupListView() {
        return groupListView;
    }
}
