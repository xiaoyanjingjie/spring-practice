package jie.dian.wan;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * r
 *
 * @author wan dianjie
 * @date 2020-10-26 13:30
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTests {


  /**
   * 发送消息
   */
  @Autowired
  RabbitTemplate rabbitTemplate;

  /**
   * 添加交换机 队列等操作用admin
   */
  @Autowired
  AmqpAdmin amqpAdmin;

  /**
   * 发送消息
   * @throws InterruptedException
   */
  @Test
  public void testRession() throws InterruptedException {
    for(int i =0 ; i<10 ;i++){
      String msg = "hahah-"+i;
      rabbitTemplate.convertAndSend("my.exchanges.direct11","atguigu",msg,new CorrelationData(
          UUID.randomUUID().toString()));
    }


  }

}
