package com.flbu920.blog.util;

import com.flbu920.blog.exception.BaseErrorInterface;
import com.flbu920.blog.exception.CommonEnum;
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
        return new BlogResult(CommonEnum.SUCCESS.getResultCode(), CommonEnum.SUCCESS.getResultMsg());
    }

    public static BlogResult success(Object data) {
        return new BlogResult(CommonEnum.SUCCESS.getResultCode(), CommonEnum.SUCCESS.getResultMsg(), data);
    }

    public static BlogResult failure() {
        return new BlogResult(600, "操作失败");
    }

    public static BlogResult failure(String message) {
        return new BlogResult(CommonEnum.BODY_NOT_MATCH.getResultCode(), message);
    }

    public static BlogResult failure(int code, String msg) {
        return new BlogResult(code, msg);
    }

    public static BlogResult failure(BaseErrorInterface baseErrorInterface) {
        return new BlogResult(baseErrorInterface.getResultCode(), baseErrorInterface.getResultMsg());
    }
}
