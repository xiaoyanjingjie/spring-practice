package jie.dian.wan.business.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jie.dian.wan.business.model.business.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wan dianjie
 * @date 2019-06-04 15:50
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

  User getMasterdb();

}
