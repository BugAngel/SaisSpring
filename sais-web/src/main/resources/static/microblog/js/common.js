
$(function(){
    /**点击@按钮，切换显示和隐藏**/
    $('.at-friend').click(function(){
        $('.interest-link').toggle();
    });

    /** 展开获取微博回复信息，关闭移除回复信息 **/
    $(".weibo_list_bottom .weibo_list_bottom_message").click(function(){
        var total = $(this).children('span').html();
        var comment_list = $(this).parent().siblings(".weibo_comment").children(".comment_list");
        if(comment_list.is(":hidden")){
            if(total > 0 ){
                var index = layer.msg('数据加载中', {icon: 16});//layer加载数据样式
                var pid = $(this).parent().attr('value');//获取微博id
                /**ajax获取评论5条以内数据数据**/
                myajax=$.ajax({
                    type: "post",
                    url: "/microblog/post/getComment",
                    dataType: "json",
                    data: {pid: pid},
                    success: function (jsdata) {
                        // var data = jsdata;
                        $(jsdata).each(function(){
                            var str = '';
                            str += '<div class="weibo_list weibo-comment" >';
                            str += '<div class="weibo_list_top">';
                            str += '<div class="weibo_list_head">';
                            str += '<a><img class ="avatar" src="/upload/microblog/images/head_image/' + this.avatar + '" alt="头像"></a></div>';
                            str += '<ul class="weibo-comment-ul">';
                            str += '<li><b>' + this.nickname + '</b></li>';
                            str += '<li><span>' + this.post.addtime + '</span></li>';
                            str += '<li><p>' + this.post.content + '</p></li>';
                            str += '</ul></div></div>';
                            comment_list.append(str);
                        });

                        // if(total > 5){
                            var str_total = '';
                            str_total += '<div class="weibo_comment_more">';
                            str_total += '<a href="/microblog/comment/index?post_id='+pid+'">共有'+ total +'条评论，点击查看全部></a></div>';
                            comment_list.append(str_total);
                        // }
                        layer.close(index);//layer关闭加载样式
                    }
                });
            }
        }else{
            //移除回复内容
            comment_list.children().remove();
        }
        $(this).parent().siblings(".weibo_comment").slideToggle(200);
        $(this).toggleClass("weibo_list_bottom_message_cur");
    });

    $(".my_friend_list button").click(function(){
        $(this).toggleClass("my_friend_btn_click");
    });

    $(".my_head_message .show_btn").click(function(){
        $(this).toggleClass("show_btn_on");
    });

    $(".weibo_list_top .weibo_list_head_collect").click(function(){
        $(this).toggleClass("weibo_list_head_collect_cur");
    });


    /** 转发 **/
    $('.forward').click(function(){
        var pid = $(this).parent().attr('value');
        //iframe层
        layer.open({
            type: 2,                            //弹出框
            title: '转发微博',                   //标题
            area:['700px','500px'],             //弹层宽高
            shade: 0.5,                         //背景透明度
            content: '/microblog/forward/getForward?pid='+pid //iframe的url
        });
    });

    /**
     * 首页收藏、取消收藏
     */
    $(function(){
        /**js实现收藏和取消收藏 **/
        $('.collect').click(function(){
            var post_id = $(this).parent().attr('value');
            var that    = $(this);
            $.ajax({
                type: "post",
                url: "/microblog/post/collect",
                dataType: "json",
                data: {post_id:post_id},
                success: function (re) {
                    if(re === 1){
                        layer.msg('收藏成功',{time:2000});
                        that.html('已收藏');
                    }else{
                        layer.msg('已取消收藏',{time:2000});
                        that.html('收藏');
                    }
                }
            });
            return false;
        });
    });

    /**
     * 我的收藏页取消收藏
     */
    $(function(){
        $('.cancel-collect').click(function(){
            var post_id = $(this).attr('value');
            $.ajax({
                type: "post",
                url: "/microblog/post/collect",
                dataType: "json",
                data: {post_id:post_id},
                success: function (re) {
                    if(re === 1){
                        layer.msg('操作失败！');
                    }else{
                        layer.msg('已取消收藏',{time:2000});
                        window.location.reload();
                    }
                }
            });
            return false;
        });
    });

    /**
     * 点赞
     */
    $(function(){
        $('.praise').click(function(){
            var post_id = $(this).parent().attr('value');
            var count   = $(this).children().text();
            var that    = $(this);

            $.ajax({
                type: "post",
                url: "/microblog/post/praise",
                dataType: "json",
                data: {post_id:post_id},
                success: function (re) {
                    if(re === 1){
                        layer.msg('点赞成功！',{time:2000});
                        count++;
                        that.children().text(count);
                    }else{
                        layer.msg('您已经赞过啦！',{time:2000});
                    }
                }
            });
            return false;
        });
    });

    /**关注/取消关注**/
    $('#follow,#cancel-follow').click(function(){
        var friend_id = $(this).attr('value');
        $.ajax({
            type: "post",
            url: "/microblog/friend/follow",
            dataType: "json",
            data: {friend_id:friend_id},
            success: function (re) {
                if(re === 1){
                    layer.msg('关注成功',{time:2000});
                }else{
                    layer.msg('已取消',{time:2000});
                }
                window.location.reload();
            }
        });
        return false;
    });

    /** 关闭弹层 **/
    $('.more-forward').click(function(){
        var post_id = $(this).attr('value');
        var index = parent.layer.getFrameIndex(window.name);
        parent.location.href = '/microblog/forward/list?post_id='+post_id;
        parent.layer.close(index); //关闭弹层
    });


});

/**
 * highslide展示图片效果
 */
$(function(){
    hs.graphicsDir = '/webjars/highslide/4.1.13/graphics/';
    hs.align = 'center';
    hs.transitions = ['expand', 'crossfade'];
    hs.wrapperClassName = 'dark borderless floating-caption';
    hs.fadeInOut = true;
    hs.dimmingOpacity = .75;

// Add the controlbar
    if (hs.addSlideshow) hs.addSlideshow({
        //slideshowGroup: 'group1',
        interval: 5000,
        repeat: false,
        useControls: true,
        fixedControls: 'fit',
        overlayOptions: {
            opacity: .6,
            position: 'bottom center',
            hideOnMouseOut: true
        }
    });
});

/**检测字数**/
function checknum(v, word) {
    var len = 140 - v.length;
    $('#sayword_' + word).text(len);
    if (len < 0) {
        $('#sayword_' + word).css({
            "color": "red"
        });
    }
}

/**选择好友**/
function chooseFriend(username){
    var content = $('textarea').val();
    content = content + '@'+username + ' ';
    $('#saybox_0').val(content);
    $('.interest-link').hide();
}

/**检测字数：大于0，小于140字**/
function checkWordsNumber(content){
    var len = content.length;
    var message = '';
    if (len === 0) {
        message = "发布内容不能为空！";
    }
    if (len > 140) {
        message = "发布内容不能超过140字！";
    }
    return message;
}

/**确认发布**/
function saysub(pid,type) {
    /**检测字数**/
    var content = $('#saybox_'+ pid).val();
    var check_result = checkWordsNumber(content);
    if(check_result){
        layer.msg(check_result);
        return false;
    }
    /**获取图片路径,拼接成字符串**/
    var pics = '';
    $('.img_common').each(function(){
        pics += $(this).attr('src') + ",";
    });
    if(pics){
        pics = pics.substring(0,pics.length-1);
    }

    /**微博类型**/
    if(type === 'comment'){
        type = 1;
    }else if(type === 'forward'){
        type = 2;
    }else{
        type = 0;
    }

    $.ajax({
        type: "post",
        url: "/microblog/post/post",
        dataType: "json",
        data: {pid:pid,type:type,content:content,pictures:pics},
        success: function (data) {
            if (data === -1) {
                layer.msg('请先登录',{time:1000},function(){
                    window.location.href = '/microblog/login/login';
                });
                return false;
            }else if(data===0){
                layer.msg('提交失败');
                return false;
            }
            layer.msg('发布成功',{time:1000},function(){
                var index = parent.layer.getFrameIndex(window.name);
                parent.location.reload();
                parent.layer.close(index); //再执行关闭
            });
        }
    });
    return false;
}

//检验账号格式
function checkname(name) {
    var reg = /^[A-Za-z0-9]+$/;
    if (!reg.test(name)) {
        return false;
    }
    return !(name.length < 5 || name.length > 14);
}

//检查密码格式
function checkpassword(password) {
    return !(password.length < 6 || password.length > 16);
}

//检查密码是否一样
function checkSame(password,repassword) {
    return password===repassword;
}

//检查电话格式
function checkPhone(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}

//检查邮箱格式
function checkEMail(email) {
    var re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    return re.test(email);
}