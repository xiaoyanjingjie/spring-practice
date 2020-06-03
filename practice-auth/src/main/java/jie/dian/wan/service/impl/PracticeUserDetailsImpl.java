package jie.dian.wan.service.impl;

import java.util.Collections;
import jie.dian.wan.business.model.auth.MyAuthUser;
import jie.dian.wan.service.PracticeUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService 实现
 *
 * @author wan dianjie
 * @date 2020-06-03 10:22
 */
@Service("userDetailsService")
@Slf4j
public class PracticeUserDetailsImpl implements PracticeUserDetailsService {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RedisTemplate redisTemplate;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.debug("当前登陆用户名为:{}", username);
    String password = passwordEncoder.encode("123456");
    log.debug("当前登陆用户名密码为:{}", password);
    /**
     * isEnabled 账户是否启用
     * isAccountNonExpired 账户没有过期
     * isCredentialsNonExpired 身份认证是否是有效的
     * isAccountNonLocked 账户没有被锁定
     * 对于 isAccountNonLocked 和 isEnabled 没有做业务处理，只是抛出了对于的异常信息；
     */

    // 赋予一个admin权限

    // User admin = new User(username, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    return new MyAuthUser("",username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
  }
}
