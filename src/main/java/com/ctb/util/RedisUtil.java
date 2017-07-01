package com.ctb.util;

/**
 * Created by jayway on 2017/6/29.
 */

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis 工具类
 *
 */
@Component
public class RedisUtil {

    protected static ReentrantLock lockJedis = new ReentrantLock();

    protected static Logger logger = Logger.getLogger(RedisUtil.class);

//
//    public static Jedis getJedis(JedisPool jedisPool) {
//        lockJedis.lock();
//        Jedis jedis = null;
//        try {
//            if (jedisPool != null) {
//                jedis = jedisPool.getResource();
//            }
//        } catch (Exception e) {
//            logger.error("Get jedis error : "+e);
//        }finally{
//            jedis.close();
//            lockJedis.unlock();
//        }
//        return jedis;
//    }

    private static JedisPool pool = null;

    /**
     * 构建redis连接池
     *
     *
     * @return JedisPool
     */
    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(1000);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(1000 * 100);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);//10.27.237.232    101.37.28.32  10.29.191.205(online)
            pool = new JedisPool(config, "10.29.191.205", 6379, 10000, "password");
        }
        return pool;
    }
    //多线程安全
    private static  void poolInit() {
        if(pool == null) {
            getPool();
        }
    }
    /**
     * 返还到连接池
     *
     * @param pool
     * @param redis
     */
//    public static void returnResource(JedisPool pool, Jedis redis) {
//        if (redis != null) {
//            pool.returnResourceObject(redis);
//        }
//    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public static String get(String key){
        String value = "";
//        if(pool==null){
//            poolInit();
//        }
        Jedis jedis = null;
        lockJedis.lock();
        try {
            poolInit();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
//            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            jedis.close();
            lockJedis.unlock();
        }

        return value;
    }
}