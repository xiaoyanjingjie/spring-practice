package jie.dian.wan.business.controller;

import jie.dian.wan.business.model.business.User;
import jie.dian.wan.business.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wan dianjie
 * @date 2020-05-28 14:16
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserContraller {

  private final UserService userService;

  @GetMapping("/getMasterdb")
  public User getMasterdb(){
     return userService.getMasterdb();
  }

  @GetMapping("/getSlavedb")
  public User getSlavedb(){
    return userService.getSlavedb();
  }
}
