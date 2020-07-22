package jie.dian.wan.business.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import jie.dian.wan.business.mapper.master.UserMapper;
import jie.dian.wan.business.mapper.slave.UserSlaveMapper;
import jie.dian.wan.business.model.business.User;
import jie.dian.wan.business.service.UserService;
import jie.dian.wan.common.data.base.EcBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息
 *
 * @author meng ran
 * @date 2019-03-07 12:45:46
 */
@Service()
@Slf4j
public class UserServiceImpl extends EcBaseServiceImpl<UserMapper, User> implements UserService {

  @Autowired
  private  UserMapper userMapper;
  @Autowired
  private  UserSlaveMapper userSlaveMapper;

  @SentinelResource(value = "getMasterdb")
  @Override
  public User getMasterdb() {
    return userMapper.getMasterdb();
  }

  @Override
  public User getSlavedb() {
    return userSlaveMapper.getSlavedb();
  }

}
