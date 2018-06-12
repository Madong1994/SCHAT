package com.schat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.schat.common.DateUtil;
import com.schat.common.MsgType;
import com.schat.common.StringUtil;
import com.schat.common.WebMsg;
import com.schat.entity.Alial;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 马东 on 2018/3/4.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 20:43 2018/3/4
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@ServerEndpoint(value = "/st")
@Component
public class WebChatController {
    private Session session;
    public static Map<String,List<Alial>> chatMap = new HashMap<>();
    public static List<Alial> alials = new ArrayList<>();
    @OnOpen
    public void open(Session session){
        this.session = session;
        System.out.println("...新链接");
    }
    public Session getSession(){
        return session;
    }

    /**
     * 房间id#sender#meg
     * @param msg
     */
    @OnMessage
    public void onMessage(String msg){
        JSON json = JSON.parseObject(msg);
        WebMsg webMsg = JSONObject.toJavaObject(json,WebMsg.class);
        handlerMsg(webMsg);
    }

    /**
     * 处理信息
     * @param webMsg
     */
    public void handlerMsg(WebMsg webMsg){
        /*创建房间*/
        webMsg.setSendTime(DateUtil.getNow());
        if (webMsg.getMsgType() == MsgType.CREAT_CHAT){
            List<Alial> ff = chatMap.get(webMsg.getChatId());
            if(ff != null){
                String msgJson = JSON.toJSONString(webMsg);
                send(this.getSession(),msgJson);
                return;
            }
            List<Alial> friends = new ArrayList<>();
            chatMap.put(webMsg.getChatId(),friends);
            String msgJson = JSON.toJSONString(webMsg);
            send(this.getSession(),msgJson);
            return;
        }
        /*设置别名*/
        if (webMsg.getMsgType() == MsgType.CREAT_ALIAS_NAME){
            List<Alial> friends = chatMap.get(webMsg.getChatId());
            for (Alial alial: friends) {
                if(alial.getAname().equals(webMsg.getSendName())){
                    String msg = "["+webMsg.getSendName()+"] 已经被使用！";
                    webMsg.setMsg(msg);
                    webMsg.setMsgType(MsgType.CREAT_ALIAS_NAME_ERRO);
                    String msgJson = JSON.toJSONString(webMsg);
                    send(this.session,msgJson);
                    return;
                }
            }
            Alial alial = new Alial(webMsg.getChatId(),webMsg.getSendName(),this);
            alials.add(alial);
            friends.add(alial);
            String msg = "["+webMsg.getSendName()+"] 进入房间...";
            webMsg.setSendName("系统消息");
            webMsg.setMsg(msg);
            webMsg.setMsgType(MsgType.SEND_SYS_MSG);
            String msgJson = JSON.toJSONString(webMsg);
            sendMsg(webMsg.getChatId(),msgJson);
            return;
        }
        /*发送信息*/
        if(webMsg.getMsgType() == MsgType.SEND_MSG){
            String msgJson = JSON.toJSONString(webMsg);
            sendMsg(webMsg.getChatId(),msgJson);
        }
    }

    /**
     * 给所有房间内的好友发送信息
     * @param chatId
     * @param msg
     */
    public void sendMsg(String chatId, String msg){
        List<Alial> friends = chatMap.get(chatId);
        if(friends != null && friends.size() != 0){
            for (Alial alial: friends) {
                Session session = alial.getWebChatController().getSession();
                send(session,msg);
            }
        }
    }

    /**
     * 信息发送
     * @param session
     * @param msg
     */
    public void send(Session session, String msg){
        if(session != null){
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("---发送信息失败！");
            }
        }
    }

    @OnClose
    public void onClose(Session session){
        removePool(session);
    }
    public void removePool(Session session){
        if(alials == null || alials.size() == 0){
            return;
        }
        Alial a = null;
        boolean isSame = false;
        for (Alial alial : alials) {
            if(alial.getWebChatController() == this){
                a = alial;
                isSame = true;
                break;
            }
        }
        if(isSame && a != null){
            alials.remove(a);
            List<Alial> ca = chatMap.get(a.getChatId());
            ca.remove(a);
        }
//        removePool(session);
    }
    @OnError
    public void onError(Throwable thr){
        thr.printStackTrace();
    }
}
