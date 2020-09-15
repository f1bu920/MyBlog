package com.flbu920.blog.service.Impl;

import com.flbu920.blog.Dao.BlogMapper;
import com.flbu920.blog.Dao.CategoryMapper;
import com.flbu920.blog.Dao.TagMapper;
import com.flbu920.blog.model.Blog;
import com.flbu920.blog.model.Category;
import com.flbu920.blog.model.Tag;
import com.flbu920.blog.service.BlogService;
import com.flbu920.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author flbu920
 * @date 2020/9/1
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private TagMapper tagMapper;
    @Autowired
    private TagService tagService;

    @Value("${blog.pageSize}")
    private int pageSize;

    @Override
    @Transactional
    public int saveBlog(Blog blog) {
        //更新blog对应的category
        Category category = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        //如果不存在就新建
        if (category == null) {
            category = Category.builder().categoryId(blog.getBlogCategoryId())
                    .categoryName(blog.getBlogCategoryName())
                    .categoryRank(1)
                    .createTime(new Date())
                    .categoryIcon("默认图标").build();
            categoryMapper.insertSelective(category);
        } else {
            category.setCategoryRank(category.getCategoryRank() + 1);
            categoryMapper.updateByPrimaryKeySelective(category);
        }
        //更新tags,如果不存在就创建
        String[] tags = blog.getBlogTags().split(",");
        for (String tagName : tags) {
            List<Tag> tagByTagName = tagService.getTagByTagName(tagName);
            //如果不存在就创建
            if (CollectionUtils.isEmpty(tagByTagName)) {
                Tag tag = Tag.builder().tagName(tagName)
                        .tagCount(0L)
                        .build();
                tagByTagName.add(tag);
                tagMapper.insertSelective(tag);
            }
            for (Tag tag : tagByTagName) {
                tag.setTagCount(tag.getTagCount() + 1);
                tagMapper.updateByPrimaryKeySelective(tag);
            }
        }
        return blogMapper.insertSelective(blog);
    }

    @Override
    @Transactional
    public int updateBlog(Blog blog) {
        Blog blogForUpdate = blogMapper.selectByPrimaryKey(blog.getBlogId());
        if (blogForUpdate != null) {
            blogForUpdate.setUpdateTime(new Date());
            int categoryId = blog.getBlogCategoryId();
            int oldCategoryId = blogForUpdate.getBlogCategoryId();
            if (oldCategoryId != categoryId) {
                //更新旧的category的rank值
                Category oldCategory = categoryMapper.selectByPrimaryKey(oldCategoryId);
                oldCategory.setCategoryRank(oldCategory.getCategoryRank() - 1);
                //更新新的category的rank值
                Category category = categoryMapper.selectByPrimaryKey(categoryId);
                //不存在就新增
                if (category == null) {
                    category = Category.builder()
                            .categoryId(categoryId)
                            .categoryName(blog.getBlogCategoryName())
                            .categoryIcon("默认图标")
                            .categoryRank(0)
                            .createTime(new Date())
                            .build();
                    categoryMapper.insertSelective(category);
                }
                category.setCategoryRank(category.getCategoryRank() + 1);
                categoryMapper.updateByPrimaryKeySelective(oldCategory);
                categoryMapper.updateByPrimaryKeySelective(category);
            }
            //更新Tag
            String[] tags = blog.getBlogTags().split(",");
            String[] oldBlogTags = blogForUpdate.getBlogTags().split(",");
            List<Tag> tagForInsert = new ArrayList<>();
            for (String tagName : tags) {
                List<Tag> tagByTagName = tagService.getTagByTagName(tagName);
                if (CollectionUtils.isEmpty(tagByTagName)) {
                    Tag tag = Tag.builder().tagName(tagName).build();
                    tagForInsert.add(tag);
                }
            }
            //新增标签数据
            for (Tag tag : tagForInsert) {
                tag.setTagCount(1L);
                tagMapper.insertSelective(tag);
            }
            Set<String> set = new HashSet<>(Arrays.asList(tags));
            for (String oldTagName : oldBlogTags) {
                //减去的tag的tagCount减一
                if (!set.contains(oldTagName)) {
                    List<Tag> tagByTagName = tagService.getTagByTagName(oldTagName);
                    if (!CollectionUtils.isEmpty(tagByTagName)) {
                        for (Tag tag : tagByTagName) {
                            tag.setTagCount(tag.getTagCount() - 1);
                            tagMapper.updateByPrimaryKeySelective(tag);
                        }
                    }
                }
                //加上的tag的tagCount加一
                Set<String> setOld = new HashSet<>(Arrays.asList(oldBlogTags));
                Set<String> tagNameForInsertSet = new HashSet<>();
                for (Tag tag : tagForInsert) {
                    tagNameForInsertSet.add(tag.getTagName());
                }
                for (String tagName : tags) {
                    //既不在旧tag中，也不是新增加的tag
                    if (!setOld.contains(tagName) && !tagNameForInsertSet.contains(tagName)) {
                        List<Tag> tagByTagName = tagService.getTagByTagName(tagName);
                        if (!CollectionUtils.isEmpty(tagByTagName)) {
                            for (Tag tag : tagByTagName) {
                                tag.setTagCount(tag.getTagCount() + 1);
                                tagMapper.updateByPrimaryKeySelective(tag);
                            }
                        }
                    }
                }
            }

        }
        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    @Override
    @Transactional
    public int deleteBlogById(Long blogId) {
        Blog blog = getBlogById(blogId);
        if (blog != null) {
            int catrgoryId = blog.getBlogCategoryId();
            Category category = categoryMapper.selectByPrimaryKey(catrgoryId);
            category.setCategoryRank(category.getCategoryRank() - 1);
            categoryMapper.updateByPrimaryKeySelective(category);
            //更新tags
            String[] tags = blog.getBlogTags().split(",");
            for (String tagName : tags) {
                List<Tag> tagByTagName = tagService.getTagByTagName(tagName);
                if (!CollectionUtils.isEmpty(tagByTagName)) {
                    for (Tag tag : tagByTagName) {
                        tag.setTagCount(tag.getTagCount() - 1);
                        tagMapper.updateByPrimaryKeySelective(tag);
                    }
                }
            }
        }
        return blogMapper.deleteByPrimaryKey(blogId);
    }

    @Override
    public Blog getBlogById(Long blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    public List<Blog> getBlogsByPage(int page) {
        PageHelper.startPage(page, pageSize);
        return blogMapper.selectAll();
    }

    @Override
    public int getTotalNumOfBlogs() {
        return blogMapper.selectCount(null);
    }

    @Override
    public List<Blog> getBlogsByCategoryId(int categoryId) {
        Blog blog = Blog.builder().blogCategoryId(categoryId).build();
        return blogMapper.select(blog);
    }

    @Override
    public List<Blog> getBlogsByCategoryName(String categoryName) {
        Blog build = Blog.builder().blogCategoryName(categoryName).build();
        return blogMapper.select(build);
    }

    @Override
    public List<Blog> getBlogsByCategoryNameAndPage(String categoryName, int page) {
        PageHelper.startPage(page,pageSize);
        Blog build = Blog.builder().blogCategoryName(categoryName).build();
        return blogMapper.select(build);
    }


}
