package com.sais.saisservice;

import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saismapper.CollectMapper;
import com.sais.saismapper.PostMapper;
import com.sais.saismapper.PraiseMapper;
import com.sais.saismapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 帖子的操作服务
 */
@Service
public class BlogService {
    private PostMapper postMapper;
    private UserMapper userMapper;
    private PraiseMapper praiseMapper;
    private CollectMapper collectMapper;

    public BlogService(PostMapper postMapper, UserMapper userMapper,PraiseMapper praiseMapper,CollectMapper collectMapper){
        this.postMapper=postMapper;
        this.userMapper=userMapper;
        this.praiseMapper=praiseMapper;
        this.collectMapper=collectMapper;
    }

    /**
     *
     * @param indexBlog 用于包装post
     * @param post 提供需要展示转发信息的post
     * @return 一个IndexBlog对象，包含转发功能所需要的信息
     */
    public IndexBlog getForward(IndexBlog indexBlog,Post post){
        if(post.getPid()!=0 && post.getPost_type()==2){ //如果帖子有父帖子，并且是转发类型
            Post parent=postMapper.selectPostFromId(post.getPid());//根据父帖子ID找到父帖子
            StringBuilder content=new StringBuilder();
            boolean flag=true;
            while (flag){
                if(parent!=null && parent.getPost_type()==2){ //如果父帖子同样是转发自别的帖子
                    //下面是字符串组装，格式为: 内容@用户名:父帖子内容//原内容
                    content=new StringBuilder(content+"@"+userMapper.selectNicknameFromId(parent.getUser_id())+":"
                            +parent.getContent()+"//");
                    //查找父级
                    parent=postMapper.selectPostFromId(parent.getPid());
                }else {
                    indexBlog.setParent(parent);
                    flag=false;
                }
            }
            if(content.length()>2){
                indexBlog.setParent_content(content.delete(content.length()-2,content.length()).toString());
            }else {
                indexBlog.setParent_content("");
            }
        }
        return indexBlog;
    }

    /**
     *
     * @param indexBlog 需要设置的对象
     * @param post_id 帖子ID
     * @return 设置好的对象
     */
    public IndexBlog setAllCount(IndexBlog indexBlog,int post_id){
        indexBlog.setForward_count(postMapper.getUserForwardNum(post_id));
        indexBlog.setComment_count(postMapper.getUserCommentNum(post_id));
        indexBlog.setPraise_count(praiseMapper.getUserPraiseNum(post_id));
        return indexBlog;
    }

    /**
     *
     * @param indexBlog 需要设置的对象
     * @param user_id 用户id
     * @param post_id 帖子ID
     * @return 设置好的对象
     */
    public IndexBlog setCollect(IndexBlog indexBlog,int user_id,int post_id){
        Integer integer=collectMapper.selectCollectStatus(user_id,post_id);
        if(integer==null){
            indexBlog.setCollect(0);
        }else {
            indexBlog.setCollect(integer);
        }
        return indexBlog;
    }
}
