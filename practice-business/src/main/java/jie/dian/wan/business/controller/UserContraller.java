package jie.dian.wan.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.List;
import jie.dian.wan.business.UserContrallerFacade;
import jie.dian.wan.business.model.business.User;
import jie.dian.wan.business.service.UserDubboService;
import jie.dian.wan.business.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wan dianjie
 * @date 2020-05-28 14:16
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserContraller implements UserContrallerFacade {

  @Autowired
  private  UserService userService;



  @PostMapping("/getMasterdb")
  public User getMasterdb(@RequestBody User user){
     return userService.getMasterdb();
  }

  @GetMapping("/getSlavedb")
  public User getSlavedb(){
    return userService.getSlavedb();
  }

  @Override
  public User getUserInfoByName(String mobilePhone) {
    return userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getMobilePhone,mobilePhone));
  }

  @GetMapping("/getDubbo")
  public List getDubbo() {
    return userService.getUserInfo();
  }
}
