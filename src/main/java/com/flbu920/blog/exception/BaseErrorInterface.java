package com.flbu920.blog.exception;

public interface BaseErrorInterface {
    /**
     * 错误码
     */
    int getResultCode();

    /**
     * 错误描述
     */
    String getResultMsg();
}
