<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/static/common/images/icon.ico" type="images/x-ico" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/webjars/Semantic-UI/2.1.7/semantic.min.css" >
    <link rel="stylesheet" type="text/css" href="/static/microblog/css/login.css" />
    <script src="/webjars/jquery/2.1.1/jquery.min.js"></script>
    <script src="/static/common/layer/layer.js"></script>
    <script src="webjars/Semantic-UI/2.1.7/semantic.min.js"></script>
    <script type="text/javascript" src="/static/microblog/js/common.js"></script>
</head>
<body>
<div class="header">
    <img src="/static/microblog/images/index_logo.png">
</div>

<div class="main">
    <div class="left">
        <div class="login-bg">
            <img src="/static/microblog/images/login_banner.png">
        </div>
    </div>
    <div class="content">
        <!-- 用户输入区开始 -->
        <div class="ui big form">
            <div class="ui stacked segment blue">
                <form id="form" name="form" method="post" action="/microblog/login/checkLogin" autocomplete="off">
                    <div class="field">
                        <div class="ui icon input">
                            <i class="user icon"></i>
                            <input id="account" name="account" type="text" placeholder="用户名" autocomplete="off">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui icon input">
                            <i class="lock icon"></i>
                            <input id="password" name="password" type="password" placeholder="密码" autocomplete="off">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui icon input">
                            <a> <img class="reloadverify" alt="验证码" onclick="this.src='/defaultKaptcha?d='+new Date()*1" src="/defaultKaptcha" /></a>
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui icon input">
                            <input id="code" name="code" type="text" placeholder="验证码" autocomplete="off">
                        </div>
                    </div>
                    <input type="submit" id="login" value="登录" class="ui fluid large teal submit  button">
                </form>
            </div>
            <div class="ui message">
                新用户? <a href="/microblog/register/index">注册</a>
            </div>

            <!--ajax异步提交-->
            <script>
                $('form').submit(function () {
                    var account = $("input[name='account']").val();
                    var password = $("input[name='password']").val();
                    var code = $("#code").val();

                    if(!checkname(account))
                    {
                        layer.msg("账号只含数字和英文字母，长度为5-14个字符");
                        return false;
                    }
                    if(!checkpassword(password))
                    {
                        layer.msg( "密码长度应为6-16个字符");
                        return false;
                    }
                    if (!code) {
                        layer.msg('验证码不能为空！');
                        return false;
                    }
                    var url = $(this).attr('action');
                    var formData = $("#form").serialize();
                    $.ajax({
                        type: "post",
                        url: url,
                        dataType: "json",
                        data: formData,
                        success: function (res) {
                            if (res.status) {
                                layer.msg(res.message, {time: 1000}, function () {
                                    window.location.href = "/college/index/index";
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
        </div>
        <!-- 推荐输入区结束 -->

        <!-- 推荐学校开始 -->
        <div class="recommend">
            <div class="ui horizontal divider">
                <h4 class="ui teal">推荐学校</h4>
            </div>
            <div class="ui tiny images">
                <img class="ui medium circular image" src="/static/college/images/colleges/Massachusetts_Institute_of_Technology_suaPINX.png">
                <img class="ui medium circular image" src="/static/college/images/colleges/Stanford_University.png">
                <img class="ui medium circular image" src="/static/college/images/colleges/Harvard_University.png">
                <img class="ui medium circular image" src="/static/college/images/colleges/California_Institute_of_Technology.png">
                <img class="ui medium circular image" src="/static/college/images/colleges/University_of_Oxford.png">
                <img class="ui medium circular image" src="/static/college/images/colleges/University_of_Cambridge_Drn0FVb.png">
                <img class="ui medium circular image" src="/static/college/images/colleges/University_College_London.png">
                <img class="ui medium circular image" src="/static/college/images/colleges/Imperial_College_London.png">
                <img class="ui medium circular image" src="/static/college/images/colleges/The_University_of_Chicago.png">
            </div>
        </div>
        <!-- 推荐用户结束 -->
    </div>
</div>
<div class="clear"></div>
<div class="footer">
    Copyright@BugAngel
</div>
</body>
</html>