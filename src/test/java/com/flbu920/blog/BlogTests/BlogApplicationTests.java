package com.flbu920.blog.BlogTests;

import com.flbu920.blog.model.Blog;
import com.flbu920.blog.service.BlogService;
import com.flbu920.blog.service.CategoryService;
import com.flbu920.blog.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;

    @Test
    void contextLoads() {
        Blog build = Blog.builder().blogCategoryId(2).blogCategoryName("测试分类2")
                .blogId(2l)
                .blogStatus((byte) 1)
                .blogTitle("测试标题2")
                .createTime(new Date())
                .blogViews(1l)
                .blogTags("测试tag2,测试tag3")
                .content("博客2正文")
                .build();
        blogService.saveBlog(build);
    }

    @Test
    void test1() {
        Blog blogById = blogService.getBlogById(2L);
        System.out.println(blogById);
        List<Blog> blogsByCategoryId = blogService.getBlogsByCategoryId(1);
        for (Blog b : blogsByCategoryId) {
            System.out.println(b);
        }
        List<Blog> blogListByCategoryName = blogService.getBlogsByCategoryName("测试分类1");
        for (Blog blog : blogListByCategoryName) {
            System.out.println(blog);
        }
        System.out.println("测试分页");
        List<Blog> blogsByPage = blogService.getBlogsByPage(2);
        for (Blog blog : blogsByPage) {
            System.out.println(blog);
        }
        int totalNumOfBlogs = blogService.getTotalNumOfBlogs();
        System.out.println("totalNumOfBlogs:" + totalNumOfBlogs);
    }

    @Test
    void testBlogUpdate() {
        Blog blogById = blogService.getBlogById(2L);
//        blogById.setBlogCategoryId(2);
//        blogById.setBlogCategoryName("测试分类2");
//        blogById.setBlogTags("测试tag2,测试tag4");
//        blogService.updateBlog(blogById);
        blogService.deleteBlogById(2L);
    }

}
