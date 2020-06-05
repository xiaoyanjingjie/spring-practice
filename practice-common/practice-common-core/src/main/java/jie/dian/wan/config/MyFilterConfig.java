package jie.dian.wan.config;

import javax.servlet.DispatcherType;
import jie.dian.wan.fiter.ModifyParametersFilter;
import jie.dian.wan.fiter.ValueDesensitizeFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author wan dianjie
 * @date 2020-06-05 13:12
 */
@Configuration
public class MyFilterConfig {
  @Bean
  public FilterRegistrationBean validateCodeFilter() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setDispatcherTypes(DispatcherType.REQUEST);
    registration.setFilter(new ModifyParametersFilter());
    registration.addUrlPatterns("/*");
    registration.setName("modifyParametersFilter");
    registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
    return registration;
  }


}
