package com.lxy.imapp.front.element.chat_group;

import com.lxy.imapp.front.data.RemindCount;
import com.lxy.imapp.front.data.TalkBoxData;
import com.lxy.imapp.front.data.TalkData;
import com.lxy.imapp.front.util.DateUtil;
import com.lxy.imapp.front.util.Ids;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Date;

// 代表会话栏里面的一次会话
public class ElementTalk {
    private Pane pane;

    private Label head;
    private Label nikeName; // 昵称区域
    private Label msgSketch; // 信息简述
    private Label msgData; // 信息时间
    private Label msgRemind; // 消息提醒
    private Button delete; // 删除对话框按钮

    private ListView<Pane> infoBoxList; // 初始化填充消息对话框

    public ElementTalk(String talkId, Integer talkType, String talkName, String talkHead, String talkSketch, Date talkDate){
        pane = new Pane();

        // 设置会话id，每个会话都有唯一的一个id
        pane.setId(Ids.ElementTalkId.createTalkPaneId(talkId));

        // 设置会话对象的属性
        pane.setUserData(new TalkBoxData(talkId, talkType, talkName, talkHead));

        // 设置会话窗口的大小
        pane.setPrefSize(270, 80);
        pane.getStyleClass().add("talkElement");

        ObservableList<Node> children = pane.getChildren();

        // 头像
        head = new Label();
        head.setPrefSize(50,50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("element_head");
        head.setStyle("-fx-background-color:red");
        children.add(head);

        // 昵称
        nikeName = new Label();
        nikeName.setPrefSize(140,25);
        nikeName.setLayoutX(80);
        nikeName.setLayoutY(15);
        nikeName.setText(talkName);
        nikeName.getStyleClass().add("element_nikeName");
        children.add(nikeName);

        // 最近的一次消息
        msgSketch = new Label();
        msgSketch.setId(Ids.ElementTalkId.createMsgSketchId(talkId));
        msgSketch.setPrefSize(200, 25);
        msgSketch.setLayoutX(80);
        msgSketch.setLayoutY(40);
        msgSketch.getStyleClass().add("element_msgSketch");
        children.add(msgSketch);

        // 信息时间
        msgData = new Label();
        msgData.setId(Ids.ElementTalkId.createMsgDataId(talkId));
        msgData.setPrefSize(60, 25);
        msgData.setLayoutX(220);
        msgData.setLayoutY(15);
        msgData.getStyleClass().add("element_msgData");
        children.add(msgData);

        // 填充 最近的一次消息和时间
        fillMsgSketch(talkSketch, talkDate);

        // 消息提醒
        msgRemind = new Label();

        msgRemind.setPrefSize(15, 15);
        msgRemind.setLayoutX(60);
        msgRemind.setLayoutY(5);
        msgRemind.setUserData(new RemindCount());

        msgRemind.setText("");
        msgRemind.setVisible(false);
        msgRemind.getStyleClass().add("element_msgRemind");

        children.add(msgRemind);

        // 删除对话框按钮
        delete = new Button();
        delete.setVisible(false);
        delete.setLayoutY(26);
        delete.setLayoutX(-8);
        delete.getStyleClass().add("element_delete");

        // 给delete-button设置背景图片
        Image image = new Image("file:src/main/resources/fxml/chat/img/talk_delete.png");

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        Background background = new Background(backgroundImage);

        delete.setBackground(background);
        children.add(delete);

        // 会话对应的具体消息框[初始化，未装载］，承载对话信息内容，点击按钮时候填充
        infoBoxList = new ListView<>();
        infoBoxList.setId(Ids.ElementTalkId.createInfoBoxListId(talkId));
        infoBoxList.setUserData(new TalkData(talkName , talkHead));
        infoBoxList.setPrefSize(850, 500);
        infoBoxList.getStyleClass().add("infoBoxStyle");

    }

    public void fillMsgSketch(String talkSketch, Date talkDate) {
        if (null != talkSketch) {
            if (talkSketch.length() > 30) talkSketch = talkSketch.substring(0, 30);
            msgSketch.setText(talkSketch);
        }
        if (null == talkDate) talkDate = new Date();
        // 格式化信息
        String talkSimpleDate = DateUtil.simpleDate(talkDate);
        msgData.setText(talkSimpleDate);
    }

    public void clearMsgSketch() {
        msgSketch.setText("");
    }

    public Label msgRemind() {
        return msgRemind;
    }

    public Pane pane() {
        return pane;
    }

    public ListView<Pane> infoBoxList() {
        return infoBoxList;
    }

    public Button delete() {
        return delete;
    }
}
