/**
 * Created by 马东 on 2018/3/4.
 */
//$().ready(function(){
//
//})

layui.use(['layer'], function(){
    var layer = layui.layer


    var ws = null;
    var ME_NAME = null;

    var msgType = new Object();
    msgType.CREAT_CHAT = 1;
    msgType.CREAT_CHAT_ERRO = 11;
    msgType.CREAT_ALIAS_NAME = 2;
    msgType.CREAT_ALIAS_NAME_ERRO = 22;
    msgType.SEND_MSG = 3;
    msgType.SEND_SYS_MSG = 33;


    var isclose = 0;
    //layer.msg('Hello World');

    initSize($("#sroot"));
    //alert("ddd")
    connectWebSocket();

    var chatId = $("#room_ID").text();


    function connectWebSocket(){
        if('WebSocket' in window){
            ws = new WebSocket("ws:/www.smchat.cn/st");
            //ws = new WebSocket("ws:/127.0.0.1:10100/st");
        }else if('MozWebSocket' in window){
            ws = new WebSocket("ws:/www.smchat.cn/st");
            //ws = new WebSocket("ws:/127.0.0.1:10100/st");
        }else {
            layer.msg('你的浏览器不支持WebSocket……');
        }

        ws.onclose = function(evt){
            layer.msg('链接关闭！');
        }

        ws.onopen = function(evt){
            //alert("连接成功！")
            //var msg = new Object();
            //msg.chatId = $("#room_ID").text();
            //alert($("#room_ID").text())
            //msg.msgType = 1;
            var msg = msgStr(chatId,msgType.CREAT_CHAT,"","");
            var jsonMsg = JSON.stringify(msg);
            //alert(ws)
            ws.send(jsonMsg);

        }
        ws.onmessage = function(evt){
            console.log(evt.data);
            var msgObjec = jQuery.parseJSON(evt.data);
            handlerMsg(msgObjec);
            $('#msg_div').scrollTop($('#msg_div')[0].scrollHeight);
            //alert($('#msg_div')[0].scrollHeight)
        }
    }

    function handlerMsg(obj){
        if(msgType.CREAT_CHAT === obj.msgType){
            //开启弹框，创建别名
            layer.open({
                title: '呢称',
                area: ['240px', '200px'],
                type: 1,
                closeBtn: 0,
                content: $("#alialDiv") //数组第二项即吸附元素选择器或者DOM
            });


            //layerObj.close();
            return;
        }

        if(msgType.CREAT_ALIAS_NAME === obj.msgType){
            //关闭弹框,加入房间可以开始聊天

            layer.closeAll();
            return;
        }
        if(msgType.CREAT_ALIAS_NAME_ERRO === obj.msgType){
            //提示别名重复
            $("#tipSpan").text(obj.msg);
            return;
        }
        if(msgType.SEND_SYS_MSG === obj.msgType){
            if(isclose == 0){
                isclose = 1;
                layer.closeAll();
            }
            var html = '<div class="ms-width-80-per ms-height-6-per ms-align-items-center ms-layout-wrap-left-center" style="color: #f3ab20;">&nbsp;&nbsp;'+obj.sendName+' ['+obj.sendTime+']: </div> <div class="ms-width-80-per ms-layout-wrap-left-center ms-margin-left-1rem" style="color: #FF5722">'+obj.msg+' </div>';
            $("#msg_div").append(html);
            //$('#msg_div').scrollTop($('#msg_div').height());
            return;
        }
        if(msgType.SEND_MSG === obj.msgType){
            if(isclose == 0){
                isclose = 1;
                layer.closeAll();
            }
            var nameColor = "color: #5FB878;";
            var msgColor = "color: #000000;";
            if(ME_NAME === obj.sendName){
                nameColor = "color: #70a6b8;";
                msgColor = "color: #317fff;";
            }

            var html = '<div class="ms-width-80-per ms-height-6-per ms-align-items-center" style="'+nameColor+'">&nbsp;&nbsp;'+obj.sendName+' ['+obj.sendTime+']: </div> <div class="ms-width-80-per ms-layout-wrap-left-center ms-margin-left-1rem" style="'+msgColor+'">'+obj.msg+' </div>';
            $("#msg_div").append(html);
            //$('#msg_div').scrollTop($('#msg_div').height());
            return;
        }

    }


    document.onkeydown=function(e){
        if(e.keyCode == 13 && e.ctrlKey){
            // 这里实现换行
            document.getElementById("textareaMsgId").value += "<br>\n";
        }else if(e.keyCode == 13){
            // 避免回车键换行
            e.preventDefault();
            // 下面写你的发送消息的代码
            var msgIn = document.getElementById("textareaMsgId").value;
            if(msgIn === '' && ME_NAME != null){
                layer.open({
                    title: '提示'
                    ,content: '消息内容不能为空！'
                });

                return
            }
            var msg = msgStr(chatId,msgType.SEND_MSG,ME_NAME,msgIn);
            var jsonMsg = JSON.stringify(msg);
            ws.send(jsonMsg);
            document.getElementById("textareaMsgId").value = "";
        }
    }


    $("#alialButton").click(function(){
        var name = $("#alialInput").val();
        ME_NAME = name;
        var msg = msgStr(chatId,msgType.CREAT_ALIAS_NAME,name,"");
        var jsonMsg = JSON.stringify(msg);
        ws.send(jsonMsg);
    });

    function msgStr(chatId,msgType,sendName,msg){
        var msgObj = new Object();
        msgObj.chatId = chatId;//$("#room_ID").text();
        msgObj.sendName = sendName;//
        msgObj.msgType = msgType;
        msgObj.msg = msg;
        return msgObj;
    }

    function initSize($obj, isIndex)
    {
        var width = document.documentElement.clientWidth;
        var height = $(window).height();
        if(isIndex)
        {
            $obj.css({width: width, height: height});//112是控制栏
        }
        else
        {
            $obj.css({width:width,height:height});
        }
        // onWindowResize();
    }
});

/*$().ready(function(){

})*/



