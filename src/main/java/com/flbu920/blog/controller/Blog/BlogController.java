package com.flbu920.blog.controller.Blog;

import cn.hutool.core.exceptions.StatefulException;
import com.flbu920.blog.annotation.PassToken;
import com.flbu920.blog.model.Blog;
import com.flbu920.blog.model.Category;
import com.flbu920.blog.model.Config;
import com.flbu920.blog.model.VO.BlogDetailVO;
import com.flbu920.blog.model.VO.BlogListVO;
import com.flbu920.blog.service.*;
import com.flbu920.blog.util.BlogResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api("博客首页相关")
@PassToken
public class BlogController {
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;
    @Resource
    private ConfigService configService;
    @Resource
    private LinkService linkService;

    @RequestMapping(value = {"/index", "/index.html"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取首页数据")
    public BlogResult index(HttpServletRequest request) {
        return this.page(request, 1);
    }

    @GetMapping("/page/{pageNum}")
    @ApiOperation("获取指定页码数据")
    public BlogResult page(HttpServletRequest request, @PathVariable("pageNum") int pageNum) {
        List<BlogListVO> blogsByPage = blogService.getBlogsByPage(pageNum);
        if (CollectionUtils.isEmpty(blogsByPage)) {
            throw new StatefulException(404, "find no data");
        }
        return BlogResult.success(blogsByPage);
    }

    @GetMapping("/categories")
    @ApiOperation("获取所有分类")
    public BlogResult category() {
        List<Category> allCategories = categoryService.getAllCategories();
        if (CollectionUtils.isEmpty(allCategories)) {
            throw new StatefulException(404, "find no data");
        }
        return BlogResult.success(allCategories);
    }

    @GetMapping("/config")
    @ApiOperation("获取所有页面通用配置信息")
    public BlogResult config() {
        List<Config> allConfig = configService.getAllConfig();
        if (CollectionUtils.isEmpty(allConfig)) {
            throw new StatefulException(404, "find no data");
        }
        return BlogResult.success(allConfig);
    }

    @GetMapping("/blog/{id}")
    @ApiOperation("根据博客id获取详情页面")
    public BlogResult getBlogDetail(@PathVariable("id") Long blogId) {
        BlogDetailVO blogDetail = blogService.getBlogDetail(blogId);
        if (blogDetail == null) {
            throw new StatefulException(404, "find no data");
        }
        return BlogResult.success(blogDetail);
    }
}
