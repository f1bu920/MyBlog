package com.flbu920.blog.model.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flbu920.blog.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDetailVO implements Serializable {
    private Long blogId;

    private String blogTitle;

    private Integer blogCategoryId;

    private String blogCategoryName;

    private String categoryIcon;

    private List<String> blogTags;

    private Byte blogStatus;

    private Long blogViews;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Date updateTime;

    private String content;
}
