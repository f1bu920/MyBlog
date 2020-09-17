package com.flbu920.blog.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleBlogListVO implements Serializable {
    private Long blogId;

    private String blogTitle;
}
