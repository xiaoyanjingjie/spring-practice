package jie.dian.wan.sentinelclient.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wan dianjie
 * https://blog.csdn.net/noaman_wgs/article/details/103328793
 * @date 2020-05-11 18:27
 */
@Configuration
public class SentinelAspectConfiguration {

  @Bean
  public SentinelResourceAspect sentinelResourceAspect() {
    return new SentinelResourceAspect();
  }

}
