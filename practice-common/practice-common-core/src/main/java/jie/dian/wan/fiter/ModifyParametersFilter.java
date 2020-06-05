package jie.dian.wan.fiter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wan dianjie
 * @date 2020-06-05 13:17
 */
@Slf4j
public class ModifyParametersFilter implements Filter {


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
      log.info("进入ModifyParametersFilter。。。。");
      chain.doFilter(request, response);
  }
}
