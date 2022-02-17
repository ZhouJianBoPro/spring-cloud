package cn.com.pro.common.redis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * @author zhoujianbo
 * @Title: redis操作类
 * @Description:
 * @date Created in 2022/1/27 13:52
 **/
@Slf4j
@Component
public class RedisManager {

    private final JedisCluster jedisCluster;

    private static final Long SUCCESS_CODE = 1L;

    public RedisManager(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    /**
     * 添加字符串类型
     * key不存在是设置，存在时不作处理
     * 设置key ttl
     * @param key
     * @param value
     * @param expireSeconds
     * @return boolean
     */
    public boolean setNxEx(String key, String value, long expireSeconds) {

        try {
            String res = jedisCluster.set(key, value, "nx", "ex", expireSeconds);
            return StringUtils.isNotEmpty(res);
        } catch (Exception e) {
            log.error("setNxEx异常, key = {}, value = {}, expireSeconds = {}", key, value, expireSeconds, e);
            return false;
        }
    }

    /**
     * 添加字符串类型
     * @param key
     * @param value
     * @return boolean
     */
    public boolean set(String key, String value) {

        try {
            String res = jedisCluster.set(key, value);
            return StringUtils.isNotEmpty(res);
        } catch (Exception e) {
            log.error("set异常, key = {}, value = {}", key, value, e);
            return false;
        }
    }

    /**
     * 获取缓存值
     * @param key
     * @return String
     */
    public String get(String key) {

        try {
            return jedisCluster.get(key);
        } catch (Exception e) {
            log.error("get异常, key = {}", key, e);
            return null;
        }
    }

    /**
     * 删除指定key
     * @param key
     * @return boolean
     */
    public boolean del(String key) {

        try {
            return jedisCluster.del(key) > 0;
        } catch (Exception e) {
            log.error("del异常, key = {}", key, e);
            return false;
        }
    }

    /**
     * 获取分布式锁
     * 当且仅当key不存在
     * @param key
     * @param value
     * @param seconds
     * @return boolean
     */
    public boolean lock(String key, String value, long seconds) {

        try {
            String res = jedisCluster.set(key, value, "nx", "ex", seconds);
            return StringUtils.isNotEmpty(res);
        } catch (Exception e) {
            log.error("lock异常, key = {}, value = {}, seconds = {}", key, value, seconds, e);
            return false;
        }
    }

    /**
     * 释放锁
     * @param key
     * @param value
     * @return boolean
     */
    public boolean unlock(String key, String value) {

        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object object = jedisCluster.eval(script, Collections.singletonList(key), Collections.singletonList(value));
            return SUCCESS_CODE.equals(object);
        } catch (Exception e) {
            log.error("unlock异常, key = {}, value = {}", key, value, e);
            return false;
        }
    }

    /**
     * 获取自增后的值
     * @param key
     * @return String
     */
    public Long incr(String key) {

        try {
            return jedisCluster.incr(key);
        } catch (Exception e) {
            log.error("incr异常, key = {}", key, e);
            return 0L;
        }
    }

    /**
     * 获取自增后的值(hash类型)
     * @param key
     * @param field
     * @return Long
     */
    public Long hIncr(String key, String field) {

        try {
            return jedisCluster.hincrBy(key, field, 1L);
        } catch (Exception e) {
            log.error("hIncr异常, key = {}, field = {}", key, field, e);
            return 0L;
        }
    }

    /**
     * 添加hash类型, 保存单个键值对
     * @param hashKey
     * @param field
     * @param value
     * @return boolean
     */
    public boolean hSet(String hashKey, String field, String value) {

        try {
            Long res = jedisCluster.hset(hashKey, field, value);
            return res > 0;
        } catch (Exception e) {
            log.error("hSet异常, hashKey = {}, field = {}, value = {}", hashKey, field, value, e);
            return false;
        }
    }

    /**
     * 添加hash类型, 保存单个键值对, 当field不存在时新增
     * @param hashKey
     * @param field
     * @param value
     * @return boolean
     */
    public boolean hSetNx(String hashKey, String field, String value) {

        try {
            Long res = jedisCluster.hsetnx(hashKey, field, value);
            return res > 0;
        } catch (Exception e) {
            log.error("hSetNx异常, hashKey = {}, field = {}, value = {}", hashKey, field, value, e);
            return false;
        }
    }

    /**
     * 添加hash类型, 可保存多个键值对
     * @param hashKey
     * @param valueMap
     * @return boolean
     */
    public boolean hmSet(String hashKey, Map<String, String> valueMap) {

        try {
            String res = jedisCluster.hmset(hashKey, valueMap);
            return StringUtils.isNotEmpty(res);
        } catch (Exception e) {
            log.error("hmSet异常, hashKey = {}, valueMap = {}", hashKey, JSONObject.toJSONString(valueMap), e);
            return false;
        }
    }

    /**
     * 获取单个field对应value
     * @param hashKey
     * @param field
     * @return String
     */
    public String hGet(String hashKey, String field) {

        try {
            return jedisCluster.hget(hashKey, field);
        } catch (Exception e) {
            log.error("hGet异常, hashKey = {}, field = {}", hashKey, field, e);
            return null;
        }
    }

    /**
     * 获取所有给定字段的值
     * @param hashKey
     * @param fields
     * @return List<String>
     */
    public List<String> hmGet(String hashKey, List<String> fields) {

        try {
            return jedisCluster.hmget(hashKey, fields.toArray(new String[fields.size()]));
        } catch (Exception e) {
            log.error("hmGet异常, hashKey = {}, fields = {}", hashKey, JSONObject.toJSONString(fields), e);
            return new ArrayList<>();
        }
    }

    /**
     * 获取hashKey中所有键值对
     * @param hashKey
     * @return
     */
    public Map<String, String> hGetAll(String hashKey) {

        try {
            return jedisCluster.hgetAll(hashKey);
        } catch (Exception e) {
            log.error("hGetAll异常, hashKey = {}", hashKey, e);
            return null;
        }
    }

    /**
     * 并发检查
     * @param key
     * @param maxFrequency 最大访问频率
     * @param expireSeconds
     * @return boolean
     */
    public boolean concurrentCheck(String key, Integer maxFrequency, Integer expireSeconds) {

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("local result = redis.call('set', KEYS[1], 1, 'NX', 'EX', tonumber(ARGV[2])) if (result) then return 1 end ")
                    .append("local current = tonumber(redis.call('get', KEYS[1])) if (current == nil) then redis.call('incr', KEYS[1]) redis.call('expire', KEYS[1], tonumber(ARGV[2])) return 1 end ")
                    .append("if (current >= tonumber(ARGV[1])) then return 0 end redis.call('incr', KEYS[1]) return 1");

            List<String> args = Arrays.asList(String.valueOf(maxFrequency), String.valueOf(expireSeconds));
            Object object = jedisCluster.eval(sb.toString(), Collections.singletonList(key), args);
            return SUCCESS_CODE.equals(object);
        } catch (Exception e) {
            log.error("concurrentCheck异常, key = {}, maxFrequency = {}, expireSeconds = {}", key, maxFrequency, expireSeconds, e);
            return false;
        }
    }

}
