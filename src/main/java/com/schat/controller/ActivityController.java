package com.schat.controller;

import com.alibaba.fastjson.JSON;
import com.schat.common.DateUtil;
import com.schat.entity.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by 马东 on 2018/3/12.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 13:03 2018/3/12
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @RequestMapping("/index")
    public String acitvity(){
        return "/activity";
    }
    @RequestMapping("/result")
    @ResponseBody
    public String activeResult(){
        java.util.Random random=new java.util.Random();// 定义随机类
        int result=random.nextInt(10)+1;// 返回[0,10)集合中的整数，注意不包括10
        String time = DateUtil.getNow();
        Activity activity = new Activity();
        activity.setResult(result);
        activity.setTime(time);
        String resultJson = JSON.toJSONString(activity);
        return resultJson;              // +1后，[0,10)集合变为[1,11)集合，满足要求
    }
}
