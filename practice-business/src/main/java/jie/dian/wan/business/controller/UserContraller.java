package jie.dian.wan.business.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.HashMap;
import java.util.Map;
import jie.dian.wan.aoplog.LogControllerFilter;
import jie.dian.wan.business.UserContrallerFacade;
import jie.dian.wan.business.model.business.User;
import jie.dian.wan.business.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wan dianjie
 * @date 2020-05-28 14:16
 */
@EnableAsync
@Slf4j
@RestController
@RequestMapping("/user")
public class UserContraller implements UserContrallerFacade {

  @Autowired
  private  UserService userService;


  @LogControllerFilter
  @ResponseBody
  @PostMapping("/getMasterdb")
  public String getMasterdb(@RequestBody User user){
    user = userService.getMasterdb() ;

    if(0==0){
       throw new RuntimeException();
    }
    return user.toString();
  }

  @GetMapping("/getSlavedb")
  public User getSlavedb(){
    return userService.getSlavedb();
  }

  @Override
  public User getUserInfoByName(String mobilePhone) {
    return userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getMobilePhone,mobilePhone));
  }

  /**
   * 测试异步情况下获取mdc
   * @param
   * @return
   */
  @GetMapping("/getAsync")
  public String getAsync(){
    Map map = new HashMap();
    map.put("trace-id","ddddddd");
    MDC.setContextMap(map);
    userService.testAsync();
    return "返回成功！";
  }
}
