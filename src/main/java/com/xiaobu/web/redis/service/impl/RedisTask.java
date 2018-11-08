package com.xiaobu.web.redis.service.impl;

import com.xiaobu.web.redis.entity.AbRedisConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: MuRunSen
 * @Date: 2018/10/11 10:15
 */
@Component
public class RedisTask extends AbRedisConfiguration {

    @Resource(name = "redisTaskRecord")
    private RedisTemplate temple;

    public RedisTemplate getTemple() {
        return temple;
    }
}
