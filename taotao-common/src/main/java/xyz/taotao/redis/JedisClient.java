package xyz.taotao.redis;

/**
 * Created with IntelliJ IDEA.
 * Jedis接口
 * @Author: yu_zh
 * @DateTime: 2018/07/14 23:32
 */
public interface JedisClient {

    String set(String key, String value);
    String get(String key);
    Boolean exists(String key);
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String field, String value);
    String hget(String key, String field);
    Long hdel(String key, String... field);

}