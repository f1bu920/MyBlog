package com.flbu920.blog.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Blog implements Serializable {
    @Getter
    @Setter
    private Long blogId;
    @Getter
    @Setter
    private String blogTitle;
    @Getter
    @Setter
    private Integer blogCategoryId;
    @Getter
    @Setter
    private String blogCategoryName;
    @Getter
    @Setter
    private String blogTags;
    @Getter
    @Setter
    private Byte blogStatus;
    @Getter
    @Setter
    private Byte enableComment;
    @Getter
    @Setter
    private Long blogViews;
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @Getter
    @Setter
    private Date updateTime;
    @Getter
    @Setter
    private String content;
}
