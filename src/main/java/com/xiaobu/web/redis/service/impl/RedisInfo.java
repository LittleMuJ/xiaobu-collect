package com.xiaobu.web.redis.service.impl;

import com.xiaobu.web.redis.entity.AbRedisConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: MuRunSen
 * @Date: 2018/10/11 9:52
 */
@Component
public class RedisInfo extends AbRedisConfiguration {

    @Resource(name = "redisObtainSmsRecord")
    private RedisTemplate temple;

    public RedisTemplate getTemple() {
        return temple;
    }

}
