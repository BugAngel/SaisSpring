<@override name="body">
    <div class="main">
    <div class="left">
        <h4 class="weibo_list_title">粉丝<span>${total}</span></h4>
        <div class="my_fans my_friend">
    <#if datalists??>
        <#list datalists as vo>
            <div class="my_fans_list">
                <img class="fl" src="/upload/microblog/images/head_image/${vo.avatar}">
                <ul class="fl">
                    <li><?php echo $v['username'] ?></li>
                    <li>
                        <span>关注</span><span style="color:#fe862c;">${vo.follows_num}</span><span>|</span>
                        <span>粉丝</span><span style="color:#fe862c;">${vo.fans_num}</span><span>|</span>
                        <span>发帖</span><span style="color:#fe862c;">${vo.posts_num}</span>
                    </li>
                    <li><span>注册于：${vo.addtime}</span>
                        <span>QQ:${vo.qq}</span>
                    </li>
                </ul>
            </div>
        </#list>
    <#else >
        还没有粉丝哦！
    </#if>
            <!--分页开始-->
    <#include "../common/page.ftl">
            <!--分页结束-->
        </div>
    </div>
    <#include "../common/profile.ftl">
    </div>
</@override>
<@extends name="../common/common.ftl"/>