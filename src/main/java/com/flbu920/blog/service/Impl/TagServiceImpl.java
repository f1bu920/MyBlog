package com.flbu920.blog.service.Impl;

import com.flbu920.blog.Dao.BlogMapper;
import com.flbu920.blog.Dao.TagMapper;
import com.flbu920.blog.model.Blog;
import com.flbu920.blog.model.Tag;
import com.flbu920.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author flbu920
 * @date 2020/9/2
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;
    @Resource
    private BlogMapper blogMapper;
    @Value("${blog.pageSize}")
    private int pagesize;

    @Override
    @Transactional
    public int saveTag(Tag tag) {
        return tagMapper.insertSelective(tag);
    }


    @Override
    @Transactional
    public int deleteTagById(Integer tagId) {
        Tag tag = tagMapper.selectByPrimaryKey(tagId);
        if (tag != null) {
            Blog build = Blog.builder()
                    .blogTags(tag.getTagName())
                    .build();
            List<Blog> blogs = blogMapper.select(build);
            if (!CollectionUtils.isEmpty(blogs)) {
                //此标签下有博客，禁止删除
                return -1;
            }
        }
        return tagMapper.deleteByPrimaryKey(tagId);
    }

    @Override
    public Tag getTagById(Integer tagId) {
        return tagMapper.selectByPrimaryKey(tagId);
    }

    @Override
    public List<Tag> getTagByTagName(String tagName) {
        Tag tag = Tag.builder().tagName(tagName)
                .build();
        return tagMapper.select(tag);
    }

    @Override
    public int getTotalTags() {
        return tagMapper.selectCount(null);
    }

    @Override
    public List<Tag> getTagByPage(Integer page) {
        PageHelper.startPage(page, pagesize);
        return tagMapper.selectAll();
    }
}
