package com.flbu920.blog.service.Impl;

import com.flbu920.blog.Dao.BlogMapper;
import com.flbu920.blog.Dao.CategoryMapper;
import com.flbu920.blog.model.Blog;
import com.flbu920.blog.model.Category;
import com.flbu920.blog.service.BlogService;
import com.flbu920.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author flbu920
 * @date 2020/9/1
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private BlogMapper blogMapper;
    @Autowired
    private BlogService blogService;

    @Override
    @Transactional
    public int saveCategory(Category category) {
        return categoryMapper.insertSelective(category);
    }

    @Override
    @Transactional
    public int updateCategory(Category category) {
        if (category != null) {
            Category oldCategory = categoryMapper.selectByPrimaryKey(category.getCategoryId());
            if (!category.getCategoryName().equals(oldCategory.getCategoryName())) {
                List<Blog> blogs = blogService.getBlogsByCategoryId(category.getCategoryId());
                for (Blog blog : blogs) {
                    blog.setBlogCategoryId(category.getCategoryId());
                    blog.setBlogCategoryName(category.getCategoryName());
                    blogMapper.updateByPrimaryKeySelective(blog);
                }
            }
        }
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    @Transactional
    public int deleteCategoryById(Integer categoryId) {
        List<Blog> blogs = blogService.getBlogsByCategoryId(categoryId);
        //category下有博客内容，不能删除
        if (!CollectionUtils.isEmpty(blogs)) {
            log.info("category下有博客内容，不能删除");
            return 0;
        }
        return categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }
}
