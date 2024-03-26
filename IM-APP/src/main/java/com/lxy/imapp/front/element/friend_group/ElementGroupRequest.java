package com.lxy.imapp.front.element.friend_group;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 * 群组加入申请
 */
public class ElementGroupRequest {
    private Pane pane;

    private Label head;
    private Label name;

    private Pane groupRequestPane; // 群组申请面板

    private ListView<Pane> groupRequestListView; // 用户列表

    private Label groupRequestRemind;

    public Label getGroupRequestRemind(){
        return groupRequestRemind;
    }
    public ElementGroupRequest(){
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
        name.setText("群组申请");
        name.getStyleClass().add("elementFriendLuck_name");
        children.add(name);

        // 消息提醒
        groupRequestRemind = new Label();

        groupRequestRemind.setPrefSize(15, 15);
        groupRequestRemind.setLayoutX(60);
        groupRequestRemind.setLayoutY(5);

        groupRequestRemind.setText("");
        groupRequestRemind.setVisible(false);
        groupRequestRemind.getStyleClass().add("element_msgRemind");

        children.add(groupRequestRemind);

        // 初始化框体区域[搜索好友框、展现框]
        groupRequestPane = new Pane();
        groupRequestPane.setPrefSize(808, 560);
        groupRequestPane.getStyleClass().add("friendLuckPane");
        ObservableList<Node> friendLuckPaneChildren = groupRequestPane.getChildren();



        // 用户列表框[初始化，未装载]
        groupRequestListView = new ListView<>();
        groupRequestListView.setId("friendLuckListView");
        groupRequestListView.setPrefSize(808, 460);
        groupRequestListView.setLayoutX(-30);
        groupRequestListView.setLayoutY(10);
        groupRequestListView.getStyleClass().add("friendLuckListView");
        friendLuckPaneChildren.add(groupRequestListView);

    }

    public Pane pane() {
        return pane;
    }

    public Pane getGroupRequestPane() {
        return groupRequestPane;
    }



    public ListView<Pane> getGroupRequestListView() {
        return groupRequestListView;
    }
}
