package com.flbu920.blog.service;

import com.flbu920.blog.model.Blog;
import com.flbu920.blog.model.VO.BlogDetailVO;
import com.flbu920.blog.model.VO.BlogListVO;
import com.flbu920.blog.util.BlogResult;

import java.util.List;

/**
 * @Author flbu920
 * @Date 2020/9/1
 */
public interface BlogService {
    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlogById(Long blogId);

    Blog getBlogById(Long blogId);

    /**
     * 根据page获取数据
     *
     * @param page 页数
     * @return
     */
    List<BlogListVO> getBlogsByPage(int page);

    /**
     * 获取博客总数量
     *
     * @return
     */
    int getTotalNumOfBlogs();

    List<Blog> getBlogsByCategoryId(int categoryId);

    List<BlogListVO> getBlogsByCategoryName(String categoryName);

    List<BlogListVO> getBlogsByCategoryNameAndPage(String categoryName, int page);

    /**
     * 根据博客id获取详情
     *
     * @param blogId
     * @return
     */
    BlogDetailVO getBlogDetail(Long blogId);
}
