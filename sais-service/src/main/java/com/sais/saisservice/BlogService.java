package com.sais.saisservice;

import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saismapper.CollectMapper;
import com.sais.saismapper.PostMapper;
import com.sais.saismapper.PraiseMapper;
import com.sais.saismapper.UserMapper;
import org.springframework.stereotype.Service;

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

    public IndexBlog getForward(IndexBlog indexBlog,Post post){
        if(post.getPid()!=0 && post.getPost_type()==2){
            Post parent=postMapper.selectPostFromId(post.getPid());
            StringBuilder content=new StringBuilder();
            boolean flag=true;
            while (flag){
                if(parent!=null && parent.getPost_type()==2){
                    content.append("@");
                    content.append(userMapper.selectNicknameFromId(parent.getUser_id()));
                    content.append(":");
                    content.append(parent.getContent());
                    content.append("//");
                    content.append(content);
                    //查找父级
                    parent=postMapper.selectPostFromId(parent.getPid());
                }else {
                    indexBlog.setParent(parent);
                    flag=false;
                }
            }
            if(content.length()>2){
                indexBlog.setParent_content(content.delete(content.length()-2,content.length()-1).toString());
            }
        }
        return indexBlog;
    }

    public IndexBlog setAllCount(IndexBlog indexBlog,int post_id){
        indexBlog.setForward_count(postMapper.getUserForwardNum(post_id));
        indexBlog.setComment_count(postMapper.getUserCommentNum(post_id));
        indexBlog.setPraise_count(praiseMapper.getUserPraiseNum(post_id));
        return indexBlog;
    }

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
