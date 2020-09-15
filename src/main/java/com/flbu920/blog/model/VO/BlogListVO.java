package com.flbu920.blog.model.VO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BlogListVO implements Serializable {
    private Long blogId;

    private String blogTitle;

    private Integer blogCategoryId;

    private String blogCategoryName;

    private String categoryIcon;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
