package com.lxy.imapp.front.view;

import com.lxy.imapp.biz.event.LoginEventHandler;
import com.lxy.imapp.front.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login extends Stage {

    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";

    private Parent root;

    private double xOffset;

    private double yOffset;

    private Chat chat;

    private LoginEventHandler loginEventHandler;

    public LoginController loginController;

    public Login(LoginEventHandler loginEventHandler, Chat chat){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(RESOURCE_NAME));
            root = fxmlLoader.load();
            loginController = fxmlLoader.getController();
            loginController.setLoginEventHandler(loginEventHandler);
            this.chat = chat;
        } catch (IOException e) {
           e.printStackTrace();
        }

        initEventHandler(root);

        loginController.setStage(this);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        setResizable(false);
        initStyle(StageStyle.TRANSPARENT);


        getIcons().add(new Image("file:src/main/resources/fxml/login/img/app.png"));

    }

    private void initEventHandler(Parent root) {
        root.setOnMousePressed(mouseEvent -> {
            xOffset = getX() - mouseEvent.getScreenX();
            yOffset = getY() - mouseEvent.getScreenY();
            root.setCursor(Cursor.CLOSED_HAND);
        });


        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });

        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }

    public void LoginSuccess(){
        // 关闭原窗口
        close();
        // 打开聊天窗口
        chat.show();
    }

    public void LoingFailed(){
        System.out.println("登录失败");

    }

    public Chat getChat(){
        return chat;
    }
}
