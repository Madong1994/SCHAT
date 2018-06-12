package com.schat.common;

import com.sun.org.apache.regexp.internal.RE;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 马东 on 2018/3/7.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 21:43 2018/3/7
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class DateUtil {
    /**
     * 得到当前时间
     * @return
     */
    public static String getNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = format.format(new Date());
        return nowTime;
    }
}
