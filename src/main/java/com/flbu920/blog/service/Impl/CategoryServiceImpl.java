package com.flbu920.blog.service.Impl;

import com.flbu920.blog.Dao.BlogMapper;
import com.flbu920.blog.Dao.CategoryMapper;
import com.flbu920.blog.model.Blog;
import com.flbu920.blog.model.Category;
import com.flbu920.blog.service.BlogService;
import com.flbu920.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author flbu920
 * @Date 2020/9/1
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private BlogMapper blogMapper;
    @Autowired
    private BlogService blogService;

    @Override
    public int saveCategory(Category category) {
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int updateCategory(Category category) {
        if (category != null) {
            //同时更新此category下的blog
            List<Blog> blogs = blogService.getBlogsByCategoryId(category.getCategoryId());
            for (Blog blog : blogs) {
                blog.setBlogCategoryId(category.getCategoryId());
                blog.setBlogCategoryName(category.getCategoryName());
                blogMapper.updateByPrimaryKeySelective(blog);
            }
        }
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int deleteCategoryById(Integer categoryId) {
        List<Blog> blogs = blogService.getBlogsByCategoryId(categoryId);
        //category下有博客内容，不能删除
        if (CollectionUtils.isEmpty(blogs)) {
            return 0;
        }
        return categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }
}
