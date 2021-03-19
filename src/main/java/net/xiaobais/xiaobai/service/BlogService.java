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
}
