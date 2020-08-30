package com.flbu920.blog.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Tag implements Serializable {
    @Setter
    @Getter
    private Integer tagId;
    @Setter
    @Getter
    private String tagName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Setter
    @Getter
    private Date createTime;
}
