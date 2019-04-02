<@override name="body">
<div class="container" style="margin-bottom: 20px">
    <div class="row clearfix">
        <div class="col-md-2 column pull-left" style="font-family: 楷体;font-size: 25px">
            <div class="list-group">
                <a href="/college/college/index?country=all&qsLow=1&qsHigh=10&majorLow=1&majorHigh=10" class="list-group-item" style="height: 67px">
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>学霸院校
                </a>
                <a href="/college/college/index?country=US&qsLow=1&qsHigh=140&majorLow=1&majorHigh=140" class="list-group-item" style="height: 67px">
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>美国院校
                </a>
                <a href="/college/college/index?country=UK&qsLow=1&qsHigh=140&majorLow=1&majorHigh=140" class="list-group-item" style="height: 67px">
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>英国院校
                </a>
                <a href="/college/guide/xingqian" class="list-group-item" style="height: 67px">
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>出国指南
                </a>
                <a href="/microblog/setting/setting" class="list-group-item"  style="height: 67px">
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>个人资料
                </a>
                <a href="/microblog/index/index" class="list-group-item" style="height: 67px">
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>论坛交流
                </a>
            </div>
        </div>
        <div class="col-md-10 column pull-right">
            <div class="carousel slide" id="carousel-931159">
                <ol class="carousel-indicators">
                    <li data-slide-to="0" data-target="#carousel-931159">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-931159">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-931159">
                    </li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img alt="" src="/upload/college/images/${img1.picture}" style="width: 960px; height: 402px"/>
                        <div class="carousel-caption">
                            <h4>
                                ${img1.college_name}
                            </h4>
                            <p>
                                ${img1.introduce}
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="/upload/college/images/${img2.picture}" style="width: 960px; height: 402px"/>
                        <div class="carousel-caption">
                            <h4>
                                ${img2.college_name}
                            </h4>
                            <p>
                                ${img2.introduce}
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="/upload/college/images/${img3.picture}" style="width: 960px; height: 402px"/>
                        <div class="carousel-caption">
                            <h4>
                                ${img3.college_name}
                            </h4>
                            <p>
                                ${img3.introduce}
                            </p>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#carousel-931159" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-931159" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>

<div style="background: #f9f9fd">
    <div class="container">
        <div class="row clearfix">
            <a href="/college/guide/xingqian">
                <div id="xingqian" class="col-md-3 column box" style="color:black">
                    <img id="xingqianimg" src="/static/college/images/square/xingqian1.png" style="width:100%"/>
                    <h3 class="text-center" style="font-family: 楷体;">
                        行前准备
                    </h3>
                    <p class="text-center">
                        老司机详解，你一定用得到的行前准备知识...
                    </p>
                </div>
            </a>
            <a href="/college/guide/xuexi">
                <div id="xuexi" class="col-md-3 column box" style="color:black">
                    <img id="xuexiimg" src="/static/college/images/square/xuexi1.png" style="width:100%"/>
                    <h3 class="text-center" style="font-family: 楷体;">
                        学习指南
                    </h3>
                    <p class="text-center">
                        到了外国不会做课堂笔记，害怕做公共演讲，小组讨论插不上话？点进来看看前辈的经验提前适应...
                    </p>
                </div>
            </a>
            <a href="/college/guide/kecheng">
                <div id="kecheng" class="col-md-3 column box" style="color:black">
                    <img id="kechengimg" src="/static/college/images/square/kecheng1.png" style="width:100%"/>
                    <h3 class="text-center" style="font-family: 楷体;">
                        课程对照
                    </h3>
                    <p class="text-center">
                        选课时看着英语发愁？英汉对照表在此...
                    </p>
                </div>
            </a>
            <a href="/college/guide/gonggong">
                <div id="gonggong" class="col-md-3 column box" style="color:black">
                    <img id="gongongimg" src="/static/college/images/square/gonggong1.png" style="width:100%"/>
                    <h3 class="text-center" style="font-family: 楷体;">
                        公共用语
                    </h3>
                    <p class="text-center">
                        雅思托福考得不错，可现实生活中却依然张不开嘴？
                        学会公共用语帮你迅速融入留学生活...
                    </p>
                </div>
            </a>
        </div>
    </div>
</div>
<style>
    .listItem {
        font-family: 楷体;
        height: 68px;
        font-size: 25px;
        text-align: center;
    }

    .box {
        height: 356px;
        background: #f9f9fd;
        display: table-cell;
        vertical-align: middle;
        text-align: center;
    }
</style>

<script>
    $(document).ready(function () {
        $("#xingqian").mouseover(function () {
            $("#xingqianimg").attr("src", "/static/college/images/square/xingqian2.png");
        }).mouseleave(function () {
            $("#xingqianimg").attr("src", "/static/college/images/square/xingqian1.png");
        });
        $("#xuexi").mouseover(function () {
            $("#xuexiimg").attr("src", "/static/college/images/square/xuexi2.png");
        }).mouseleave(function () {
            $("#xuexiimg").attr("src", "/static/college/images/square/xuexi1.png");
        });
        $("#kecheng").mouseover(function () {
            $("#kechengimg").attr("src", "/static/college/images/square/kecheng2.png");
        }).mouseleave(function () {
            $("#kechengimg").attr("src", "/static/college/images/square/kecheng1.png");
        });
        $("#gonggong").mouseover(function () {
            $("#gongongimg").attr("src", "/static/college/images/square/gonggong2.png");
        }).mouseleave(function () {
            $("#gongongimg").attr("src", "/static/college/images/square/gonggong1.png");
        });
    });
</script>
</@override>
<@extends name="../common/common.ftl"/>
