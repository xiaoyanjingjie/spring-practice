package jie.dian.wan;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WanPracticeTests {

  @Autowired
  RedissonClient redissonClient;

    @Test
    public void testRession() throws InterruptedException {
     RLock r = redissonClient.getLock("lock_test");

     r.lock();
     System.out.println("业务进行中 。。。");
     Thread.sleep(10000);
     r.unlock();

    }

}
