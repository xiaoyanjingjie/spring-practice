package jie.dian.wan.config;

import java.io.IOException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wan dianjie
 * @date 2020-10-16 09:36
 */
@Configuration
public class MyRedissionConfig {


  @Bean(destroyMethod="shutdown")
  RedissonClient redisson() throws IOException {

    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    config.useSingleServer().setPassword("1234567");
    return Redisson.create(config);
  }
}
