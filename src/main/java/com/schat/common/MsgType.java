package com.schat.common;

/**
 * Created by 马东 on 2018/3/4.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 21:26 2018/3/4
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class MsgType {
    /**
     * 创建房间
     */
    public static int CREAT_CHAT = 1;
    /**
     * 创建房间失败，被已经创建
     */
    public static int CREAT_CHAT_ERRO= 11;
    /**
     * 创建聊天的别名
     */
    public static int CREAT_ALIAS_NAME = 2;
    /**
     * 创建别名吃失败
     */
    public static int CREAT_ALIAS_NAME_ERRO = 22;
    /**
     * 发送信息
     */
    public static int SEND_MSG = 3;
    /**
     * 系统信息
     */
    public static int SEND_SYS_MSG = 33;
}
