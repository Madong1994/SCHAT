package com.schat.controller;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;

/**
 * Created by 马东 on 2018/3/5.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 20:38 2018/3/5
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
//@Component
//@ServerEndpoint(value = "/test")
public class JettyWebSocket implements WebSocketListener {
    @Override
    public void onWebSocketBinary(byte[] bytes, int i, int i1) {
        System.out.println("--onWebSocketBinary--");
    }

    @Override
    public void onWebSocketText(String s) {
        System.out.println("--onWebSocketText--");
    }

    @Override
    public void onWebSocketClose(int i, String s) {
        System.out.println("--onWebSocketClose--");
    }

    @Override
    public void onWebSocketConnect(Session session) {
        System.out.println("--onWebSocketConnect--");
    }

    @Override
    public void onWebSocketError(Throwable throwable) {
        System.out.println("--onWebSocketError--");
    }
}
