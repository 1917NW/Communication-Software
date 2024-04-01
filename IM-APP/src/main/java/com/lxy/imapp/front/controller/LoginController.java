package com.lxy.imapp.front.controller;

import com.lxy.imapp.TestApplication;
import com.lxy.imapp.biz.event.LoginEventHandler;
import com.lxy.imapp.biz.http.RegisterURL;
import com.lxy.imapp.biz.http.UserRegisterDto;
import com.lxy.imapp.biz.http.UserRegisterResult;
import com.lxy.imapp.front.view.Login;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginController {

    private LoginEventHandler loginEventHandler;

    public void setLoginEventHandler(LoginEventHandler loginEventHandler){
        this.loginEventHandler = loginEventHandler;
    }

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


    private Login stage;

    private Paint lightBlue = Color.web("#03e9f4");


    public void initialize(){

        setLoginCloseStyle();
        setRegisterCloseStyle();

        setLoginMinimizeStyle();
        setRegisterMinimizeStyle();

        initNickNameIcon();
        initVerifyCodeIcon();

        setAppNameStyle();

        bindPasswordField();
        bindRegisterPasswordField();

        initUserAccountIcon();

        initUserPasswordIcon();

        initUserShowPasswordIcon();

        initAccountFocusHandler();
    }


    @FXML
    private Label verifyCodeIcon;
    private void initVerifyCodeIcon(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/register.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE603';
        verifyCodeIcon.setFont(Font.font(font.getFamily(), 15));
        verifyCodeIcon.setText(Character.toString(unicode));
    }

    private void setLoginMinimizeStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/login.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE65A';
        loginMinimize.setFont(Font.font(font.getFamily(), 20));
        loginMinimize.setText(Character.toString(unicode));
    }

    @FXML
    private Label registerMinimize;
    private void setRegisterMinimizeStyle() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/login.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE65A';
        registerMinimize.setFont(Font.font(font.getFamily(), 20));
        registerMinimize.setText(Character.toString(unicode));
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

    @FXML
    private Label registerClose;

    private void setRegisterCloseStyle(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/login.ttf"), 35);
        //某个图标的unicode
        char unicode = '\uE7AC';
        registerClose.setFont(Font.font(font.getFamily(), 20));
        registerClose.setText(Character.toString(unicode));
    }

    @FXML
    private Label registerAppName;
    private void setAppNameStyle(){
        loginAppName.setText("Nida");
        loginAppName.setFont(Font.font("Blackadder ITC", 100));

        registerAppName.setText("Nida");
        registerAppName.setFont(Font.font("Blackadder ITC", 100));
    }

    @FXML
    private Label registerLabel;

    public void lostFocus(MouseEvent mouseEvent) {
        if(loginPane.isVisible())
            dummyLabel.requestFocus();
        else
            registerLabel.requestFocus();
    }

    public void registerLostFocus(MouseEvent mouseEvent){registerLabel.requestFocus();}

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

        loginEventHandler.doLogin(userAccount.getText(), password);
    }

    @FXML
    private Label userAccountIcon;

    @FXML
    private Label userPasswordIcon;

   @FXML
   private Label registerAccountIcon;

    private void initUserAccountIcon(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/input.ttf"), 20);
        //某个图标的unicode
        char unicode = '\uE61E';
        userAccountIcon.setFont(Font.font(font.getFamily(), 15));
        userAccountIcon.setText(Character.toString(unicode));
        registerAccountIcon.setFont(Font.font(font.getFamily(), 15));
        registerAccountIcon.setText(Character.toString(unicode));

    }

    @FXML
    private Label registerPasswordIcon;

    private void initUserPasswordIcon(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/input.ttf"), 20);
        //某个图标的unicode
        char unicode = '\uE608';
        userPasswordIcon.setFont(Font.font(font.getFamily(), 18));
        userPasswordIcon.setText(Character.toString(unicode));
        registerPasswordIcon.setFont(Font.font(font.getFamily(), 18));
        registerPasswordIcon.setText(Character.toString(unicode));
    }

    @FXML
    private Label registerNameIcon;

    private void initNickNameIcon(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/register.ttf"), 20);
        //某个图标的unicode
        char unicode = '\uE66A';
        registerNameIcon.setFont(Font.font(font.getFamily(), 16));
        registerNameIcon.setText(Character.toString(unicode));

    }
    @FXML
    private Label userShowPasswordIcon;

    @FXML
    private TextField registerAccount;

    @FXML
    private TextField registerNickname;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerShowPassword;

    @FXML
    private TextField verifyCode;

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

        registerAccount.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                registerAccountIcon.getStyleClass().remove("highlight");
            }
            else {
                registerAccountIcon.getStyleClass().add("highlight");
            }
        });

        registerNickname.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                registerNameIcon.getStyleClass().remove("highlight");
            }
            else {
                registerNameIcon.getStyleClass().add("highlight");
            }
        });

        registerPassword.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                registerPasswordIcon.getStyleClass().remove("highlight");
            }
            else {
                registerPasswordIcon.getStyleClass().add("highlight");
            }
        });

        registerShowPassword.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                registerPasswordIcon.getStyleClass().remove("highlight");
            }
            else {
                registerPasswordIcon.getStyleClass().add("highlight");
            }
        });

        verifyCode.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                verifyCodeIcon.getStyleClass().remove("highlight");
            }
            else {
                verifyCodeIcon.getStyleClass().add("highlight");
            }
        });



    }

    @FXML
    private Label registerShowPasswordIcon;

    private void initUserShowPasswordIcon(){
        Font font = Font.loadFont(getClass().getResourceAsStream("/fxml/login/tff/input.ttf"), 20);
        //某个图标的unicode
        char unicode = '\uE600';
        userShowPasswordIcon.setFont(Font.font(font.getFamily(), 18));
        userShowPasswordIcon.setText(Character.toString(unicode));

        registerShowPasswordIcon.setFont(Font.font(font.getFamily(), 18));
        registerShowPasswordIcon.setText(Character.toString(unicode));
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

    private boolean isHighlightShowRegisterPasswordIcon =false;

    public void showRegisterPassword(MouseEvent mouseEvent){
        if(!isHighlightShowRegisterPasswordIcon){
            registerShowPasswordIcon.getStyleClass().add("highlight");
            showRegisterPassword(true);
        }else {
            registerShowPasswordIcon.getStyleClass().remove("highlight");
            showRegisterPassword(false);
        }
        isHighlightShowRegisterPasswordIcon = !isHighlightShowRegisterPasswordIcon;
    }

    private void showRegisterPassword(boolean isShow){
        registerPassword.setVisible(!isShow);
        registerShowPassword.setVisible(isShow);
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

    @FXML
    private Pane registerPane;

    @FXML
    private Pane loginPane;

    public void switchToRegister() {
        loginPane.setVisible(false);
        registerPane.setVisible(true);

    }

    public void switchToLogin(MouseEvent mouseEvent) {
        loginPane.setVisible(true);
        registerPane.setVisible(false);
        inputUserInfo();
    }

    @FXML
    private Pane verifyCodePane;

    @FXML
    private Pane userRegisterInfoPane;

    public void inputVerifyCode(MouseEvent mouseEvent) {
        verifyCodePane.setVisible(true);
        userRegisterInfoPane.setVisible(false);
        RegisterURL.getVerifyCode(registerAccount.getText());
    }

    public void inputUserInfo(){
        verifyCodePane.setVisible(false);
        userRegisterInfoPane.setVisible(true);
    }

    public void submitVerifyCode(MouseEvent mouseEvent) {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUserPhone(registerAccount.getText());
        userRegisterDto.setUserNickname(registerNickname.getText());
        userRegisterDto.setUserPassword(registerPrePassword);
        userRegisterDto.setVerifyCode(verifyCode.getText());
        UserRegisterResult userRegisterResult = RegisterURL.verifyCode(userRegisterDto);
        if(!userRegisterResult.isSuccess()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register Result");
            alert.setHeaderText("Register Failed!");
            alert.setContentText(userRegisterResult.getRemindMsg());
            alert.showAndWait();
            verifyCode.setText("");
            switchToRegister();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Register Result");
            alert.setHeaderText("Register Success!");
            alert.setContentText(userRegisterResult.getRemindMsg());
            alert.showAndWait();
            verifyCode.setText("");
            stage.LoginSuccess();
            ChatController controller = stage.getChat().controller;
            controller.setUserInfo(registerAccount.getText(), registerNickname.getText(), "xxxx");

        }


    }

    private void clearRegister() {

    }

    private String registerPrePassword;

    private void bindRegisterPasswordField(){
        registerPassword.textProperty().addListener((observable, oldValue, newValue) ->
        {
            registerPrePassword = newValue;
            registerShowPassword.setText(registerPrePassword);
        });
        registerShowPassword.textProperty().addListener((observable, oldValue, newValue) ->
        {
            registerPrePassword = newValue;
            registerPassword.setText(registerPrePassword);
        });
    }

}
