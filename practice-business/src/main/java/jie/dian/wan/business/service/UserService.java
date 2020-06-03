package jie.dian.wan.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jie.dian.wan.business.model.business.User;

/**
 * 用户信息
 *
 * @author meng ran
 * @date 2019-03-07 12:45:46
 */
public interface UserService extends IService<User> {

  User getMasterdb();

  User getSlavedb();
}

