<@override name="body">
<div class="my_head my_head_other width_1000">
    <div class="my_head_img">
    <img src="/upload/microblog/images/head_image/${friend_info.avatar}" alt="头像">
    </div>
    <h4>${friend_info.nickname}</h4>
    <div class="my_head_message">
        <ul class="fl">
            <li>注册于：${friend_info.addtime}</li>
            <li><span>QQ：${friend_info.qq}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span>邮箱：${friend_info.email}</span></li>
        </ul>

    <#if Session["user"].id == friend_info.id>
        <button  class="show_btn">
            <a href="/microblog/home/index" style="color:white;">
                个人主页
            </a>
        </button>
    <#elseif is_friend==1>
        <button id="cancel-follow" class="show_btn" value=${friend_info.id}>
            取消关注
        </button>
     <#else >
        <button id="follow" class="show_btn" value=${friend_info.id}>
            关注
        </button>
    </#if>

        <div class="my_info_list fr">
            <div class="fr">
                <ul>
                    <li><span>${friend_info.follows_num}</span></li>
                    <li>关注</li>
                </ul>
                <ol></ol>
                <ul>
                    <li>
                        <span>${friend_info.fans_num}</span>
                    </li>
                    <li>粉丝</li>
                </ul>
                <ol></ol>
                <ul>
                    <li><span>${friend_info.posts_num}</span></li>
                    <li>微博</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="main">
    <div class="left">
        <h4 class="weibo_list_title">他的微博</h4>
        <#include "../common/bloglist.ftl">
        <#include "../common/page.ftl">
    </div>
    <#include "../common/profile.ftl">
</div>
</@override>
<@extends name="../common/common.ftl"/>
