package com.flbu920.blog.service;

import com.flbu920.blog.util.BlogResult;

/**
 * @Author flbu920
 * @Date 2020/9/1
 */
public interface ConfigService {
    BlogResult updateConfig(String configName,String configValue);

    BlogResult getAllConfig();
}
