package jie.dian.wan.business.model.auth;

import java.io.Serializable;
import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

/**
 * oauth2.0 定义userdetail
 *
 * @author wan dianjie
 * @date 2020-06-03 11:18
 */
@EqualsAndHashCode(callSuper = true)
public class MyAuthUser extends User implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 用户ID
   */
  @Getter
  private String id;


  public MyAuthUser(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username,password,authorities);
    this.id = id;
  }

}
