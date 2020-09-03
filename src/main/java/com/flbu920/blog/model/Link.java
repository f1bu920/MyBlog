package com.flbu920.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Link implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer linkId;
    private Byte linkType;
    private String linkName;
    private String linkUrl;
    private String linkDescription;
    private Integer linkRank;
    private Date createTime;
}
