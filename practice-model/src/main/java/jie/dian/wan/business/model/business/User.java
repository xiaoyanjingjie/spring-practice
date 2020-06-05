package jie.dian.wan.business.model.business;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import jie.dian.wan.sensitive.Sensitive;
import jie.dian.wan.sensitive.SensitiveType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wan dianjie
 * @date 2019-06-04 15:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_s_user")
public class User extends Model<User> {
  private String id;
  private String email;
  @Sensitive(type = SensitiveType.MOBILE_PHONE)
  private String mobilePhone;
}
