package jie.dian.wan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 *
 * 初学者很容易看错，如果没有看到spring或者JUC源码的人肯定是不太了解的。
 *
 * ThreadPoolTaskExecutor是spring core包中的，而ThreadPoolExecutor是JDK中的JUC。ThreadPoolTaskExecutor是对ThreadPoolExecutor进行了封装处理。
 *
 * 自己在之前写多线程代码的时候都是这么玩的executor=Executors.newCachedThreadPool();但是有一次在大量数据的时候由于入库速度远大于出库速度导致内存急剧膨胀最后悲剧了重写代码，原来spring 早就给我们做好封装了。
 *
 * 来看一下ThreadPoolExecutor结构，祖类都是调用Executor接口：
 * @author wan dianjie
 * @date 2020-07-22 14:12
 */
@Configuration
@ConfigurationProperties(prefix = "spring.threadpool")
@Data
public class ThreadPoolExecutorConfig {

  private int threadPoolCoreSize;
  private int threadPoolKeepAliveSeconds;
  private int threadPoolMaxSize;
  private int threadPoolQueueCapacity;


  @Bean(name = "taskExecutor")
  public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    //线程池维护线程的最少数量
    threadPoolTaskExecutor.setCorePoolSize(threadPoolCoreSize);
    //允许的空闲时间
    threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolKeepAliveSeconds);
    //线程池维护线程的最大数量
    threadPoolTaskExecutor.setMaxPoolSize(threadPoolMaxSize);
    //缓存队列
    threadPoolTaskExecutor.setQueueCapacity(threadPoolQueueCapacity);
    return threadPoolTaskExecutor;
  }

}
