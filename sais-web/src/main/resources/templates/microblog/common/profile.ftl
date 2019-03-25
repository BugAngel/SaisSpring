<?php
    $sql    = 'select * from mr_user where id = :user_id';
    $user   = $db->row($sql,array('user_id'=>$_SESSION['user']['id']));
?>
<div class="profile">
    <!--    新增右侧个人部分-->
    <div class="my_info">
        <img class="my_info_head" height="90px" width="90px" src="<?php echo get_cover_path($user['avatar']) ?>">
        <h4><?php echo $user['username'] ?></h4>
        <div class="my_info_list">
            <ul>
                <a href="friends.php">
                    <li><span><?php echo $user['follows_num'] ?></span></li>
                    <li>关注</li>
                </a>
            </ul>
            <ol></ol>
            <ul>
                <a href="myFans.php">
                    <li><span><?php echo $user['fans_num'] ?></span></li>
                    <li>粉丝</li>
                </a>
            </ul>
            <ol></ol>
            <ul>
                <a href="myPost.php">
                    <li><span><?php echo $user['posts_num'] ?></span></li>
                    <li>微博</li>
                </a>
            </ul>
        </div>
    </div>
</div>