package jie.dian.wan.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jie.dian.wan.business.model.business.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * 用户信息
 *
 * @author meng ran
 * @date 2019-03-07 12:45:46
 */
@CacheConfig(cacheNames = "user")
public interface UserService extends IService<User> {

  @Cacheable(key = "2333")
  User getMasterdb();

  User getSlavedb();

  String testAsync();
}

