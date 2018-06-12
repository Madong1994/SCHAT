package com.schat.controller;

import com.schat.common.StringUtil;
import com.schat.entity.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by 马东 on 2018/3/4.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 18:47 2018/3/4
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Controller
//@RequestMapping("/schat")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }
    @RequestMapping("/room")
    public String room(String roomid,Map model){
        if(StringUtil.isEmpty(roomid)||roomid.length()<=0 ||roomid.length() > 8){
            return "/error";
        }
        Room roomObject = new Room();
        roomObject.setRoomName(roomid);
        model.put("room",roomObject);
        return "/room";
    }
}
