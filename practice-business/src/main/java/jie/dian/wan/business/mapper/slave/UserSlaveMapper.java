package jie.dian.wan.business.mapper.slave;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jie.dian.wan.business.model.business.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wan dianjie
 * @date 2019-06-04 15:50
 */
@Mapper
public interface UserSlaveMapper extends BaseMapper<User> {

  //@Select("select * from t_s_user")
  User getSlavedb();
}
