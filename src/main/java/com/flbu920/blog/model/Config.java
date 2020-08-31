package com.flbu920.blog.Model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Config {
    @Getter
    @Setter
    private String configName;
    @Getter
    @Setter
    private String configValue;
    @Getter
    @Setter
    private Date createTime;
    @Getter
    @Setter
    private Date updateTime;
}
