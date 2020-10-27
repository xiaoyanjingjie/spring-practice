package jie.dian.wan.business.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.rabbitmq.client.Channel;
import jie.dian.wan.business.mapper.master.UserMapper;
import jie.dian.wan.business.mapper.slave.UserSlaveMapper;
import jie.dian.wan.business.model.business.User;
import jie.dian.wan.business.service.UserService;
import jie.dian.wan.common.data.base.EcBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 用户信息
 *
 * @author meng ran
 * @date 2019-03-07 12:45:46
 */

@Service
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

  @Async
  @Override
  public String  testAsync() {
    log.info("trace-id:{}",MDC.get("trace-id"));
    return "haha";
  }

  /**
   * Rabb itListener  必须在容器中 @Service
   *
   */
  @RabbitListener(queues = {"atguigu"})
  public void rabbitmqListener(Message message, String ss, Channel channel){
    log.info("rabbitmq--接受到消息--{}",ss);
    byte[] body = message.getBody();
    //消息头属性
    MessageProperties properties = message.getMessageProperties();
    log.info("消息处理完成:{}",ss);
    long deliveryTag = message.getMessageProperties().getDeliveryTag();
    log.info("deliveryTag:{}",deliveryTag);
    try{

      if(deliveryTag%2 == 0){
        //手动确认 不批量
        channel.basicAck(deliveryTag,false);
        log.info("签收了：{}",deliveryTag);
      }else{
        // deliveryTag  false(批量)  true（重新入队）
        channel.basicNack(deliveryTag,false,false);
        log.info("没有签收了：{}",deliveryTag);
      }

    }catch (Exception e){

    }



  }
}
