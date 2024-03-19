package com.lxy.imapp.front.controller;

import com.lxy.imapp.front.view.Login;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Label loginAppName;

    @FXML
    private Label loginClose;

    @FXML
    private Label loginMinimize;

    @FXML
    private TextField userAccount;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label dummyLabel;

    @FXML
    private Button loginButton;


    private Stage stage;

    private Paint lightBlue = Color.web("#03e9f4");


    public void initialize(){

        setLoginCloseStyle();

        setLoginMinimizeStyle();

        setAppNameStyle();

        bindPasswordField();

        initUserAccountIcon();

        initUserPasswordIcon();

        initUserShowPasswordIcon();

        initAccountFocusHandler();
    }



    private void setLoginMinimizeStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/login.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE65A';
        loginMinimize.setFont(Font.font(font.getFamily(), 20));
        loginMinimize.setText(Character.toString(unicode));
    }

    // 设置阴影
    private void setLoginButtonStyle() {
        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setWidth(1);
        boxBlur.setHeight(1);
        boxBlur.setIterations(1);
        loginButton.setEffect(boxBlur);

    }

    private void setLoginCloseStyle(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/login.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE7AC';
        loginClose.setFont(Font.font(font.getFamily(), 20));
        loginClose.setText(Character.toString(unicode));
    }

    private void setAppNameStyle(){
        loginAppName.setText("Nida");
        loginAppName.setFont(Font.font("Blackadder ITC", 100));
    }

    public void lostFocus(MouseEvent mouseEvent) {
        dummyLabel.requestFocus();
    }


    public void setStage(Login login) {
        this.stage = login;
    }

    public void minimizeStage(MouseEvent mouseEvent) {

        stage.setIconified(true);

    }

    public void closeStage(MouseEvent mouseEvent) {
        stage.close();
        System.exit(0);
    }

    public void login(MouseEvent mouseEvent) {
        System.out.println("用户名:" + userAccount.getText());
        System.out.println("密码:"+ password);
    }

    @FXML
    private Label userAccountIcon;

    @FXML
    private Label userPasswordIcon;

    private void initUserAccountIcon(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/input.ttf"), 20);
        //某个图标的unicode
        char unicode = '\uE61E';
        userAccountIcon.setFont(Font.font(font.getFamily(), 15));
        userAccountIcon.setText(Character.toString(unicode));
    }

    private void initUserPasswordIcon(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/input.ttf"), 20);
        //某个图标的unicode
        char unicode = '\uE608';
        userPasswordIcon.setFont(Font.font(font.getFamily(), 18));
        userPasswordIcon.setText(Character.toString(unicode));
    }

    @FXML
    private Label userShowPasswordIcon;

    private void initAccountFocusHandler(){
        userAccount.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
               userAccountIcon.getStyleClass().remove("highlight");
            }
            else {
                userAccountIcon.getStyleClass().add("highlight");
            }
        });

        userPassword.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                userPasswordIcon.getStyleClass().remove("highlight");
            }
            else {
                userPasswordIcon.getStyleClass().add("highlight");
            }
        });

        userShowPassword.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                userPasswordIcon.getStyleClass().remove("highlight");
            }
            else {
                userPasswordIcon.getStyleClass().add("highlight");
            }
        });


    }

    private void initUserShowPasswordIcon(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/input.ttf"), 20);
        //某个图标的unicode
        char unicode = '\uE600';
        userShowPasswordIcon.setFont(Font.font(font.getFamily(), 18));
        userShowPasswordIcon.setText(Character.toString(unicode));
    }


    public void highlightAccountIcon(MouseEvent mouseEvent) {
        userAccountIcon.setTextFill(lightBlue);
    }

    private boolean isHighlightShowPasswordIcon = false;

    @FXML
    private TextField userShowPassword;

    void showPassword(boolean isShow){

        userPassword.setVisible(!isShow);
        userShowPassword.setVisible(isShow);

    }
    public void showPassword(MouseEvent mouseEvent) {

        if(!isHighlightShowPasswordIcon){
            userShowPasswordIcon.getStyleClass().add("highlight");
            showPassword(true);
        }else {
            userShowPasswordIcon.getStyleClass().remove("highlight");
            showPassword(false);
        }
        isHighlightShowPasswordIcon = !isHighlightShowPasswordIcon;
    }
    private String password;

    private void bindPasswordField(){
        userPassword.textProperty().addListener((observable, oldValue, newValue) ->
        {
            password = newValue;
            userShowPassword.setText(password);
        });
        userShowPassword.textProperty().addListener((observable, oldValue, newValue) ->
        {
            password = newValue;
            userPassword.setText(password);
        });
    }
}
