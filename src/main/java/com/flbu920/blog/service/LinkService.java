package com.flbu920.blog.service;

import com.flbu920.blog.model.Link;

import java.util.List;

/**
 * @Author flbu920
 * @Date 2020/9/1
 */
public interface LinkService {
    List<Link> getLinksByPage(Integer page);

    int insertLink(Link link);

    int deleteLinkByPrimaryKey(Integer linkId);

    int updateLink(Link link);

    Link selectById(Integer linkId);

    int getTotalLinks();
}
