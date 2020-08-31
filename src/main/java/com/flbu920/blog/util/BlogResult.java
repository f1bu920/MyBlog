package com.flbu920.blog.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogResult {
    private int code;
    private String message;
    private Object data;

    public BlogResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BlogResult success() {
        return new BlogResult(200, "操作成功");
    }

    public static BlogResult failure() {
        return new BlogResult(600, "操作失败");
    }

    public static BlogResult success(Object data) {
        return new BlogResult(200, "操作成功", data);
    }

    public static BlogResult failure(String message) {
        return new BlogResult(600, message);
    }
}
