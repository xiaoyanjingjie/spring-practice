package jie.dian.wan.business.service.impl;

import jie.dian.wan.business.mapper.master.UserMapper;
import jie.dian.wan.business.mapper.slave.UserSlaveMapper;
import jie.dian.wan.business.model.business.User;
import jie.dian.wan.business.service.UserService;
import jie.dian.wan.common.data.base.EcBaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户信息
 *
 * @author meng ran
 * @date 2019-03-07 12:45:46
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl extends EcBaseServiceImpl<UserMapper, User> implements UserService {

  private final UserMapper userMapper;
  private final UserSlaveMapper userSlaveMapper;
  @Override
  public User getMasterdb() {
    return userMapper.getMasterdb();
  }

  @Override
  public User getSlavedb() {
    return userSlaveMapper.getSlavedb();
  }
}
