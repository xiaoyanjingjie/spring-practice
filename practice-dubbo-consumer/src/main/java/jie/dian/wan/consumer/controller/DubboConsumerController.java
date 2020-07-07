package jie.dian.wan.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import jie.dian.wan.dubboservice.user.DubboUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wan dianjie
 * @date 2020-07-07 11:49
 */
@RestController
@RequestMapping("/dubbo")
public class DubboConsumerController {

  @Reference(version = "${demo.service.version}")
  private DubboUserService dubboUserService;

  @GetMapping("/getUserName")
  public String getUserName(@RequestParam String name){
     return dubboUserService.getUserInfo(name);
  }

  //直连方式
//  @Reference(version = "${demo.service.version}",url = "dubbo://127.0.0.1:20880")
//  private DubboUserService dubboUserService;
//
//  @GetMapping("/getUserName")
//  public String getUserName(@RequestParam String name){
//    return dubboUserService.getUserInfo(name);
//  }

}
