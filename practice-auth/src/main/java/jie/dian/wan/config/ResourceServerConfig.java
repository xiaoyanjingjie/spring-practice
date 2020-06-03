package jie.dian.wan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器
 *
 * @author wan dianjie
 * @date 2020-06-02 16:14
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/oauth/confirm_access").permitAll()
        .antMatchers("/**/*.js").permitAll()
        .antMatchers("/favicon.ico").permitAll()
        .and()
        .requestMatchers().antMatchers("/api/**").and().authorizeRequests().antMatchers("/api/**")
        .authenticated();
  }

}