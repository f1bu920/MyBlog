package com.flbu920.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Config {
    @Id
    private String configName;

    private String configValue;

    private Date createTime;

    private Date updateTime;
}
