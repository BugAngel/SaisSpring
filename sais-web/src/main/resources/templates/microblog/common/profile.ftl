<div class="profile">
    <!--    新增右侧个人部分-->
    <div class="my_info">
        <#--<img class="my_info_head" height="90px" width="90px" src="<?php echo get_cover_path($user['avatar']) ?>">-->
        <h4>昵称</h4>
        <div class="my_info_list">
            <ul>
                <li><span>关注数</span></li>
                <li>${Session["user"].follows_num}</li>
            </ul>
            <ol></ol>
            <ul>
                <li><span>粉丝数</span></li>
                <li>${Session["user"].fans_num}</li>
            </ul>
            <ol></ol>
            <ul>
                <li><span>已发微博数</span></li>
                <li>${Session["user"].posts_num}</li>
            </ul>
        </div>
    </div>
</div>