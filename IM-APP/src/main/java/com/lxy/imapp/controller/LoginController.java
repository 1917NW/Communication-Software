package com.lxy.imapp.controller;

import com.lxy.imapp.view.Login;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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




    public void initialize(){

        setLoginCloseStyle();

        setLoginMinimizeStyle();

        setAppNameStyle();

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
        System.out.println("密码:"+ userPassword.getText());
    }


}
