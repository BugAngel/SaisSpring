<@override name="body">
<div class="main">
    <div class="left">
        <h4 class="weibo_list_title">评论</h4>
        <div class="weibo_list">
            <div class="weibo_list_top">
                <div class="weibo_list_head">
                    <a>
                        <img class="avatar" src="/upload/microblog/images/head_image/${post_info.avatar}" alt="头像">
                    </a>
                </div>
                <ul>
                    <li><b>${post_info.post.nickname}</b></li>
                    <li><span>${post_info.post.addtime}</span></li>
                    <li><p>${post_info.post.content}</p></li>
                </ul>
            </div>
        </div>

        <div class="send-weibo">
            <div class="ui form" style="overflow: auto">
                <div class="field">
                    <div class="content-title" style="font-size: 16px; color: #d79f34; padding: 8px 0 4px 0;">
                        我来评论~~~
                    </div>
                    <div class="weibo-text">
                        <textarea  name="content" id="saybox_0" onkeyup="checknum(this.value, '0')"  rows="5" ></textarea>
                    </div>
                </div>
            </div>
            <div class="send-action">
                <div class="release">
                    <span class="countTxt">还可输入<em id="sayword_0" class="count">140</em>字</span>
                    <button class="ui teal button" onclick="saysub(0)">发布 </button>
                </div>
            </div>
        </div>

        <!-- 评论显示区域-->
        <h4 class="weibo_list_title" style="margin-top: 10px">全部评论</h4>
        <#if datalists??>
        <#list datalists as vo>
        <div class="weibo_list">
            <div class="weibo_list_top">
                <div class="weibo_list_head">
                    <a>
                        <img class="avatar" src="/upload/microblog/images/head_image/${vo.avatar}" alt="头像">
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
        <#else >
            <div class="empty">还没有评论哦！</div>
        </#if>
    </div>
    <#include "../common/profile.ftl">
</div>
</@override>
<@extends name="../common/common.ftl"/>