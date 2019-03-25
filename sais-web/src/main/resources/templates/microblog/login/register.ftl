<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/static/common/images/icon.ico" type="images/x-ico" />
    <title>论坛</title>
    <link rel="stylesheet" type="text/css" href="/webjars/Semantic-UI/2.1.7/semantic.min.css" >
    <link rel="stylesheet" type="text/css" href="/static/microblog/css/login.css" />
    <script src="/webjars/jquery/2.1.1/jquery.min.js"></script>
    <script src="/static/common/layer/layer.js"></script>
    <script src="webjars/Semantic-UI/2.1.7/semantic.min.js"></script>
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
                <form id="form" name="form" method="post" action="/microblog/register/checkRegister" autocomplete="off">
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
                            <i class="lock icon"></i>
                            <input id="repwd" name="repwd" type="password" placeholder="确认密码" autocomplete="off">
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
                    <input type="submit" id="register" value="注册" class="ui fluid large teal submit  button" >
                </form>
            </div>

            <div class="ui message">
                已有账号，直接 <a href="/microblog/login/index">登录</a>
            </div>

            <script>
                //检验账号格式
                function checkname(name) {
                    var reg = /^[A-Za-z0-9]+$/;
                    if (!reg.test(name)) {
                        layer.msg( "用户名应由数字和26个英文字母组成");
                        return false;
                    }
                    if (name.length < 5 || name.length > 14) {
                        layer.msg( "用户名长度应为5-14个字符");
                        return false;
                    }
                    return true;
                }

                //检查密码格式
                function checkpassword(password) {
                    if (password.length < 6 || password.length > 16) {
                        layer.msg( "密码长度应为6-16个字符");
                        return false;
                    }
                    return true;
                }

                //检查密码是否一样
                function checkSame(password,repassword) {
                    if(password!==repassword){
                        layer.msg("两次密码输入不同");
                        return false;
                    }
                    return true;
                }
            </script>

            <script>
                //ajax异步提交
                $('form').submit(function () {
                    var account = $("#account").val();
                    var password = $("#password").val();
                    var repassword=$("#repwd").val();
                    var code = $("#code").val();

                    var data = {};

                    if (!checkname(account)) {
                        return false;
                    }
                    if (!checkpassword(password)) {
                        return false;
                    }
                    if (!checkSame(password,repassword)) {
                        return false;
                    }
                    if (!code) {
                        layer.msg('验证码不能为空！');
                        return false;
                    }

                    var url = $(this).attr('action');

                    data.account = account;
                    data.password = password;
                    data.code = code;

                    $.ajax({
                        type: "post",
                        url: url,
                        dataType: "json",
                        data: data,
                        success: function (res) {
                            if (res.status) {
                                layer.msg(res.message, {time: 1000}, function () {
                                    window.location.href = "/microblog/login/login";
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