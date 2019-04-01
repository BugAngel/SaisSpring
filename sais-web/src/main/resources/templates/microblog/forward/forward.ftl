<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body style="background-color: #f2f2f2">
<!-- 引入头部菜单-->
<div class="main">
    <div class="left weibo_list_forward">
        <div class="weibo_list " style="box-shadow: 2px 2px 5px rgba(0,0,0,0);">
            <div class="weibo_comment" style="overflow: hidden; display: block;box-shadow: 2px 2px 5px rgba(0,0,0,0);">
                <!-- 微博回复框开始 -->
                <div class="send-weibo">
                    <div class="ui form" style="">
                        <div class="field">
                            <div class="weibo-text">
                                <textarea name="content" id="saybox_${RequestParameters['pid']}" onkeyup="checknum(this.value, '0')" rows="5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="send-action">
                        <span style="color: #d79f34;">
                            最新转发~~~
                        </span>
                        <div class="release">
                            <span class="countTxt">还可输入<em id="sayword_0" class="count">140</em>字</span>
                            <button class="ui teal button" onclick="saysub(${RequestParameters['pid']} , 'forward')">转发</button>
                        </div>
                    </div>
                </div>
                <!-- 微博回复框结束-->

                <!--转发数据开始-->
                <div class="comment_list">
                    <#list datalists as vo>
                    <div class="weibo_list">
                        <div class="weibo_list_top">
                            <div class="weibo_list_head">
                                <a href="<?php echo "homepage.php?friend_id=".$v['user_id'] ?>">
                                    <img class="avatar"   src="/upload/microblog/images/head_image/${vo.avatar}"  />
                                </a>
                            </div>
                            <ul>
                                <li><b>${vo.nickname}</b></li>
                                <li><span>${vo.post.addtime}</span></li>
                                <li><p>${vo.post.content}</p></li>
                            </ul>
                        </div>
                    </div>
                    </#list>
                    <#--<#if total gt 2>-->
                    <div class="weibo_comment_more">
                        <a class="more-forward"  value=${RequestParameters['pid']}>
                            查看所有${total}条转发
                        </a>
                    </div>
                    <#--</#if>-->
                </div>
                <!--转发数据结束-->
            </div>
        </div>
    </div>
</div>
</body>
</html>