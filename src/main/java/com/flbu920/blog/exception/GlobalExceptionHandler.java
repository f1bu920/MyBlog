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
    /**
     * 处理Hutool中定义的StatefulException异常
     *
     * @param stateException
     * @return
     */
    @ExceptionHandler(value = StatefulException.class)
    public BlogResult statefulExceptionHandler(StatefulException stateException) {
        log.error("处理StatefulException异常，原因是" + stateException.getCause());
        return BlogResult.failure(stateException.getStatus(), stateException.getMessage());
    }

    /**
     * 处理空指针异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public BlogResult nullPointExceptionHandler(NullPointerException e) {
        log.error("发生空指针异常，原因是" + e.getCause());
        return BlogResult.failure(CommonEnum.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value = Exception.class)
    public BlogResult exception(Exception e) {
        log.error("处理未知异常：" + e);
        return BlogResult.failure(CommonEnum.INTERNAL_SERVER_ERROR);
    }
}
