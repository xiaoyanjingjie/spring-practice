package jie.dian.wan.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

/**
 * 配置数据源
 *
 * @author wan dianjie
 * @date 2020-06-03 12:59
 */
@Configuration
public class AuthenticationBeanConfig {
  @Autowired
  private DataSource dataSource;

  @Bean
  @ConditionalOnMissingBean(PasswordEncoder.class)
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean(ClientDetailsService.class)
  public ClientDetailsService clientDetails() {
    return new JdbcClientDetailsService(dataSource);

  }
}
