<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>后台管理界面 - 登录</title>
    <link rel="icon" href="/static/common/images/icon.ico" type="images/x-ico" />

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/layer/3.0.1/skin/default/layer.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" >
    <link href="//cdn.bootcss.com/animate.css/3.5.2/animate.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/static/admin/css/admin-style.css" >

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/layer/3.0.1/layer.min.js"></script>
</head>
<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">WR</h1>
        </div>
        <h3>后台管理登录界面</h3>
        <form id="form" name="form" method="post" action="/admin/login/checkLogin"  autocomplete="off">
            <div class="form-group">
                <input name="account" type="text"  class="form-control" placeholder="账号"  autocomplete="off">
            </div>
            <div class="form-group">
                <input name="password" type="password" class="form-control" placeholder="密码" autocomplete="off">
            </div>
            <div class="form-group login">
                <span>验证码</span>
                <input name="code" style="width:110px" type="text" id="code"/>
                <a> <img class="reloadverify" alt="验证码" onclick="this.src='/defaultKaptcha?d='+new Date()*1" src="/defaultKaptcha" /></a>
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
        </form>
    </div>
</div>
<!--ajax异步提交-->
<script>
    $('form').submit(function(){
        var account  = $("input[name='account']").val();
        var password  = $("input[name='password']").val();
        var code = $("#code").val();
        if(!account){
            layer.msg('用户名不能为空！',{time:2000});
            return false;
        }
        if(!password){
            layer.msg('密码不能为空！',{time:2000});
            return false;
        }
        if(!code){
            layer.msg('验证码不能为空！',{time:2000});
            return false;
        }
        var url  = $(this).attr('action');
        var formData = $("#form").serialize();
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            data: formData,
            success: function (res) {
                if (res.status) {
                    layer.msg(res.message, {time: 1000}, function () {
                        window.location.href = "/admin/user_list/lists";
                    });
                } else {
                    //刷新验证码
                    $(".reloadverify").click();
                    layer.msg(res.message);
                }
            }
        });
        return false;
    });
</script>
</body>
</html>