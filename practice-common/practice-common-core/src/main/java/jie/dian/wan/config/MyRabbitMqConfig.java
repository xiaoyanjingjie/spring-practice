package jie.dian.wan.config;

import javax.annotation.PostConstruct;
import jie.dian.wan.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wan dianjie
 * @date 2020-10-26 13:18
 *
 * https://www.colabug.com/2020/0813/7615186/
 * rabbitmq reply-text=PRECONDITION_FAILED – unknown delivery tag 1的解决
 *
 * 因为rabbitmq 提供了java对象json序列化的支持，然后出现了，
 *
 *
 * Channel shutdown: channel error; protocol method: #method
 * (reply-code=406, reply-text=PRECONDITION_FAILED – unknown delivery tag 1, class-id=60, method-id=80)
 *
 * 因为在
 *
 * 添加了
 *
 * factory.setMessageConverter(new Jackson2JsonMessageConverter());
 *
 * rabbitmq的ack 就被设置为自动提交，即使配置文件
 *
 * 添加了
 *
 * 也没有任何作用，只好重新设置
 *
 * factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
 */
@Slf4j
@Configuration
public class MyRabbitMqConfig {

  @Autowired
  SpringContextUtil springContextUtil;

//  @Bean
//  public RabbitAdmin rabbitTemplate(ConnectionFactory connectionFactory) {
//    return new RabbitAdmin(connectionFactory);
//  }
//
//  @Bean
//  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
//    RabbitTemplate template = new RabbitTemplate(connectionFactory);
//    template.setMessageConverter(messageConverter);
//    return template;
//  }
  @Bean
  public MessageConverter messageConverter(){
    return new Jackson2JsonMessageConverter();
  }
  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ConnectionFactory connectionFactory, MessageConverter messageConverter) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

    factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    factory.setConnectionFactory(connectionFactory);

    factory.setMessageConverter(messageConverter);
    return factory;
  }
  /**
   * 定制rabbitTemplate
   */

  @PostConstruct
  public void initRabbitTemplate(){
    RabbitTemplate rabbitTemplate = springContextUtil.getBean(RabbitTemplate.class);
    rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
      /**
       * 设置确认回掉
       * @param correlationData 当前消息唯一标示
       * @param b 消息是否收到
       * @param s 失败的原因
       */
      @Override
      public void confirm(CorrelationData correlationData, boolean b, String s) {
         log.info("confirm...correlationData:==>{},ack :==>{},msg:==>{}",correlationData,b,s);
      }
    });

    //设置消息抵达队列的确认回掉
    rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
      /**
       * 主要消息没有投递指定的队列，就会触发这个失败的回掉
       * @param message 投递失败的详细信息
       * @param i 回复的状态吗
       * @param s 回复的文本内容
       * @param s1 当时这个消息投递的是那个交换机
       * @param s2 当时指定的路由建
       */
      @Override
      public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("Fail Message:==>{},code :==>{},text:==>{},exchange:==>{},routingKey:==>{}",message,i,s,s1,s2);
      }
    });

  }



}
