package com.lxy.imapp.front.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.lxy.imapp.biz.event.ChatEventHandler;
import com.lxy.imapp.biz.file.ChatRecordMap;
import com.lxy.imapp.biz.file.FileManager;
import com.lxy.imapp.biz.socket.po.NewFriendRequest;
import com.lxy.imapp.biz.socket.po.UserGroupRequest;
import com.lxy.imapp.biz.source.impl.FileDataSource;
import com.lxy.imapp.biz.threadPool.TaskExecutor;
import com.lxy.imapp.biz.util.BeanUtil;
import com.lxy.imapp.front.constant.FriendPaneId;
import com.lxy.imapp.front.constant.ImFileConstants;
import com.lxy.imapp.front.data.GroupsData;
import com.lxy.imapp.front.data.RemindCount;
import com.lxy.imapp.front.data.TalkBoxData;
import com.lxy.imapp.front.data.TalkData;
import com.lxy.imapp.front.element.chat_group.ElementInfoBox;
import com.lxy.imapp.front.element.chat_group.ElementTalk;
import com.lxy.imapp.front.element.friend_group.*;
import com.lxy.imapp.front.util.CacheUtil;
import com.lxy.imapp.front.util.Ids;
import com.lxy.imapp.front.view.Chat;
import com.lxy.imapp.front.view.Emotion;
import com.lxy.protocolpackage.constants.*;

import com.lxy.protocolpackage.dto.ChatRecordDto;
import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.dto.UserDto;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ChatController {

    private ChatEventHandler chatEventHandler;

    public void setChatEventHandler(ChatEventHandler chatEventHandler){
        this.chatEventHandler = chatEventHandler;
    }

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

    private Chat stage;

    public void setStage(Chat stage){
        this.stage = stage;
    }


    private Paint darkBlue = Color.web("#0099cc");

    private Paint whiteBlue = Color.web("#99cccc");

    private Paint lightBlue = Color.web("#03e9f4");

    public String currentUserId;       // 用户ID
    public String currentUserNickName; // 用户昵称
    public String currentUserHead;     // 用户头像

    public ListView<Pane> userListView;

    public ListView<Pane> groupListView;

    public Pane friendGroupPane;



    public void initialize() {
        // 设置用户头像
       setProfilePhoto();


       setBarChatStyle();
       setBarFriendStyle();
       setBarFavoriteStyle();
       setBarSettingStyle();
       initPaneList();
       initToolBox();
       initSendHandler();
       setLogo();


        // 初始化新的好友
        initNewFriend();

       // 初始化好友界面左侧栏
        initAddFriendLuck();



        // 群组申请通知
        addGroupRequest();

        // 初始化创建群组
        addCreateGroup();

        // 初始化加入群组
        addJoinGroup();



        // 初始化群聊列表
        addFriendGroupList();
//
//        // 初始化好友列表
        addFriendUserList();
//
//        testTalkList();
//        testFriendList();

        initBackGround();
    }

    private ElementGroupRequest elementGroupRequest;

    private ListView<Pane> groupRequestListView;
    private void addGroupRequest() {
        ObservableList<Pane> items = friendList.getItems();

        // 设置新的好友面板
        ElementFriendTag elementFriendTag = new ElementFriendTag("群组申请");
        items.add(elementFriendTag.pane());

        elementGroupRequest = new ElementGroupRequest();

        Pane pane = elementGroupRequest.pane();
        items.add(pane);
        groupRequestListView = elementGroupRequest.getGroupRequestListView();
        pane.setOnMousePressed(event -> {
            Pane groupRequestPane = elementGroupRequest.getGroupRequestPane();
            setContentPaneBox(FriendPaneId.NEW_FRIEND_PANE_ID.getId(), "群组申请", groupRequestPane);
            clearViewListSelectedAll(userListView, groupListView);
            System.out.println("群组申请");
            elementGroupRequest.getGroupRequestRemind().setVisible(false);
        });

    }

    private void initBackGround() {
        if(talkList.getSelectionModel().getSelectedItem() == null){
            setValid(false);
        }
    }

    private void testFriendList() {
        // 群组
        addFriendGroup("5307392", "NK-F4", "group_2");
        addFriendGroup("5307399", "天启四骑士", "group_3");

        // 好友
        addFriendUser(false, "1000004", "哈尼克兔", "04_50");
        addFriendUser(false, "1000001", "拎包冲", "02_50");
        addFriendUser(false, "1000002", "铁锤", "03_50");

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
        ImageView imageView = new ImageView(image);

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(50,50,true,true,true,true));


        Background background = new Background(backgroundImage);

//         给Button设置背景
        profile.setBackground(background);
    }

    public void switchToChat(MouseEvent mouseEvent) {
        if(chatPane.isVisible())
            return;


        switchToPane(chatPane);



        barChat.setTextFill(lightBlue);
        barFriend.setTextFill(whiteBlue);


    }

    public void switchToFriend(MouseEvent mouseEvent){
        if(friendPane.isVisible())
            return;

        switchToPane(friendPane);
        barFriend.setTextFill(lightBlue);
        barChat.setTextFill(whiteBlue);


    }

    public void hoverChatLabel(MouseEvent mouseEvent) {
        barChat.setTextFill(lightBlue);
    }

    public void unhoverChatLabel(MouseEvent mouseEvent) {
        if(chatPane.isVisible())
            return;

        barChat.setTextFill(whiteBlue);
    }

    public void hoverFriendLabel(MouseEvent mouseEvent) {
        if(barFriend.getTextFill().equals(Color.YELLOW))
            return;
        barFriend.setTextFill(lightBlue);
    }

    public void unhoverFriendLabel(MouseEvent mouseEvent) {
        if(barFriend.getTextFill().equals(Color.YELLOW))
            return;

        if(friendPane.isVisible())
            return;
        barFriend.setTextFill(whiteBlue);
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
            //
            setValid(true);

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

            talkList.getItems().remove(talkElementPane);
            talkElement.clearMsgSketch();
            setValid(false);
            talkList.getSelectionModel().clearSelection();

            // 发送删除对话消息
            chatEventHandler.doEventDeleteTalk(currentUserId, talkId, talkType);

        });

        return talkElement;
    }



    public void setUserInfo(String userId, String userNickName, String userHead) {
        this.currentUserId = userId;
        this.currentUserNickName = userNickName;
        this.currentUserHead = userHead;

        BeanUtil.addBean("userId", currentUserId);
        BeanUtil.addBean("userNickName", currentUserNickName);
        BeanUtil.addBean("userHead", currentUserHead);

        // TODO ：设置头像
        //button.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
    }

    public void addTalkMsgUserLeft(UserDto userDto, String msg, Integer msgType, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        String talkId = userDto.getUserId();
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        if(talkElement == null){
            // 初始化对话框元素
             talkElement = addTalkBox(0, TalkType.PRIVATE_MESSAGE.getTalkTypeCode(), talkId, userDto.getUserNickName(), userDto.getUserHead(), msg, msgDate, false);

             CacheUtil.talkMap.put(talkId, talkElement);
        }

        ListView<Pane> listView = talkElement.infoBoxList();
        TalkData talkUserData = (TalkData) listView.getUserData();

        // 创建消息pane
        Pane left = new ElementInfoBox().left(talkId, talkUserData.getTalkName(), talkUserData.getTalkHead(), msg, msgType, stage);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);

        // 设置会话栏里面的消息
        if(MsgType.TEXT_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch(msg, msgDate);
        if(MsgType.EMOTION_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch("[表情]", msgDate);
        if(MsgType.FILE_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch("[文件]"+msg, msgDate);

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

        TalkBoxData userData = (TalkBoxData) talkElement.pane().getUserData();

        listView.setPrefWidth(808);
        listView.setPrefHeight(542);

        infoPaneBox.setUserData(talkId);
        infoPaneBox.getChildren().clear();
        infoPaneBox.getChildren().add(listView);

        // 对话框名称
        infoName.setText(talkName);



        // 清除通知
        clearRemind(talkElement.msgRemind());

        if(listView.getItems().isEmpty()){


            List<ChatRecordDto> chatRecordList = ChatRecordMap.getChatRecordDtoListById(talkId);

            if(chatRecordList == null || chatRecordList.isEmpty()){
                return;
            }

            if(userData.getTalkType().equals(TalkType.PRIVATE_MESSAGE.getTalkTypeCode())){

                UserDto userDto = new UserDto();
                userDto.setUserId(userData.getTalkId());
                userDto.setUserHead(userData.getTalkHead());
                userDto.setUserNickName(userData.getTalkName());



                chatRecordList.forEach(chatRecord ->{
                    // 如果是自己发送的消息
                    if(chatRecord.getMsgUserType().equals(MsgUserType.MINE_MSG.getMsgTypeCode())){
                        addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(),  MsgType.TEXT_MSG.getMsgTypeCode(),chatRecord.getMsgDate(), true,false, false);
                    }else if(chatRecord.getMsgUserType().equals(MsgUserType.OTHERS_MSG.getMsgTypeCode())){
                        addTalkMsgUserLeft(userDto, chatRecord.getMsgContent(),  chatRecord.getMsgType(),chatRecord.getMsgDate(), true,false, false);
                    }
                } );

            }
            else if(userData.getTalkType().equals(TalkType.GROUP_MESSAGE.getTalkTypeCode())){
                // 如果为空，则直接返回


                chatRecordList.forEach(chatRecord ->{
                    // 如果是自己发送的消息
                    if(chatRecord.getMsgType().equals(MsgUserType.MINE_MSG.getMsgTypeCode())){
                        addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), MsgType.TEXT_MSG.getMsgTypeCode(), chatRecord.getMsgDate(), true,false, false);
                    }else if(chatRecord.getMsgType().equals(MsgUserType.OTHERS_MSG.getMsgTypeCode())){
                        addTalkMsgGroupLeft(chatRecord.getTalkId(), userData.getTalkName(), userData.getTalkHead(), chatRecord.getUserId(), chatRecord.getUserNickName(), chatRecord.getUserHead(), chatRecord.getMsgContent(),chatRecord.getMsgType(),chatRecord.getMsgDate(),true,false, false);
                    }
                } );
            }
        }


    }

    public void addTalkMsgRight(String talkId, String msg, Integer msgType, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.infoBoxList();
        Pane right = new ElementInfoBox().right(currentUserNickName, currentUserHead, msg, msgType);
        // 消息填充
        listView.getItems().add(right);
        // 滚动条
        listView.scrollTo(right);
        if(MsgType.TEXT_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch(msg, msgDate);
        if(MsgType.EMOTION_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch("[表情]", msgDate);
        if(MsgType.FILE_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch("[文件]" + msg, msgDate);


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
        }else
            talkList.getItems().add(talkElementPane);

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

        addTalkMsgRight(talkBoxData.getTalkId(), msg, MsgType.TEXT_MSG.getMsgTypeCode(), msgDate, true, true, false);
        txtInput.clear();
        chatEventHandler.doEventSendMsg(currentUserId, currentUserNickName, currentUserHead, talkBoxData.getTalkId(), talkBoxData.getTalkType(), msg,MsgType.TEXT_MSG.getMsgTypeCode(), msgDate);

    }

    // selected为true，则表示选中该会话框，并且将会话填充到infoBox中
    public void addTalkMsgGroupLeft(String talkId, String groupName, String groupHead, String userId, String userName, String userHead, String msg, Integer msgType, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        // 如果时自己发送的消息，则抛弃
        if (this.currentUserId.equals(userId))
            return;

        GroupsData groupsData = null;
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        if(talkElement == null){
            // 初始化对话框元素
            talkElement = addTalkBox(0, TalkType.GROUP_MESSAGE.getTalkTypeCode(), talkId, groupName, groupHead, msg,msgDate, false);

            CacheUtil.talkMap.put(talkId, talkElement);
        }

        ListView<Pane> listView = talkElement.infoBoxList();
        Pane left = new ElementInfoBox().left(userId, userName, userHead, msg, msgType, stage);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);
        if(MsgType.TEXT_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch( userName + "：" + msg, msgDate);
        if(MsgType.EMOTION_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch(userName + "：[表情]" , msgDate);
        if(MsgType.FILE_MSG.getMsgTypeCode().equals(msgType))
            talkElement.fillMsgSketch(userName + "：[文件]"+ msg , msgDate);

        // 设置位置&选中
        updateTalkListIdxAndSelected(TalkType.GROUP_MESSAGE.getTalkTypeCode(), talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);

        if(selected && groupsData!=null)
            fillInfoBox(talkElement, groupsData.getGroupName());

    }


    public void minimizeWindow(MouseEvent mouseEvent) {
        stage.setIconified(true);
    }

    public void closeWindow(MouseEvent mouseEvent){
        FileDataSource fileDataSource = new FileDataSource(currentUserId);
        
        // 缓存对话框消息
        ObservableList<Pane> items = talkList.getItems();
        List<TalkBoxData> talkBoxDataList = new ArrayList<>();
        if(talkBoxDataList!=null ) {
            for (Pane pane : items) {
                TalkBoxData userData = (TalkBoxData) pane.getUserData();
                talkBoxDataList.add(userData);
            }

            fileDataSource.addTalkBoxList(talkBoxDataList);
        }

        // 缓存好友申请消息
        ObservableList<Pane> newFriendListViewItems = newFriendListView.getItems();
        List<NewFriendRequest> newFriendRequests = new ArrayList<>();
        if(newFriendListViewItems != null ) {
            for (Pane pane : newFriendListViewItems) {
                NewFriendRequest userData = (NewFriendRequest) pane.getUserData();
                newFriendRequests.add(userData);
            }
            fileDataSource.addNewFriendRequestList(newFriendRequests);
        }

        // 缓存群组申请消息
        ObservableList<Pane> groupRequestPaneList = groupRequestListView.getItems();
        List<UserGroupRequest> userGroupRequests = new ArrayList<>();
        if(groupRequestPaneList!=null){
            for(Pane pane : groupRequestPaneList){
                UserGroupRequest userData = (UserGroupRequest) pane.getUserData();
                userGroupRequests.add(userData);
            }
            fileDataSource.addGroupRequestList(userGroupRequests);
        }

        // 缓存好友消息
        if(ChatRecordMap.updated) {
            List<ChatRecordDto> chatRecordDtoList = ChatRecordMap.getChatRecordDtoList();
            fileDataSource.addChatRecordDto(chatRecordDtoList);
        }


        stage.close();
        System.exit(0);
    }

    public void fillNewFriendRequest(){
        // 读数据
        FileDataSource fileDataSource = new FileDataSource(currentUserId);
        List<NewFriendRequest> newFriendRequestList = fileDataSource.getNewFriendRequestList();
        // 填充消息
        if(newFriendRequestList != null && !newFriendRequestList.isEmpty()){
            for(NewFriendRequest newFriendRequest : newFriendRequestList){
                remindNewFriend(newFriendRequest.getUserId(), newFriendRequest.getUserName(), newFriendRequest.getUserHead());
            }
        }

    }

    public void fillGroupRequest(){
        // 读数据
        FileDataSource fileDataSource = new FileDataSource(currentUserId);
        List<UserGroupRequest> groupRequests = fileDataSource.getGroupRequestList();
        // 填充消息
        if(groupRequests != null && !groupRequests.isEmpty()){
            for(UserGroupRequest groupRequest : groupRequests){
                remindGroupRequest(groupRequest.getUserDto(), groupRequest.getGroupDto());
            }
        }
    }

    // ----------------------以下均为好友面板---------------------------

    private ElementFriendLuck elementFriendLuck;
    // 添加新的好友
    public void initAddFriendLuck(){
        ObservableList<Pane> items = friendList.getItems();

        // 设置新的好友面板
        ElementFriendTag elementFriendTag = new ElementFriendTag("添加好友");
        items.add(elementFriendTag.pane());

        ElementFriendLuck element = new ElementFriendLuck();
        elementFriendLuck = element;
        Pane pane = element.pane();
        items.add(pane);

        pane.setOnMousePressed(event -> {
            Pane friendLuckPane = element.friendLuckPane();
            setContentPaneBox(FriendPaneId.NEW_FRIEND_PANE_ID.getId(), "添加好友", friendLuckPane);
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

                if(!text.equals(""))
               chatEventHandler.doFriendLuckSearch(currentUserId, text);

            }
        });


    }

    public void addSearchGroup(GroupDto groupDto){
        ElementJoinGroupItem elementJoinGroupItem = new ElementJoinGroupItem(groupDto.getGroupId(), groupDto.getGroupName(), groupDto.getGroupHead(), groupDto.getStatus());
        Pane pane = elementJoinGroupItem.pane();

        ListView<Pane> groupListView = elementJoinGroup.getGroupListView();
        ObservableList<Pane> items = groupListView.getItems();
        items.add(pane);

        elementJoinGroupItem.statusLabel().setOnMousePressed(event -> {
            if(GroupState.NOT_ADD.getStateCode().equals(groupDto.getStatus()))
                chatEventHandler.doSendJoinInGroupRequest(this.currentUserId, groupDto.getGroupId(),groupDto.getGroupLeaderId());
        });



    }

    public void addLuckFriend(String userId, String userNickName, String userHead, Integer status) {
        ElementFriendLuckUser friendLuckUser = new ElementFriendLuckUser(userId, userNickName, userHead, status);
        Pane pane = friendLuckUser.pane();

        // 添加到好友列表
        ListView<Pane> friendLuckListView = elementFriendLuck.friendLuckListView();
        ObservableList<Pane> items = friendLuckListView.getItems();
        items.add(pane);

        // 点击事件 TODO 对Label的分类讨论
        friendLuckUser.statusLabel().setOnMousePressed(event -> {
            if(FriendState.NOT_ADD.getStateCode().equals(status))
            chatEventHandler.doSendFriendRequest(this.currentUserId, userId);
        });
    }

    private  ListView<Pane> newFriendListView;

    private ElementNewFriend elementNewFriend;
    private void initNewFriend(){
        ObservableList<Pane> items = friendList.getItems();

        // 设置新的好友面板
        ElementFriendTag elementFriendTag = new ElementFriendTag("新的好友");
        items.add(elementFriendTag.pane());

        elementNewFriend = new ElementNewFriend();

        Pane pane = elementNewFriend.pane();
        items.add(pane);
        newFriendListView = elementNewFriend.newFriendListView();
        pane.setOnMousePressed(event -> {
            Pane newFriendPane = elementNewFriend.getNewFriendPane();
            setContentPaneBox(FriendPaneId.NEW_FRIEND_PANE_ID.getId(), "新的好友", newFriendPane);
            clearViewListSelectedAll(userListView, groupListView);
            System.out.println("添加好友");
            elementNewFriend.getNewFriendRemind().setVisible(false);
        });

    }

    public void remindGroupRequest(UserDto userDto, GroupDto groupDto){


        if(!friendPane.isVisible())
            barFriend.setTextFill(Color.YELLOW);

        // 显示提示
        elementGroupRequest.getGroupRequestRemind().setVisible(true);

        // 添加申请
        ObservableList<Pane> items = groupRequestListView.getItems();
        ElementNewGroupRequest elementNewGroupRequest = new ElementNewGroupRequest(userDto, groupDto);
        Pane pane = elementNewGroupRequest.pane();

        UserGroupRequest userGroupRequest = new UserGroupRequest(userDto, groupDto);
        for(Pane currentPane : items){
            UserGroupRequest paneUserData = (UserGroupRequest) currentPane.getUserData();
            if(paneUserData.equals(userGroupRequest)) {
                groupRequestListView.getItems().remove(currentPane);
                groupRequestListView.getItems().add(0, currentPane);
                return;
            }
        }
        items.add(pane);

        // 注册申请事件处理
        elementNewGroupRequest.agreeLabel().setOnMousePressed(event -> {
            System.out.println("同意加入群组");
            groupRequestListView.getItems().remove(pane);
            chatEventHandler.agreeJoinInGroupRequest(userDto.getUserId(), groupDto);
        });

        elementNewGroupRequest.rejectLabel().setOnMousePressed(event -> {
            System.out.println("拒绝加入群组");
            groupRequestListView.getItems().remove(pane);
            chatEventHandler.rejectJoinInGroupRequest(userDto.getUserId(), groupDto);
        });

    }



    public void remindNewFriend(String userId, String userNickName, String userHead){
        ElementNewFriendUser newFriendUser = new ElementNewFriendUser(userId, userNickName, userHead);
        Pane pane = newFriendUser.pane();

        if(!friendPane.isVisible())
            barFriend.setTextFill(Color.YELLOW);
        elementNewFriend.getNewFriendRemind().setVisible(true);

        // 添加到新的好友列表
        ObservableList<Pane> items = newFriendListView.getItems();

        for(Pane currentPane : items){
            NewFriendRequest paneUserId = (NewFriendRequest) currentPane.getUserData();
            if(paneUserId.getUserId().equals(userId)) {
                newFriendListView.getItems().remove(currentPane);
                newFriendListView.getItems().add(0, currentPane);
                return;
            }
        }
        items.add(pane);

        newFriendUser.agreeLabel().setOnMousePressed(event -> {
            System.out.println("同意添加好友");
            newFriendListView.getItems().remove(pane);
            addFriendUser(false, userId, userNickName, userHead);
            chatEventHandler.agreeFriendRequest(this.currentUserId, userId);
        });

        newFriendUser.rejectLabel().setOnMousePressed(event -> {
            System.out.println("拒绝添加好友");
            newFriendListView.getItems().remove(pane);
            chatEventHandler.rejectFriendRequest(this.currentUserId, userId);
        });



    }


    // 添加公众号
    public void addCreateGroup(){
        ObservableList<Pane> items = friendList.getItems();
        ElementFriendTag elementFriendTag = new ElementFriendTag("创建群组");
        items.add(elementFriendTag.pane());

        ElementCreateGroup elementFriendSubscription = new ElementCreateGroup();
        Pane pane = elementFriendSubscription.pane();
        items.add(pane);


        pane.setOnMousePressed(event -> {
            clearViewListSelectedAll(userListView, groupListView);
            Pane subPane = elementFriendSubscription.subPane();
            Button createGroupButton = elementFriendSubscription.getCreateGroupButton();
            createGroupButton.setOnMouseClicked(event1 -> {
                TextField groupName = elementFriendSubscription.getGroupName();
                String text = groupName.getText();
                if(text == null)
                    text = "";

                text = text.trim();
                if(text.length() > 18)
                    text = text.substring(0, 18);

                if(! text.equals(""))
                    chatEventHandler.doEventCreateGroup(currentUserId, text);


            });
            setContentPaneBox(FriendPaneId.SUBSCRIBE_PANE_ID.getId(), "创建群组", subPane);

        });


    }

    private ElementJoinGroup elementJoinGroup;
    public void addJoinGroup(){
        ObservableList<Pane> items = friendList.getItems();
        ElementFriendTag elementFriendTag = new ElementFriendTag("加入群组");
        items.add(elementFriendTag.pane());

        elementJoinGroup = new ElementJoinGroup();
        Pane pane = elementJoinGroup.pane();
        items.add(pane);


        pane.setOnMousePressed(event -> {
            clearViewListSelectedAll(userListView, groupListView);
            Pane subPane = elementJoinGroup.subPane();
            setContentPaneBox(FriendPaneId.SUBSCRIBE_PANE_ID.getId(), "加入群组", subPane);
        });

        // 搜索框事件
        TextField friendLuckSearch = elementJoinGroup.getGroupSearch();

        friendLuckSearch.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                String text = friendLuckSearch.getText();
                if(text == null)
                    text = "";

                text = text.trim();
                if(text.length() > 30)
                    text = text.substring(0, 30);

                System.out.println("搜搜群组:" + text);

                elementJoinGroup.getGroupListView().getItems().clear();

                if(!text.equals(""))
                chatEventHandler.doGroupSearch(currentUserId, text);

            }
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
        ElementFriendTag elementFriendTag = new ElementFriendTag("群组");
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


        Label groupHeadLabel = new Label();
        groupHeadLabel.setPrefSize(100, 100);
        groupHeadLabel.setStyle("-fx-background-color: purple;");
        groupHeadLabel.setLayoutX(354);
        groupHeadLabel.setLayoutY(150);
        children.add(groupHeadLabel);

        Label groupIdLabel = new Label();
        groupIdLabel.setText("id : "+groupId);
        groupIdLabel.setPrefSize(808, 30);
        groupIdLabel.setFont(Font.font(24));
        groupIdLabel.setLayoutY(300);
        groupIdLabel.setAlignment(Pos.CENTER);
        children.add(groupIdLabel);

        Label groupNameLabel = new Label();
        groupNameLabel.setText("群组 : "+groupName);
        groupNameLabel.setAlignment(Pos.CENTER);
        groupNameLabel.setPrefSize(808, 30);
        groupNameLabel.setFont(Font.font(24));
        groupNameLabel.setLayoutY(360);
        children.add(groupNameLabel);

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

        doEventOpenFriendGroupSendMsg(sendMsgButton, groupId, groupName, groupHead, TalkType.GROUP_MESSAGE.getTalkTypeCode());
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

        Label userHeadLabel = new Label();
        userHeadLabel.setPrefSize(100, 100);
        userHeadLabel.setStyle("-fx-background-color: red;");
        userHeadLabel.setLayoutX(354);
        userHeadLabel.setLayoutY(150);
        children.add(userHeadLabel);

        Label userIdLabel = new Label();
        userIdLabel.setText("id : "+userFriendId);
        userIdLabel.setPrefSize(808, 30);
        userIdLabel.setFont(Font.font(24));
        userIdLabel.setLayoutY(300);
        userIdLabel.setAlignment(Pos.CENTER);
        children.add(userIdLabel);

        Label nickNameLabel = new Label();
        nickNameLabel.setText("昵称 : "+userFriendNickName);
        nickNameLabel.setAlignment(Pos.CENTER);
        nickNameLabel.setPrefSize(808, 30);
        nickNameLabel.setFont(Font.font(24));
        nickNameLabel.setLayoutY(360);
        children.add(nickNameLabel);

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

        doEventOpenFriendGroupSendMsg(sendMsgButton, userFriendId, userFriendNickName, userFriendHead, TalkType.PRIVATE_MESSAGE.getTalkTypeCode());
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

    public void doEventOpenFriendGroupSendMsg(Button sendMsgButton, String talkId, String talkName, String groupHead, Integer talkType) {
        sendMsgButton.setOnMouseClicked(event -> {
            // 1. 添加好友到对话框
            ElementTalk elementTalk = addTalkBox(0, talkType, talkId, talkName, groupHead, null, null, true);

            // 2. 切换到对话框窗口
            switchToChat(event);
            // 3.设置infoBox有效
            setValid(true);

            talkList.getItems().remove(elementTalk.pane());
            talkList.getItems().add(0, elementTalk.pane());

            // 4.填充对话框
            fillInfoBox(elementTalk, talkName);


            // 5. 使对话框处于第一位
            talkList.getSelectionModel().select(elementTalk.pane());


            // 6. 发送到服务器端，添加对话数据
            chatEventHandler.doEventAddTalk(currentUserId, talkId, talkType);

        });
    }


    @FXML
    private Label emotionLabel;

    private void registerEmotionHandler() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/tool.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE600';
        emotionLabel.setFont(Font.font(font.getFamily(), 30));
        emotionLabel.setText(Character.toString(unicode));
        emotionLabel.setTextAlignment(TextAlignment.CENTER);

        emotionLabel.setOnMousePressed(event -> {
            Emotion emotionStage = new Emotion(this, chatEventHandler, talkList);
            emotionStage.doShowFace(stage.getX() + 314 + 74 , stage.getY() + 618 - 170);
        });
    }
    @FXML
    private Label fileLabel;

    private void registerFileHandler(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/chat/ttf/tool.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE608';

        fileLabel.setFont(Font.font(font.getFamily(), 30));
        fileLabel.setText(Character.toString(unicode));
        fileLabel.setTextAlignment(TextAlignment.CENTER);

        fileLabel.setOnMousePressed(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {

                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                String objectName = currentUserId + "/" + selectedFile.getName();

                TaskExecutor.execTask(()->{
                    // 上传文件
                    FileManager.upLoadFile(ImFileConstants.Bucket, objectName, selectedFile.getAbsolutePath());
                });

                String filename = selectedFile.getName();
                Pane selectedItem = talkList.getSelectionModel().getSelectedItem();
                TalkBoxData userData = (TalkBoxData)selectedItem.getUserData();

                // 添加到对话
                addTalkMsgRight(userData.getTalkId(), filename,MsgType.FILE_MSG.getMsgTypeCode(), new Date(), true, false, true);

                // 发送消息到服务器
                chatEventHandler.doEventSendMsg(currentUserId, currentUserNickName, currentUserHead, userData.getTalkId(),userData.getTalkType(), filename, MsgType.FILE_MSG.getMsgTypeCode(), new Date());

            }

        });
    }

    public void initToolBox(){
        registerEmotionHandler();
        registerFileHandler();
    }

    @FXML
    private Pane validPane;

    @FXML
    private Pane invalidPane;


    public void setValid(boolean isValid){
        validPane.setVisible(isValid);
        invalidPane.setVisible(!isValid);
    }

    @FXML
    private Label logo;

    private void setLogo(){

        logo.setFont(Font.font("Blackadder ITC", 100));
        logo.setText("Nida");

    }

}




