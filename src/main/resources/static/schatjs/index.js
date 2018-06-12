/**
 * Created by 马东 on 2018/3/4.
 */
layui.use(['layer'], function() {
    var layer = layui.layer
    initSize($("#sroot"));
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



    $("#creatBtn").click(function(){

        var roomId = $("#roomId").val();
        if(roomId.length >8){
            layer.open({
                title: '提示'
                ,content: '输入的房间名称过长！'
            });
            return;
        }
        //if(roomId.length == 0){
        //    layer.open({
        //        title: 'erro'
        //        ,content: '未输入房间名称！'
        //    });
        //    return;
        //}
        window.location.href = "/schat/room?roomid="+roomId;

        $(this).attr('disabled','disabled');

    });
});
