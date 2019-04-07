<@override name="body">
<div class="main">
    <div class="left">
        <h4 class="weibo_list_title">全部收藏</h4>
            <#if datalists??>
                <#list datalists as vo>
                    <div class="weibo_list">
                        <div class="weibo_list_top">
                            <div class="weibo_list_head">
                                <a href="/microblog/friend/home?friend_id=${vo.post.user_id}">
                                    <img class="avatar"   src="/upload/microblog/images/head_image/${vo.avatar}"  />
                                </a>
                            </div>
                            <ul>
                                <li><b>${vo.post.nickname}</b>
                                    <button class="cancel-collect fr weibo_list_head_collect" value=${vo.post.id}>取消收藏</button>
                                </li>
                                <li><span>${vo.post.addtime}</span></li>
                                <li>
                                    <p>
                                        <#if vo.post.post_type==2 && vo.parent_content!="">
                                            ${vo.post.content}//${vo.parent_content}
                                        <#else >
                                            ${vo.post.content}
                                        </#if>
                                    </p>
                                </li>
                            </ul>
                        </div>

                    <#if vo.post.post_type==2>
                        <div class="weibo_list_top" style="background: #F2F2F5">
                        <ul>
                        <li><b>${vo.post.nickname}</b></li>
                        <li><span>${vo.parent.addtime}</span></li>
                        <li>
                        <p>
                        ${vo.parent.content}
                        </p>
                        </li>
                        </ul>
                        </div>
                    </#if>

                        <div class="weibo_list_bottom" style="overflow: auto" value=${vo.post.id} >
                            <!--转发-->
                            <a class="forward" href="javascript:" style="width: 33%;">转发
                                ( ${vo.forward_count} )
                            </a>
                            <!--评论-->
                            <a class="weibo_list_bottom_message" style="width: 33%;">评论
                                ( <span> ${vo.comment_count} </span> )
                            </a>
                            <!--点赞-->
                            <a class="praise" href="javascript:" style="width: 33%;">点赞
                                ( <span> ${vo.praise_count} </span> )
                            </a>
                        </div>
                        <div class="weibo_comment">
                            <!-- 微博回复框开始 -->
                            <div class="send-weibo">
                                <div class="ui form" >
                                    <div class="field">
                                        <div class="weibo-text" style="overflow: auto">
                                            <textarea  name="content" id="saybox_${vo.post.id}" onkeyup="checknum(this.value, ${vo.post.id})"  rows="5" ></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="send-action">
                        <span style="color: #d79f34;">
                            评论~~~
                        </span>
                                    <div class="release">
                                        <span class="countTxt">还可输入<em id="sayword_${vo.post.id}" class="count">140</em>字</span>
                                        <button class="ui teal button" onclick="saysub(${vo.post.id} , 'comment')">发布 </button>
                                    </div>
                                </div>
                            </div>
                            <!-- 微博回复框结束-->

                            <!--评论显示数据开始-->
                            <div class="comment_list"></div>
                            <!--评论显示每条数据结束-->
                        </div>
                    </div>
                </#list>
            <#else >
                <div class="empty">
                    <p>还没有收藏哦！</p>
                </div>
            </#if>
            <#include "../common/page.ftl">
    </div>
    <#include "../common/profile.ftl">
</div>
</@override>
<@extends name="../common/common.ftl"/>