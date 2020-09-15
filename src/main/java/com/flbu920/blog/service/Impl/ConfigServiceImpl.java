package com.flbu920.blog.service.Impl;

import com.flbu920.blog.Dao.ConfigMapper;
import com.flbu920.blog.model.Config;
import com.flbu920.blog.service.ConfigService;
import com.flbu920.blog.util.BlogResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService {
    @Resource
    private ConfigMapper configMapper;

    @Override
    public int updateConfig(String configName, String configValue) {
        Config config = configMapper.selectByPrimaryKey(configName);
        if (config == null) {
            config = Config.builder()
                    .configName(configName)
                    .configValue(configValue)
                    .createTime(new Date())
                    .build();
            return configMapper.insertSelective(config);
        }
        config.setConfigValue(configValue);
        return configMapper.updateByPrimaryKey(config);
    }

    @Override
    public List<Config> getAllConfig() {
        return configMapper.selectAll();
    }
}
