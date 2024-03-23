package com.lxy.imapp.front;

import com.lxy.imapp.front.view.Chat;
import com.lxy.imapp.front.view.Login;

import java.util.logging.Logger;


public class ImUI {
    private Login login;
    private Chat chat;

    public Login getLogin() {
        return login;
    }



    public void setLogin(Login login) {
        this.login = login;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
