package com.lxy.imapp.controller;

import com.lxy.imapp.constant.FriendPaneId;
import com.lxy.imapp.constant.TalkType;
import com.lxy.imapp.data.GroupsData;
import com.lxy.imapp.data.RemindCount;
import com.lxy.imapp.data.TalkBoxData;
import com.lxy.imapp.data.TalkData;
import com.lxy.imapp.element.chat_group.ElementInfoBox;
import com.lxy.imapp.element.chat_group.ElementTalk;
import com.lxy.imapp.element.friend_group.*;
import com.lxy.imapp.util.CacheUtil;
import com.lxy.imapp.util.Ids;
import com.lxy.imapp.view.Emotion;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.*;

public class ChatController {

    @FXML
    private Button profile;

    @FXML
    private Label barChat;

    @FXML
    private Label barFriend;

    @FXML
    private Label barFavorite;

    @FXML
    private Label barSetting;

    @FXML
    private Pane chatPane;

    @FXML
    private Pane friendPane;

    @FXML
    private ListView<Pane> talkList;

    @FXML
    private ListView<Pane> friendList;

    private Map<Pane, Label> paneLabelMap;

    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }


    private Paint darkBlue = Color.web("#0099cc");

    private Paint lightBlue = Color.web("#03e9f4");

    public String userId;       // 用户ID
    public String userNickName; // 用户昵称
    public String userHead;     // 用户头像

    public ListView<Pane> userListView;

    public ListView<Pane> groupListView;

    public Pane friendGroupPane;



    public void initialize() {

       setProfilePhoto();
       setBarChatStyle();
       setBarFriendStyle();
       setBarFavoriteStyle();
       setBarSettingStyle();
       initPaneList();
       initToolBox();
       initSendHandler();

       // 初始化好友界面左侧栏
        initAddFriendLuck();

        // 初始化公众号
        addFriendSubscription();

        // 初始化群聊列表
        addFriendGroupList();
//
//        // 初始化好友列表
        addFriendUserList();
//
        testTalkList();
        testFriendList();
    }

    private void testFriendList() {
        // 群组
        addFriendGroup("5307397", "虫洞 · 技术栈(1区)", "group_1");
        addFriendGroup("5307392", "CSDN 社区专家", "group_2");
        addFriendGroup("5307399", "洗脚城VIP", "group_3");

        // 好友
        addFriendUser(false, "1000004", "哈尼克兔", "04_50");
        addFriendUser(false, "1000001", "拎包冲", "02_50");
        addFriendUser(false, "1000002", "铁锤", "03_50");
        addFriendUser(true, "1000003", "小傅哥 | bugstack.cn", "01_50");
    }


    private void initPaneList() {
        paneLabelMap = new HashMap<>();
        paneLabelMap.put(friendPane, barFriend);
        paneLabelMap.put(chatPane, barChat);
    }

    private void switchToPane(Pane pane){
        for(Pane currenPane : paneLabelMap.keySet()){
            if(currenPane != pane) {
                currenPane.setVisible(false);
            }
            else {
                currenPane.setVisible(true);
            }
        }
    }

    private void setBarSettingStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/chat.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE621';
        barSetting.setFont(Font.font(font.getFamily(), 20));
        barSetting.setText(Character.toString(unicode));
        barSetting.setTextAlignment(TextAlignment.CENTER);
    }

    private void setBarFavoriteStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/chat.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE65E';
        barFavorite.setFont(Font.font(font.getFamily(), 27));
        barFavorite.setText(Character.toString(unicode));
        barFavorite.setTextAlignment(TextAlignment.CENTER);
    }

    private void setBarFriendStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/chat.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE6E7';
        barFriend.setFont(Font.font(font.getFamily(), 27));
        barFriend.setText(Character.toString(unicode));
        barFriend.setTextAlignment(TextAlignment.CENTER);
    }

    private void setBarChatStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/chat.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE600';
        barChat.setFont(Font.font(font.getFamily(), 27));
        barChat.setText(Character.toString(unicode));
        barChat.setTextAlignment(TextAlignment.CENTER);
    }

    private void setProfilePhoto() {

        // 给Button设置背景图片
        Image image = new Image("file:src/main/resources/fxml/chat/img/dog.png");

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(50,50,true,true,true,true));

        Background background = new Background(backgroundImage);

        // 给Button设置背景
        profile.setBackground(background);
    }

    public void switchToChat(MouseEvent mouseEvent) {
        if(chatPane.isVisible())
            return;


        switchToPane(chatPane);



        barChat.setTextFill(lightBlue);
        barFriend.setTextFill(darkBlue);


    }

    public void switchToFriend(MouseEvent mouseEvent){
        if(friendPane.isVisible())
            return;

        switchToPane(friendPane);
        barFriend.setTextFill(lightBlue);
        barChat.setTextFill(darkBlue);


    }

    public void hoverChatLabel(MouseEvent mouseEvent) {
        barChat.setTextFill(lightBlue);
    }

    public void unhoverChatLabel(MouseEvent mouseEvent) {
        if(chatPane.isVisible())
            return;

        barChat.setTextFill(darkBlue);
    }

    public void hoverFriendLabel(MouseEvent mouseEvent) {
        barFriend.setTextFill(lightBlue);
    }

    public void unhoverFriendLabel(MouseEvent mouseEvent) {
        if(friendPane.isVisible())
            return;
        barFriend.setTextFill(darkBlue);
    }


    /**
     * 在talklist中查询指定id的pane
     * @param talkList
     * @param id
     * @return
     */
    Pane findItemInListView(ListView<Pane> talkList, String id){
        if(talkList.getItems().isEmpty())
            return null;
        ObservableList<Pane> items = talkList.getItems();
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getId().equals(id))
                return items.get(i);
        }
        return null;
    }

    // selected表示是否在talkList中选择该会话框
    // talkType=0表示为好友消息， talkType=1表示群组消息
    public ElementTalk addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) {

        // 判断会话框是否有该对象
        ElementTalk elementTalk = CacheUtil.talkMap.get(talkId);
        if (null != elementTalk) {

             Pane  talkPane = findItemInListView(talkList, Ids.ElementTalkId.createTalkPaneId(talkId));
            if (null == talkPane) {
                talkList.getItems().add(talkIdx, elementTalk.pane());
            }
            if (selected) {
                // 设置选中
                talkList.getSelectionModel().select(elementTalk.pane());
            }
            return elementTalk;
        }

        // 初始化对话框元素
        ElementTalk talkElement = new ElementTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);

        CacheUtil.talkMap.put(talkId, talkElement);
        // 填充到对话框
        ObservableList<Pane> items = talkList.getItems();

        Pane talkElementPane = talkElement.pane();
        if (talkIdx >= 0) {
            items.add(talkIdx, talkElementPane);  // 添加到指定位置
        } else {
            items.add(talkElementPane);           // 顺序添加
        }

        if (selected) {
            talkList.getSelectionModel().select(talkElementPane);
        }
        // 对话框元素点击事件
        talkElementPane.setOnMousePressed(event -> {
            // 填充消息栏
            fillInfoBox(talkElement, talkName);


        });

        // 鼠标事件[移入/移出]
        talkElementPane.setOnMouseEntered(event -> {

            talkElement.delete().setVisible(true);
        });

        talkElementPane.setOnMouseExited(event -> {
            talkElement.delete().setVisible(false);
        });
        // 从对话框中删除
        talkElement.delete().setOnMouseClicked(event -> {
            System.out.println("删除对话框：" + talkName);
            talkList.getItems().remove(talkElementPane);
            talkElement.clearMsgSketch();
        });

        return talkElement;
    }

    private void testTalkList() {

        setUserInfo("1000001", "拎包冲", "02_50");

        // 好友 - 对话框
        addTalkBox(-1, 0, "1000002", "铁锤", "03_50", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), false);
        addTalkMsgUserLeft("1000002", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), true, false, true);
        addTalkMsgRight("1000002", "我Q，传说中的老头杀？", new Date(), true, true, false);

        addTalkBox(-1, 0, "1000004", "哈尼克兔", "04_50", null, null, true);
        addTalkMsgUserLeft("1000004", "沉淀、分享、成长，让自己和他人都有所收获！", new Date(), true, true, true);
        addTalkMsgRight("1000004", "今年过年是放假时间最长的了！", new Date(), true, true, false);

        addTalkMsgUserLeft("1000002", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), true, false, true);
        addTalkMsgUserLeft("1000002", "我是狗", new Date(), true, false, true);


        // 群组 - 对话框
        addTalkBox(0, 1, "5307397", "虫洞 · 技术栈(1区)", "group_1", "", new Date(), false);

        addTalkMsgRight("5307397", "你炸了我的山", new Date(), true, true, false);
        addTalkMsgGroupLeft("5307397", "1000002", "拎包冲", "01_50", "推我过忘川", new Date(), true, false, true);
        addTalkMsgGroupLeft("5307397", "1000003", "铁锤", "03_50", "奈河桥边的姑娘", new Date(), true, false, true);
        addTalkMsgGroupLeft("5307397", "1000004", "哈尼克兔", "04_50", "等我回头看", new Date(), true, false, true);
    }

    public void setUserInfo(String userId, String userNickName, String userHead) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;

        // TODO ：设置头像
        //button.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
    }

    public void addTalkMsgUserLeft(String talkId, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.infoBoxList();
        TalkData talkUserData = (TalkData) listView.getUserData();
        // 创建pane
        Pane left = new ElementInfoBox().left(talkUserData.getTalkName(), talkUserData.getTalkHead(), msg);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);

        // 设置会话栏里面的消息
        talkElement.fillMsgSketch(msg, msgDate);

        // 将对话框向上移动，并且指定是否选中
        updateTalkListIdxAndSelected(TalkType.PRIVATE_MESSAGE.getTalkTypeCode(), talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);

        // 如果选中该对话，才会进行填充
        if(selected)
        fillInfoBox(talkElement, talkUserData.getTalkName());
    }

    @FXML
    private Label infoName;

    @FXML
    private Pane infoPaneBox;


    // 填充talkElement到infoBox
    private void fillInfoBox(ElementTalk talkElement, String talkName) {


        String talkId = talkElement.pane().getId();

        // 填充对话列表
        String boxUserId = (String) infoPaneBox.getUserData();
        // 判断是否已经填充[talkId]，当前已填充则返回
        if (talkId.equals(boxUserId))
            return;

        ListView<Pane> listView = talkElement.infoBoxList();
        listView.setPrefWidth(800);

        infoPaneBox.setUserData(talkId);
        infoPaneBox.getChildren().clear();
        infoPaneBox.getChildren().add(listView);

        // 对话框名称
        infoName.setText(talkName);

        // 清除通知
        clearRemind(talkElement.msgRemind());
    }

    public void addTalkMsgRight(String talkId, String msg, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.infoBoxList();
        Pane right = new ElementInfoBox().right(userNickName, userHead, msg);
        // 消息填充
        listView.getItems().add(right);
        // 滚动条
        listView.scrollTo(right);
        talkElement.fillMsgSketch(msg, msgData);

        updateTalkListIdxAndSelected(1, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);

    }

    void updateTalkListIdxAndSelected(int talkType, Pane talkElementPane, Label msgRemindLabel, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        // 对话框ID、好友ID
        TalkBoxData talkBoxData = (TalkBoxData) talkElementPane.getUserData();
        // 填充到对话框
        // 对话空为空，初始化[置顶、选中、提醒]
        if (talkList.getItems().isEmpty()) {
            if (idxFirst) {
                talkList.getItems().add(0, talkElementPane);
            }
            if (selected) {
                // 设置对话框[√选中]
                talkList.getSelectionModel().select(talkElementPane);
            }

            isRemind(msgRemindLabel, talkType, isRemind);
            return;
        }
        // 对话框不为空，判断第一个元素是否当前聊天Pane
        Pane firstPane = talkList.getItems().get(0);
        // 判断元素是否在首位，如果是首位可返回不需要重新设置首位
        if (talkBoxData.getTalkId().equals(((TalkBoxData) firstPane.getUserData()).getTalkId())) {

            // 选中判断；如果第一个元素已经选中[说明正在会话]，那么清空消息提醒
            Pane selectedItem = talkList.getSelectionModel().getSelectedItem();
            // 选中判断；如果第一个元素已经选中[说明正在会话]，那么清空消息提醒
            if (null == selectedItem){
                isRemind(msgRemindLabel, talkType, isRemind);
                return;
            }

            TalkBoxData selectedItemUserData = (TalkBoxData) selectedItem.getUserData();
            if (null != selectedItemUserData && talkBoxData.getTalkId().equals(selectedItemUserData.getTalkId())) {
                clearRemind(msgRemindLabel);
            } else {
                isRemind(msgRemindLabel, talkType, isRemind);
            }
            return;
        }

        if (idxFirst) {
            talkList.getItems().remove(talkElementPane);
            talkList.getItems().add(0, talkElementPane);
        }
        if (selected) {
            // 设置对话框[√选中]
            talkList.getSelectionModel().select(talkElementPane);
        }
        isRemind(msgRemindLabel, talkType, isRemind);
    }

    private void clearRemind(Label msgRemindLabel) {
        msgRemindLabel.setVisible(false);
        msgRemindLabel.setUserData(new RemindCount(0));
    }
    private void isRemind(Label msgRemindLabel, int talkType, Boolean isRemind) {
        if (!isRemind) return;
        msgRemindLabel.setVisible(true);
        // 群组直接展示小红点
        if (TalkType.GROUP_MESSAGE.getTalkTypeCode() == talkType) {
            return;
        }
        RemindCount remindCount = (RemindCount) msgRemindLabel.getUserData();
        // 超过10个展示省略号
        if (remindCount.getCount() > 99) {
            msgRemindLabel.setText("···");
            return;
        }
        int count = remindCount.getCount() + 1;
        msgRemindLabel.setUserData(new RemindCount(count));
        msgRemindLabel.setText(String.valueOf(count));
    }


    @FXML
    private TextArea txtInput;

    @FXML
    private Label touchSend;

    private void initSendHandler(){
        touchSend.setOnMouseClicked(event -> {
            sendMessage();
        });

        txtInput.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                sendMessage();
            }
        });
    }

    private void sendMessage(){
        MultipleSelectionModel<Pane> selectionModel = talkList.getSelectionModel();
        Pane selectedItem = selectionModel.getSelectedItem();

        // 对话信息
        TalkBoxData  talkBoxData = (TalkBoxData) selectedItem.getUserData();


        String msg = txtInput.getText();

        if(null == msg || "".equals(msg) || "".equals(msg.trim()))
            return;

        Date msgDate = new Date();
        System.out.println("发送消息:"+msg);

        addTalkMsgRight(talkBoxData.getTalkId(), msg, msgDate, true, true, false);
        txtInput.clear();
    }

    // selected为true，则表示选中该会话框，并且将会话填充到infoBox中
    public void addTalkMsgGroupLeft(String talkId, String userId, String userNickName, String userHead, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        // 如果时自己发送的消息，则抛弃
        if (this.userId.equals(userId))
            return;

        GroupsData groupsData = null;
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        if (null == talkElement) {
            Pane itemInListView = findItemInListView(talkList, Ids.ElementTalkId.createFriendGroupId(talkId));
            if(itemInListView == null)
                return;
            groupsData = (GroupsData)itemInListView.getUserData();
            if (groupsData == null)
                return;
            addTalkBox(0, 1, talkId, groupsData.getGroupName(), groupsData.getGroupHead(), userNickName + "：" + msg, msgDate, false);
            talkElement = CacheUtil.talkMap.get(talkId);
            // 事件通知(开启与群组发送消息)
            System.out.println("填充到聊天窗口[群组] groupId：" + groupsData.getGroupId());
        }
        ListView<Pane> listView = talkElement.infoBoxList();
        Pane left = new ElementInfoBox().left(userNickName, userHead, msg);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);
        talkElement.fillMsgSketch( userNickName + "：" + msg, msgDate);
        // 设置位置&选中
        updateTalkListIdxAndSelected(TalkType.GROUP_MESSAGE.getTalkTypeCode(), talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);

        if(selected && groupsData!=null)
            fillInfoBox(talkElement, groupsData.getGroupName());

    }


    public void minizeWindow(MouseEvent mouseEvent) {
        stage.setIconified(true);
    }

    public void closeWindow(MouseEvent mouseEvent){
        stage.close();
        System.exit(0);
    }

    // ----------------------以下均为好友面板---------------------------

    // 添加新的好友
    public void initAddFriendLuck(){
        ObservableList<Pane> items = friendList.getItems();

        // 设置新的朋友面板
        ElementFriendTag elementFriendTag = new ElementFriendTag("新的朋友");
        items.add(elementFriendTag.pane());

        ElementFriendLuck element = new ElementFriendLuck();
        Pane pane = element.pane();
        items.add(pane);

        pane.setOnMousePressed(event -> {
            Pane friendLuckPane = element.friendLuckPane();
            setContentPaneBox(FriendPaneId.NEW_FRIEND_PANE_ID.getId(), "新的朋友", friendLuckPane);
            clearViewListSelectedAll(userListView, groupListView);
            ListView<Pane> paneListView = element.friendLuckListView();
            paneListView.getItems().clear();
            System.out.println("添加好友");

        });

        // 搜索框事件
        TextField friendLuckSearch = element.friendLuckSearch();

        friendLuckSearch.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                String text = friendLuckSearch.getText();
                if(text == null)
                    text = "";

                text = text.trim();
                if(text.length() > 30)
                    text = text.substring(0, 30);

                System.out.println("搜搜好友:" + text);

                element.friendLuckListView().getItems().clear();
                // 添加朋友
                element.friendLuckListView().getItems().add(new ElementFriendLuckUser("100005", "比丘卡", "05_50",0).pane());

                element.friendLuckListView().getItems().add(new ElementFriendLuckUser("1000006", "兰兰", "05_50",0).pane());

                element.friendLuckListView().getItems().add(new ElementFriendLuckUser("100007","Alexa", "07_50", 2).pane());


            }
        });


    }


    // 添加公众号
    public void addFriendSubscription(){
        ObservableList<Pane> items = friendList.getItems();
        ElementFriendTag elementFriendTag = new ElementFriendTag("公众号");
        items.add(elementFriendTag.pane());

        ElementFriendSubscription elementFriendSubscription = new ElementFriendSubscription();
        Pane pane = elementFriendSubscription.pane();
        items.add(pane);


        pane.setOnMousePressed(event -> {
            clearViewListSelectedAll(userListView, groupListView);
            Pane subPane = elementFriendSubscription.subPane();
            setContentPaneBox(FriendPaneId.SUBSCRIBE_PANE_ID.getId(), "公众号", subPane);
        });


    }

    public void clearViewListSelectedAll(ListView<Pane>... listViews) {
        for (ListView<Pane> listView : listViews) {
            if(listView != null)
            listView.getSelectionModel().clearSelection();
        }
    }

    public void addFriendGroupList(){
        ObservableList<Pane> items = friendList.getItems();
        ElementFriendTag elementFriendTag = new ElementFriendTag("群聊");
        items.add(elementFriendTag.pane());

        ElementFriendGroupList elementFriendGroupList = new ElementFriendGroupList();
        groupListView = elementFriendGroupList.getGroupListView();
        Pane pane = elementFriendGroupList.pane();
        friendGroupPane = pane;
        items.add(pane);

    }

    public void addFriendUserList(){
        ObservableList<Pane> items = friendList.getItems();

        ElementFriendTag elementFriendTag = new ElementFriendTag("好友");
        items.add(elementFriendTag.pane());

        ElementFriendUserList elementFriendUserList = new ElementFriendUserList();
        Pane pane = elementFriendUserList.pane();
        userListView = elementFriendUserList.getUserListView();
        friendUserListPane = pane;
        items.add(pane);

    }

    public void addFriendGroup(String groupId, String groupName, String groupHead) {
        ElementFriendGroup elementFriendGroup = new ElementFriendGroup(groupId, groupName, groupHead);
        Pane pane = elementFriendGroup.pane();
        if(groupListView == null) {
            System.out.println("groupListView is Null!");
            return;
        }
        // 添加到群组列表
        ObservableList<Pane> items = groupListView.getItems();
        items.add(pane);
        groupListView.setPrefHeight(80 * items.size());

        // 群组信息面板，点击群组时，右侧会显现
        Pane detailContent = new Pane();
        detailContent.setPrefSize(808,560);
        detailContent.getStyleClass().add("friendGroupDetailContent");
        ObservableList<Node> children = detailContent.getChildren();

        Button sendMsgButton = new Button();
        sendMsgButton.setId(groupId);
        sendMsgButton.getStyleClass().add("friendGroupSendMsgButton");
        sendMsgButton.setPrefSize(176,50);
        sendMsgButton.setLayoutX(316);
        sendMsgButton.setLayoutY(450);
        sendMsgButton.setText("发送消息");
        children.add(sendMsgButton);

        if(friendGroupPane != null)
        friendGroupPane.setPrefHeight(80 * items.size());

        doEventOpenFriendGroupSendMsg(sendMsgButton, groupId, groupName, groupHead, 1);
        // 添加监听事件
        pane.setOnMousePressed(event -> {
            clearViewListSelectedAll(friendList, userListView);
            setContentPaneBox(groupId, groupName, detailContent);
        });
    }

    private Pane friendUserListPane;

    public void addFriendUser(boolean selected, String userFriendId, String userFriendNickName, String userFriendHead) {
        ElementFriendUser friendUser = new ElementFriendUser(userFriendId, userFriendNickName, userFriendHead);
        Pane pane = friendUser.pane();

        ObservableList<Pane> items = null;
        // 添加到好友列表
        if(userListView == null)
            return;

        // 好友，内容框[初始化，未装载]，承载好友信息内容，点击按钮时候填充
        Pane detailContent = new Pane();
        detailContent.setPrefSize(808, 560);
        detailContent.getStyleClass().add("friendUserDetailContent");
        ObservableList<Node> children = detailContent.getChildren();

        Button sendMsgButton = new Button();
        sendMsgButton.setId(userFriendId);
        sendMsgButton.getStyleClass().add("friendUserSendMsgButton");
        sendMsgButton.setPrefSize(176, 50);
        sendMsgButton.setLayoutX(316);
        sendMsgButton.setLayoutY(450);
        sendMsgButton.setText("发送消息");
        children.add(sendMsgButton);

        items = userListView.getItems();
        items.add(pane);
        userListView.setPrefHeight(80 * items.size());
        friendUserListPane.setPrefHeight(80 * items.size());
        // 选中
        if (selected) {
            userListView.getSelectionModel().select(pane);
        }

        doEventOpenFriendGroupSendMsg(sendMsgButton, userFriendId, userFriendNickName, userFriendHead, 0);
        // 添加监听事件
        pane.setOnMousePressed(event -> {
            clearViewListSelectedAll(friendList, groupListView);
            setContentPaneBox(userFriendId, userFriendNickName,detailContent);
        });
    }


    @FXML
    private Pane contentPaneBox;

    @FXML
    private Label contentName;
    void setContentPaneBox(String id, String name, Node node) {
        // 填充对话列表
        contentPaneBox.setUserData(id);
        contentPaneBox.getChildren().clear();
        contentPaneBox.getChildren().add(node);

        // 设置对话框名称
        contentName.setText(name);
    }

    public void doEventOpenFriendGroupSendMsg(Button sendMsgButton, String groupId, String groupName, String groupHead, Integer talkType) {
        sendMsgButton.setOnMouseClicked(event -> {
            // 1. 添加好友到对话框
            ElementTalk elementTalk = addTalkBox(0, talkType, groupId, groupName, groupHead, null, null, true);
            // 2. 切换到对话框窗口
            switchToChat(event);
            fillInfoBox(elementTalk, groupName);
            // 3. 事件处理；填充到对话框
            System.out.println("事件处理；填充到对话框");
        });
    }


    @FXML
    private Button emotionButton;

    private void registerEmotionHandler() {
        Emotion emotionStage = new Emotion();
        emotionButton.setOnMousePressed(event -> {
            emotionStage.doShowFace(stage.getX() + 314 + 74 , stage.getY() + 618- 170);
        });
    }

    public void initToolBox(){
        registerEmotionHandler();
    }

}




