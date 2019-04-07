<@override name="body">
<div class="main">
    <div class="left">
        <h4 class="weibo_list_title">我的消息</h4>
        <#if datalists??>
        <#list datalists as vo>
        <div class="weibo_list">
            <div class="weibo_list_top">
                <div class="weibo_list_head">
                    <a href="/microblog/home/index?friend_id=${vo.parent.user_id}">
                        <img class="avatar" src="/upload/microblog/images/head_image/${vo.avatar}"/>
                    </a>
                </div>

                <ul>
                    <li><b>${vo.parent.nickname}</b></li>
                    <li><span>${vo.parent.addtime}</span></li>
                    <li>
                        <p>
                            ${vo.parent.content}
                        </p>
                    </li>
                </ul>
            </div>

            <div class="weibo_list_top" style="background: #F2F2F5">
                <ul>
                    <li><b>${vo.post.nickname}</b></li>
                    <li><span>${vo.post.addtime}</span></li>
                    <li>
                        <p>
                            ${vo.post.content}
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        </#list>
        <#else >
            <div class="empty">
                <p>还没有消息哦！</p>
            </div>
        </#if>
        <!--分页开始-->
        <#include "../common/page.ftl">
        <!--分页结束-->
    </div>
    <#include "../common/profile.ftl">
</div>
</@override>
<@extends name="../common/common.ftl"/>