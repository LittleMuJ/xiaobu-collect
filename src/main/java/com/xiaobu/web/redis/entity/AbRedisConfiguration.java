package com.xiaobu.web.redis.entity;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Author: MuRunSen
 * @Date: 2018/10/11 9:49
 */
public abstract class AbRedisConfiguration {

    protected RedisTemplate temple;

    public void setData(String key, String value) {
        getTemple().opsForValue().set(key, value);
    }

    public void setData(String key, Integer value) {
        getTemple().opsForValue().set(key, value);
    }

    public String getData(String key) {
        return (String) getTemple().opsForValue().get(key);
    }

    public RedisTemplate getTemple() {
        return temple;
    }


    public void delete(String key) {
        getTemple().delete(key);
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return temple.hasKey(key);
    }

    public  void set(String key, String value, long timeout, TimeUnit unit){
        getTemple().opsForValue().set(key,value,timeout,unit);
    }
}
