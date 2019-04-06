<@override name="body">

<div class="main">
    <div class="left">
        <h4 class="weibo_list_title">原始微博</h4>
        <!--新增转发当前微博-->
        <div class="weibo_list">
            <div class="weibo_list_top">
                <div class="weibo_list_head">
                    <a>
                        <img class="avatar" src="/upload/microblog/images/head_image/${post_info.avatar}">
                    </a>
                </div>
                <ul>
                    <li><b>${post_info.nickname}</b></li>
                    <li><span>${post_info.post.addtime}</span></li>
                    <li><p>${post_info.post.content}</p></li>
                </ul>
            </div>
        </div>

        <!-- 转发显示区域-->
        <h4 class="weibo_list_title" style="margin-top: 10px">全部转发</h4>
        <#list datalists as vo>
        <div class="weibo_list">
            <div class="weibo_list_top">
                <div class="weibo_list_head">
                    <a>
                        <img class="avatar" src="/upload/microblog/images/head_image/${vo.avatar}">
                    </a>
                </div>
                <ul>
                    <li><b>${vo.post.nickname}</b></li>
                    <li><span>${vo.post.addtime}</span></li>
                    <li><p>${vo.post.content}</p></li>
                </ul>
            </div>
        </div>
        </#list>
    <#include "../common/page.ftl">
    </div>
    <#include "../common/profile.ftl">
</div>
</@override>
<@extends name="../common/common.ftl"/>