<!--导航-->
<div class="nav">
    <div class="width_1200">
        <ul>
            <li><a href="/microblog/">论坛主页</a></li>
            <li style="padding-left: 20px">
                <form method="post" action="search.php" >
                    <input type="text" name="keyword" placeholder="三千世界来搜一搜吧...">
                    <button type="button" class="search"></button>
                </form>
            </li>
            <li style="padding-left: 10px"><a href="myCollect.php">我的收藏</a></li>
            <li><a href="myPraise.php">我的赞</a></li>

        </ul>
        <ol>
            <li>
                <a href="/microblog/setting/index">
                    ${Session["user"].nickname}
                </a>
            </li>
            <li class="li_message">&nbsp;
                <dl class="menu">
                    <dd><a href="atMe.php">@我的</a></dd>
                    <dd><a href="message.php">我的消息</a></dd>
                </dl>
            </li>
            <li class="li_set">&nbsp;
                <dl class="menu">
                    <dd><a href="/microblog/setting/index">个人中心</a></dd>
                    <dd><a href="/microblog/login/logout">退出</a></dd>
                </dl>
            </li>
        </ol>
    </div>
</div>