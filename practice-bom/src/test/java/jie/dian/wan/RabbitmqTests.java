package jie.dian.wan;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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



  @Autowired
  RabbitTemplate rabbitTemplate;

  @Test
  public void testRession() throws InterruptedException {
    rabbitTemplate.convertAndSend("haha");
    rabbitTemplate.convertAndSend("my.exchanges.direct","atguigu","hhhhhhhhhh");

  }

}
