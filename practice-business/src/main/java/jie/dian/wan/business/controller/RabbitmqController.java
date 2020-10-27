package jie.dian.wan.business.controller;

import java.util.UUID;
import jie.dian.wan.util.SpringContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * mq
 *
 * @author wan dianjie
 * @date 2020-10-27 10:25
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {

  /**
   * 发送消息
   */
  //private final RabbitTemplate rabbitTemplate;
 private final SpringContextUtil springContextUtil;

  @ResponseBody
  @GetMapping("/sendMq")
  public String sendMq(){
    for(int i =0 ; i<10 ;i++){
      String msg = "hahah-"+i;
      springContextUtil.getBean(RabbitTemplate.class).convertAndSend("my.exchanges.direct","atguigu",msg,new CorrelationData(
          UUID.randomUUID().toString()));
    }
    return "aaa";
  }

}
