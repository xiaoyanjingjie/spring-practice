package jie.dian.wan;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * 整合启动类
 *
 * @author wandianjie
 * @date 2019-03-04 20:43
 */
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
//@SpringBootApplication(scanBasePackages = {"jie.dian.wan"},exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
@SpringBootApplication
public class PracticeBomApplication {
  public static void main(String[] args) {
    SpringApplication.run(PracticeBomApplication.class, args);
  }
}