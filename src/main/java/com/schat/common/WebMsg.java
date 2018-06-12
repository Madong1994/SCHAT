package com.schat.common;

/**
 * Created by 马东 on 2018/3/4.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 22:07 2018/3/4
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class WebMsg {
    private String chatId;
    private String sendName;
    private String sendTime;
    private int msgType;
    private String msg;

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
