package com.flbu920.blog.service;

import com.flbu920.blog.model.Config;
import com.flbu920.blog.util.BlogResult;

import java.util.List;

/**
 * @Author flbu920
 * @Date 2020/9/1
 */
public interface ConfigService {
    int updateConfig(String configName,String configValue);

    List<Config> getAllConfig();
}
