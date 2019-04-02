<@override name="body">
<div class="my_head width_1000">
    <div class="my_head_img">
        <img src="/upload/microblog/images/head_image/${Session["user"].avatar}" alt="头像">

    </div>
    <h4>${Session["user"].nickname}</h4>
    <div class="my_head_message">
        <ul class="fl">
            <li>注册于：${Session["user"].addtime}</li>
        </ul>
        <div class="my_info_list fr">
            <div class="fr">
                <ul>
                    <li><span>${Session["user"].follows_num}</span></li>
                    <li>关注</li>
                </ul>
                <ol></ol>
                <ul>
                    <li><span>${Session["user"].fans_num}</span></li>
                    <li>粉丝</li>
                </ul>
                <ol></ol>
                <ul>
                    <li><span>${Session["user"].posts_num}</span></li>
                    <li>微博</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="main">
    <div class="left setting-head">
        <div class="ui top attached tabular menu new_menu">
            <a class="item" data-tab="first">头像设置</a>
            <a class="item active" data-tab="second">资料设置</a>
            <a class="item" data-tab="third">更改密码</a>
        </div>
        <div class="ui bottom attached tab segment segment_new" data-tab="first">
            <div style="height: 400px">
                <form class="ui form" id="avatar"  name="avatar" method="post" enctype="multipart/form-data"  action="/microblog/setting/imageUpload">
                    <div class="field">
                        <label>头像</label>
                    </div>
                    <div class="field">
                        <img width="160px" height="160px" src="/upload/microblog/images/head_image/${user.avatar}" alt="图片">
                    </div>
                    <div class="field">
                        <input type="file" class="file" id="picture" name="picture"/>
                    </div>
                    <div class="field">
                        <input class="ui teal button" type="submit" value="提交">
                    </div>
                </form>
            </div>
        </div>
        <div class="ui bottom attached tab segment segment_new active" data-tab="second">
            <form class="ui form" id="setting"  name="setting" method="post" action="/microblog/setting/saveSetting">
                <div class="field">
                    <label>昵称</label>
                    <input type="text"  name="nickname" value=${Session["user"].nickname}>
                </div>
                <div class="field">
                    <label>性别</label>
                    <select class="ui fluid dropdown" name="sex">
                        <option value="0" <#if Session["user"].sex==0>selected</#if>>保密</option>
                        <option value="1" <#if Session["user"].sex==1>selected</#if>>男</option>
                        <option value="2" <#if Session["user"].sex==2>selected</#if>>女</option>
                    </select>
                </div>
                <div class="field">
                    <label>qq号</label>
                    <input type="text" name="qq" value=${Session["user"].qq}>
                </div>
                <div class="field">
                    <label>邮箱</label>
                    <input type="text" name="email" value=${Session["user"].email}>
                </div>
                <div class="field">
                    <label>电话</label>
                    <input type="text" name="phone" value=${Session["user"].phone}>
                </div>
                <div class="field">
                    <label>GPA</label>
                    <input type="text" name="gpa" <#if Session["user"].gpa gt 0>value=${Session["user"].gpa}</#if>>
                </div>
                <div class="field">
                    <label>SAT</label>
                    <input type="text" name="sat" <#if Session["user"].sat gt 0>value=${Session["user"].sat}</#if>>
                </div>
                <div class="field">
                    <label>雅思</label>
                    <input type="text" name="ielts" <#if Session["user"].ielts gt 0>value=${Session["user"].ielts}</#if>>
                </div>
                <div class="field">
                    <label>托福</label>
                    <input type="text" name="toefl" <#if Session["user"].toefl gt 0>value=${Session["user"].toefl}</#if>>
                </div>
                <input class="ui teal button" type="submit" value="提交">
            </form>
        </div>
        <div class="ui bottom attached tab segment segment_new" data-tab="third">
            <form class="ui form" id="changePassword"  name="changePassword" method="post" action="/microblog/setting/changePassword">
                <div class="required field">
                    <label>原始密码</label>
                    <input type="password" name="old_password"  id="old_password">
                </div>
                <div class="required field">
                    <label>新密码</label>
                    <input type="password" name="new_password"  id="new_password">
                </div>
                <div class="required field">
                    <label>确认新密码</label>
                    <input type="password" name="new_password2" id="new_password2">
                </div>
                <div id="save-password" class="ui submit teal  button ">确认</div>
            </form>
        </div>
    </div>
    <#include "../common/profile.ftl">
</div>

<script>
    $('#setting').submit(function () {
        var phone = $("input[name='phone']").val();
        var email = $("input[name='email']").val();
        var gpa = $("input[name='gpa']").val();
        var sat = $("input[name='sat']").val();
        var ielts = $("input[name='ielts']").val();
        var toefl = $("input[name='toefl']").val();
        if(phone!=="" && !checkPhone(phone)){
            layer.msg("电话格式错误");
            return false;
        }
        if(email!=="" && !checkEMail(email)){
            layer.msg("邮箱格式错误");
            return false;
        }
        if(gpa!=="" && !((parseFloat(gpa)>0) && (parseFloat(gpa)<=5))){
            layer.msg("gpa输入错误");
            return false;
        }
        if(sat!==""  && !((parseInt(sat)>0) && (parseInt(sat)<=1600))){
            layer.msg("sat输入错误");
            return false;
        }
        if(ielts!=="" && !((parseFloat(ielts)>0) && (parseFloat(ielts)<=9))){
            layer.msg("雅思输入错误");
            return false;
        }
        if(toefl!=="" && !((parseFloat(toefl)>0) && (parseFloat(toefl)<=120))){
            layer.msg("托福输入错误");
            return false;
        }
        var url = $(this).attr('action');
        var formData = $("#setting").serialize();
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            data: formData,
            success: function (message) {
                layer.msg(message);
            }
        });
        return false;
    });
</script>

<script>
    $('#save-password').click(function(){
        var old_password  = $("input[name='old_password']").val();
        var new_password  = $("input[name='new_password']").val();
        var new_password2 = $("input[name='new_password2']").val();
        if(checkpassword(new_password)){
            layer.msg('密码长度应为6-16个字符');
            return false;
        }
        if(old_password === ''){
            layer.msg('原始密码不能为空');
            return false;
        }
        if(new_password === ''){
            layer.msg('新密码不能为空');
            return false;
        }
        if(new_password === old_password){
            layer.msg('新密码与原始密码不能相同');
            return false;
        }
        if(new_password !== new_password2){
            layer.msg('新密码与确认密码不一致');
            return false;
        }

        var url = "/microblog/setting/changePassword";
        var formData = $("#changePassword").serialize();
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            data: formData,
            success: function (res) {
                if (res.status) {
                    layer.msg(res.message, {time: 1000}, function () {
                        window.location.href = "/microblog/login/login";
                    });
                } else {
                    layer.msg(res.message);
                }
            }
        });
        return false;
    });

    //菜单切换
    $('.menu .item')
        .tab()
    ;
</script>
</@override>
<@extends name="../common/common.ftl"/>