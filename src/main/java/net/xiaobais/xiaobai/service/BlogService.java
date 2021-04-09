package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Blog;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:31
 * @Version 1.0
 */
public interface BlogService {

    /**
     * 通过Id查找blog
     * @param id id
     * @return Blog
     */
    Blog findBlogById(Integer id);

    /**
     * 添加博客
     * @param blog blog
     * @return int
     */
    int insertBlog(Blog blog);

    /**
     * 根据标题和内容添加博客
     * @param blogTitle 标题
     * @param blogContent 内容
     * @param blogDes 博客描述
     * @return int
     */
    int insertBlogByTitleAndContent(String blogTitle, String blogContent, String blogDes);

    /**
     * 通过blogId更新博客
     * @param blogId blogId
     * @param blogTitle blogTitle
     * @param blogContent blogContent
     * @param blogDes blogDes
     * @return int
     */
    int updateBlogByBlogId(Integer blogId, String blogTitle, String blogContent, String blogDes);

    /**
     * 根据博客Id删除博客
     * @param blogId blogId
     * @return blogId
     */
    int deleteBlogByBlogId(Integer blogId);
}
