package com.flbu920.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Config {

    private String configName;

    private String configValue;

    private Date createTime;

    private Date updateTime;
}
