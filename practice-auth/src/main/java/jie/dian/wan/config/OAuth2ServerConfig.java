package jie.dian.wan.config;

import jie.dian.wan.service.PracticeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 密码模式（resource owner password credentials）(为遗留系统设计)(支持refresh token)
 * 授权码模式（authorization code）(正宗方式)(支持refresh token)
 * 简化模式（implicit）(为web浏览器应用设计)(不支持refresh token)
 * 客户端模式（client credentials）(为后台api服务消费者设计)(不支持refresh token)
 * 参考 https://blog.csdn.net/qq_38765404/article/details/88803411
 *
 * https://blog.csdn.net/qq_38765404/article/details/88822281?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1
 * 认证服务器：
 * @author wan dianjie
 * @date 2020-06-02 15:33
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  /**
   * 替换成自己的
   *private UserDetailsService userDetailsService;
   */
  @Autowired
  private PracticeUserDetailsService userDetailsService;

  @Autowired
  private ClientDetailsService clientDetails;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private TokenStore tokenStore;



  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.realm("oauth2-resources") // code授权添加
        .tokenKeyAccess("permitAll()")				// 开启/oauth/token_key验证端口无权限访问
        .checkTokenAccess("isAuthenticated()") // allow check token
        .allowFormAuthenticationForClients()	// 使/oauth/token支持client_id以及client_secret作登录认证
        .passwordEncoder(passwordEncoder);				// 密码编码器

  }
  // 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 认证管理器
        endpoints.authenticationManager(authenticationManager)
        // 允许 GET、POST 请求获取 token，即访问端点：oauth/token
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        // 要使用refresh_token的话，需要额外配置userDetailsService
        endpoints.userDetailsService(userDetailsService)
            // 指定token存储位置
        .tokenStore(tokenStore)
            // 客户端详细信息服务的基本实现 这里使用JdbcClientDetailsService
            .setClientDetailsService(clientDetails);;
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // 内存模式
//    clients.inMemory().withClient("demoApp").secret(bCryptPasswordEncoder.encode("demoAppSecret"))
//        .redirectUris("http://baidu.com")// code授权添加
//        .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
//        .scopes("all").resourceIds("oauth2-resource").accessTokenValiditySeconds(1200)
//        .refreshTokenValiditySeconds(50000);
    // 数据库模式
    clients.withClientDetails(clientDetails); // 表中存储的secret值是加密后的值，并非明文；
  }


}