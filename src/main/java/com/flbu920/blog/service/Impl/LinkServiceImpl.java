package com.flbu920.blog.service.Impl;

import com.flbu920.blog.Dao.LinkMapper;
import com.flbu920.blog.model.Link;
import com.flbu920.blog.service.LinkService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Resource
    private LinkMapper linkMapper;
    @Value("${link.pageSize}")
    private int linkPageSize;

    @Override
    public List<Link> getLinksByPage(Integer page) {
        PageHelper.startPage(linkPageSize, page);
        return linkMapper.selectAll();
    }

    @Override
    public int insertLink(Link link) {
        return linkMapper.insertSelective(link);
    }

    @Override
    public int deleteLinkByPrimaryKey(Integer linkId) {
        return linkMapper.deleteByPrimaryKey(linkId);
    }

    @Override
    public int updateLink(Link link) {
        return linkMapper.updateByPrimaryKeySelective(link);
    }

    @Override
    public Link selectById(Integer linkId) {
        return linkMapper.selectByPrimaryKey(linkId);
    }

    @Override
    public int getTotalLinks() {
        return linkMapper.selectCount(null);
    }
}
