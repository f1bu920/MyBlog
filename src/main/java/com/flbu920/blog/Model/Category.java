package com.flbu920.blog.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Category implements Serializable {
    @Getter
    @Setter
    private Integer categoryId;
    @Getter
    @Setter
    private String categoryName;
    @Getter
    @Setter
    private String categoryIcon;
    @Getter
    @Setter
    private Integer categoryRank;
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
