package com.flbu920.blog.service;

import com.flbu920.blog.model.Blog;
import com.flbu920.blog.model.Tag;
import com.flbu920.blog.util.BlogResult;

import java.util.List;

/**
 * @Author flbu920
 * @Date 2020/9/1
 */
public interface TagService {
    int  saveTag(Tag tag);

    int deleteTagById(Integer tagId);

    Tag getTagById(Integer tagId);

    List<Tag> getTagByTagName(String tagName);

    int getTotalTags();

    List<Tag> getTagByPage(Integer page);
}
