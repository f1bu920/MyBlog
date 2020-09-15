package com.flbu920.blog.controller.Blog;

import cn.hutool.core.exceptions.StatefulException;
import com.flbu920.blog.annotation.PassToken;
import com.flbu920.blog.model.Blog;
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
        List<Blog> blogsByPage = blogService.getBlogsByPage(pageNum);
        if (CollectionUtils.isEmpty(blogsByPage)) {
            throw new StatefulException(404,"find no data");
        }
        return BlogResult.success(blogsByPage);
    }
}
