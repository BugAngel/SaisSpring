<#list datalists as vo>
<div class="weibo_list">
    <div class="weibo_list_top">
        <div class="weibo_list_head">
            <a href="/microblog/friend/index?friend_id=${vo.post.user_id}">
                <img class="avatar"   src="/upload/microblog/images/head_image/${vo.avatar}"  />
            </a>
        </div>

        <ul>
            <li><b>${vo.nickname}</b></li>
            <li><span>${vo.post.addtime}</span></li>
            <li>
                <p>
                    <#if vo.post.post_type==2>
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
            <li><b>${vo.nickname}</b></li>
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
        <!--收藏-->
        <a class="collect" href="javascript:">
            <#if vo.collect==1>
                已收藏
            <#else >
                收藏
            </#if>
        </a>
        <!--转发-->
        <a class="forward" href="javascript:">转发
            ( ${vo.post.forward_num} )
        </a>
        <!--评论-->
        <a class="weibo_list_bottom_message">评论
            ( <span> ${vo.post.comment_num} </span> )
        </a>
        <!--点赞-->
        <a class="praise" href="javascript:">点赞
            (<span> ${vo.post.praise_num} </span> )
        </a>
    </div>
    <div class="weibo_comment">
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

        <!--评论显示数据开始-->
        <div class="comment_list"></div>
        <!--评论显示每条数据结束-->
    </div>
</div>
</#list>