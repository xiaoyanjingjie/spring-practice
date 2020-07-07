package jie.dian.wan.business.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import jie.dian.wan.dubboservice.user.DubboUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * dubbo 实现
 *
 * @author wan dianjie
 * @date 2020-07-07 11:53
 */
@Slf4j
@Service(version = "${demo.service.version}")
@Component
public class DubboUserServiceImpl implements DubboUserService {

  @Override
  public String getUserInfo(String name) {
    return "helleo ，我是provider 返回的"+name;
  }
}
