package com.schat.entity;

import com.schat.controller.WebChatController;

/**
 * Created by 马东 on 2018/3/4.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 21:37 2018/3/4
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class Alial {
    private String chatId;
    private String aname;
    private WebChatController webChatController;

    public Alial(String chatId,String aname,WebChatController webChatController){
        this.chatId = chatId;
        this.aname = aname;
        this.webChatController = webChatController;
    }

    public String getChatId() {
        return chatId;
    }

    public String getAname() {
        return aname;
    }
    public WebChatController getWebChatController() {
        return webChatController;
    }

}
