package com.schat.common;

/**
 * Created by 马东 on 2018/3/4.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 21:20 2018/3/4
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class StringUtil {
    /**
     * 解析信息
     * @param msg
     * @return
     */
    public static String[] splitStr(String msg){
        String[] msgs = msg.split("#");
        return msgs;
    }

    /**
     * 判断str是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || str.equals("");
    }
}
