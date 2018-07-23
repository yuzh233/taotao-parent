import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import xyz.taotao.redis.JedisClient;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/14 22:51
 */
public class TestJedisCluster {

    @Test
    public void testJedisClient() throws Exception {
        //初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
        //从容器中获得JedisClient对象（接口）
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("testJedis", "500");
        String result = jedisClient.get("testJedis");
        System.out.println(result);
    }

    @Test
    public void testJedisCluster() throws Exception {
        // 第一步：使用JedisCluster对象。需要一个Set<HostAndPort>参数。Redis节点的列表。
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.184.130", 7001));
        nodes.add(new HostAndPort("192.168.184.130", 7002));
        nodes.add(new HostAndPort("192.168.184.130", 7003));
        nodes.add(new HostAndPort("192.168.184.130", 7004));
        nodes.add(new HostAndPort("192.168.184.130", 7005));
        nodes.add(new HostAndPort("192.168.184.130", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        // 第二步：直接使用JedisCluster对象操作redis。在系统中单例存在。
        jedisCluster.set("hello", "200");
        String result = jedisCluster.get("hello");
        // 第三步：打印结果
        System.out.println(result);
        // 第四步：系统关闭前，关闭JedisCluster对象。
        jedisCluster.close();
    }

    @Test
    public void testJedisPool() throws Exception {
        // 第一步：创建一个JedisPool对象。需要指定服务端的ip及端口。
        JedisPool jedisPool = new JedisPool("192.168.184.130", 6379);
        // 第二步：从JedisPool中获得Jedis对象。
        Jedis jedis = jedisPool.getResource();
        // 第三步：使用Jedis操作redis服务器。
        jedis.set("jedis", "test");
        String result = jedis.get("jedis");
        System.out.println(result);
        // 第四步：操作完毕后关闭jedis对象，连接池回收资源。
        jedis.close();
        // 第五步：关闭JedisPool对象。
        jedisPool.close();
    }


}
