package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.BlogMapper;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:33
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public Blog findBlogById(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertBlog(Blog blog) {
        int insert = blogMapper.insert(blog);
        return insert == -1 ? -1 : blog.getBlogId();
    }

    @Override
    public int insertBlogByTitleAndContent(String blogTitle, String blogContent, String blogDes) {
        Blog blog = new Blog();
        blog.setBlogTitle(blogTitle);
        blog.setBlogContent(blogContent);
        blog.setBlogDes(blogDes);
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());
        blog.setIsComment(false);
        int insert = blogMapper.insert(blog);
        return insert == -1 ? -1 : blog.getBlogId();
    }

    @Override
    public int updateBlogByBlogId(Integer blogId, String blogTitle, String blogContent, String blogDes) {
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog.setBlogTitle(blogTitle);
        blog.setBlogContent(blogContent);
        blog.setBlogDes(blogDes);
        blog.setUpdateDate(new Date());
        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    @Override
    public int deleteBlogByBlogId(Integer blogId) {
        return blogMapper.deleteByPrimaryKey(blogId);
    }


}
