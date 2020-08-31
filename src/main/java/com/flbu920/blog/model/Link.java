package com.flbu920.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Link implements Serializable {
    private Integer linkId;
    private Byte linkType;
    private String linkName;
    private String linkUrl;
    private String linkDescription;
    private Integer linkRank;
    private Date createTime;
}
