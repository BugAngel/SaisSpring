<@override name="body">
    <div class="main">
        <div class="left">
            <!-- 引入微博发送框 -->
            <?php include("view/common/send-weibo.php"); ?>

            <h4 class="weibo_list_title">全部微博</h4>
            <?php if(!isset($lists)){ ?>
            <div class="empty">
                <p>还没有微博哦！</p>
            </div>
            <?php }else {
            //微博列表
            include_once("view/common/weibo-list.php");
        } ?>
        </div>
        <!-- 个人信息 -->
        <#include "../common/profile.ftl">
    </div>

</@override>
<@extends name="../common/common.ftl"/>