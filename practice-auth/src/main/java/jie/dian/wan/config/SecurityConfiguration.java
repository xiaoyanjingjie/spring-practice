package jie.dian.wan.config;

import jie.dian.wan.service.PracticeUserDetailsService;
import jie.dian.wan.service.impl.PracticeUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 安全配置
 *
 * @author wan dianjie
 * @date 2020-06-02 16:16
 */

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private RedisConnectionFactory connectionFactory;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private PracticeUserDetailsService userDetailsService;

  /**
   * anyRequest          |   匹配所有请求路径
   * access              |   SpringEl表达式结果为true时可以访问
   * anonymous           |   匿名可以访问
   * denyAll             |   用户不能访问
   * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
   * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
   * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
   * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
   * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
   * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
   * permitAll           |   用户可以任意访问
   * rememberMe          |   允许通过remember-me登录的用户访问
   * authenticated       |   用户登录后可访问
   */

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**").and().authorizeRequests()
        .antMatchers("/oauth/**").authenticated().and().formLogin().permitAll();
  }

  // 配置内存模式的用户
//  @Bean
//  @Override
//  protected UserDetailsService userDetailsService() {
//    //TODO 改造成从数据库中获取数据 然后用jwt加密 构建 UserDetailsService
//    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//    manager.createUser(User.withUsername("demoUser1").password(this.passwordEncoder().encode("123456"))
//        .authorities("USER").build());
//    manager.createUser(User.withUsername("demoUser2").password(this.passwordEncoder().encode("123456"))
//        .authorities("USER").build());
//    return manager;
//  }

  @Bean
  public TokenStore tokenStore() {
    // 使用redis存储token信息
    RedisTokenStore redisTokenStore = new RedisTokenStore(connectionFactory);
    return redisTokenStore;

    // 使用jwt内存存储token信息
//		JwtTokenStore jwtTokenStore = new JwtTokenStore(accessTokenConverter());
//		return jwtTokenStore;
  }

//  @Bean
//  public JwtAccessTokenConverter accessTokenConverter() {
//    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//    converter.setSigningKey("healthy");
//    return converter;
//  }

  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    return defaultTokenServices;
  }

  /**
   * 需要配置这个支持password模式 support password grant typ
   * @return
   * @throws Exception
   */
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  public static void main(String[] args) {
    String encode = new BCryptPasswordEncoder().encode("demoAppSecret");
    System.out.println(encode);
  }

}
