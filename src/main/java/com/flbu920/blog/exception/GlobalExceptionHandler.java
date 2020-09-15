package com.flbu920.blog.exception;

import cn.hutool.core.exceptions.StatefulException;
import com.flbu920.blog.util.BlogResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = StatefulException.class)
    public BlogResult statefulExceptionHandler(StatefulException stateException) {
        log.info("处理StatefulException异常");
        return new BlogResult(stateException.getStatus(), stateException.getMessage());
    }
}
