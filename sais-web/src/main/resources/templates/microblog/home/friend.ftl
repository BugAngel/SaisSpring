<@override name="body">
<div class="main">
    <div class="left">
        <h4 class="weibo_list_title">全部关注<span>${total}</span></h4>
        <div class="my_friend">
            <#if datalists??>
                <#list datalists as vo>
                    <div class="my_friend_list">
                        <img class="fl" src="/upload/microblog/images/head_image/${vo.avatar}">
                        <ul class="fl">
                            <li>${vo.nickname}</li>
                            <li><span>注册于：${vo.addtime}</span>
                                <span>QQ:${vo.qq}</span>
                            </li>
                        </ul>
                        <button id="cancel-follow" class="fr" value=${vo.id}>取消关注</button>
                    </div>
                </#list>
            <#else >
                还没有关注哦！
            </#if>
            <div class="showPage" style="float:right">
                <div class="cl"></div>
            </div>
        </div>
    </div>
    <#include "../common/profile.ftl">
</div>
</@override>
<@extends name="../common/common.ftl"/>
