package com.lxy.imapp.front.controller;

import com.lxy.imapp.biz.util.BeanUtil;
import com.lxy.imapp.front.view.PersonalInfo;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PersonalController {

    @FXML
    private Label myHead;

    @FXML
    private Label myId;

    @FXML
    private Label myName;


    @FXML
    private Label personMinimize;

    @FXML
    private Label personClose;

    private PersonalInfo stage;

    public void setStage(PersonalInfo stage){
        this.stage = stage;
    }


    void setUserInfo(String userId, String userName, String userHead){
        myId.setText(userId);

        myName.setText(userName);



    }

    public void initialize(){

        setPersonMinimizeStyle();
        setPersonCloseStyle();
        initHead();
        initMyId();
        initMyName();
    }

    private void setPersonCloseStyle(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/login.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE7AC';
        personClose.setFont(Font.font(font.getFamily(), 20));
        personClose.setText(Character.toString(unicode));
    }

    private void setPersonMinimizeStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/login.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE65A';
        personMinimize.setFont(Font.font(font.getFamily(), 20));
        personMinimize.setText(Character.toString(unicode));
    }

    private void initHead() {
        myHead.setPrefSize(80,80);
        myHead.setLayoutX(110);
        myHead.setStyle("-fx-background-color: yellow");
    }

    private void initMyId(){
        myId.setPrefSize(300,30);;
        myId.setLayoutY(100);
        myId.setAlignment(Pos.CENTER);
        myId.setTextFill(Color.BLACK);
        myId.setFont(Font.font(24));
        myId.setText("id : " + BeanUtil.getUserId());
    }

    private void initMyName(){
        myName.setPrefSize(300,30);;
        myName.setLayoutY(150);
        myName.setAlignment(Pos.CENTER);
        myName.setTextFill(Color.BLACK);
        myName.setFont(Font.font(24));
        myName.setText("昵称 : " + BeanUtil.getUserNickName());
    }

    public void minimizeStage(MouseEvent mouseEvent) {
        stage.setIconified(true);
    }

    public void closeStage(MouseEvent mouseEvent) {
        stage.bar.setTextFill(Color.web("#99cccc"));
        stage.close();

    }
}
