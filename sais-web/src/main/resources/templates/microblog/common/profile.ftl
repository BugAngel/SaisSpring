<div class="profile">
    <!--    新增右侧个人部分-->
    <div class="my_info">
        <a href="/microblog/home/index">
            <img class="my_info_head" height="90px" width="90px" src="/upload/microblog/images/head_image/${Session["user"].avatar}">
        </a>
        <h4>${Session["user"].nickname}</h4>
        <div class="my_info_list">
            <ul>
                <a href="/microblog/home/friend">
                    <li><span>${Session["user"].follows_num}</span></li>
                    <li>关注</li>
                </a>
            </ul>
            <ol></ol>
            <ul>
                <a href="/microblog/home/fan">
                    <li><span>${Session["user"].fans_num}</span></li>
                    <li>粉丝</li>
                </a>
            </ul>
            <ol></ol>
            <ul>
                <a href="/microblog/home/index">
                    <li><span>${Session["user"].posts_num}</span></li>
                    <li>发帖</li>
                </a>
            </ul>
        </div>
    </div>
</div>
