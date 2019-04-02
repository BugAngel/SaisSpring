<div style="height:52px; font-family: 楷体; font-size: 20px; background: black">
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <nav class="navbar navbar-inverse" role="navigation">
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <#if Session["user"]??>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="fa fa-user" aria-hidden="true"></i>${Session["user"].nickname} <b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu" style="font-family: 楷体; font-size: 20px;text-align:center;">
                                        <li><a href="/microblog/setting/setting"><i class="fa fa-id-card" aria-hidden="true"></i>个人资料</a></li>
                                        <li><a href="/microblog/index"><i class="fa fa-street-view" aria-hidden="true"></i>查看论坛</a></li>
                                        <li class="divider"></li>
                                        <li><a href="/microblog/login/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>退出</a></li>
                                    </ul>
                                </li>
                            <#else>
                                <li>
                                    <a href="/microblog/login/index"><i class="fa fa-sign-in" aria-hidden="true"></i>登录</a>
                                </li>
                                <li>
                                    <a href="/microblog/register/index"><i class="fa fa-registered" aria-hidden="true"></i>注册</a>
                                </li>
                            </#if>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#myModal"><i class="fa fa-hand-o-up" aria-hidden="true"></i>投诉</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</div>

<!-- 投诉框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    我要投诉<br>
                    <small>亲爱的用户，欢迎在下方填写您遇到的问题或意见建议。</small>
                </h4>
            </div>
            <div class="modal-body">
                <textarea name='tsnr' class="form-control" rows="7" placeholder="详尽的描述问题更有利于我们为您解答哦~"></textarea>
                <br>
                <div class="input-group margin-bottom-sm" style="width: 300px">
                    <span class="input-group-addon"><i class="fa fa-envelope-o fa-fw"></i></span>
                    <input name='email' class="form-control" type="text" placeholder="您的邮箱地址">
                </div>

                <div class="input-group" style="width: 300px">
                    <span class="input-group-addon"><i class="fa fa-mobile fa-fw"></i></span>
                    <input name='phone' class="form-control" type="text" placeholder="您的手机号">
                </div>
                <br>
                <em>请留下这些联系方式，更方便我们联系您</em>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button id="complaint" type="button" class="btn btn-primary">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script>
    $(function () {
        $("#complaint").click(function () {
            var tsnr = $("textarea[name='tsnr']").val();
            var email = $("input[name='email']").val();
            var phone = $("input[name='phone']").val();
            var pattern = /^1[34578]\d{9}$/;
            var isphone = pattern.test(phone);
            if(tsnr===""){
                layer.msg('投诉内容不能为空！');
                return false;
            }
            if (!isphone) {
                layer.msg('请输入正确的手机号码');
                return false;
            }
            var re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            var a = re.test(email);
            if (!a) {
                layer.msg('邮箱格式不正确！');
                return false;
            }
            //开始发送数据
            $.ajax
            ({
                url: "/college/complain/checkComplain",
                type: "POST",
                dataType: "json",
                //传送请求数据
                data: {tsnr: tsnr, email: email, phone: phone},
                success: function (res) { //登录成功后返回的数据
                    //根据返回值进行状态显示
                    if (res.status === 1) {
                        $('#myModal').modal('hide');
                        layer.msg(res.message);
                    } else if (res.status === 2) {
                        $('#myModal').modal('hide');
                        layer.msg(res.message,{time:1000},function () {
                            window.location.href = "/microblog/login/index";
                        });
                    } else {
                        $('#myModal').modal('hide');
                        layer.msg(res.message);
                    }
                }
            });
        })
    })
</script>