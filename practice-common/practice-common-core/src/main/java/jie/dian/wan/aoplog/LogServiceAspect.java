package jie.dian.wan.aoplog;


import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * service层切面
 *
 * @author wan dianjie
 * @date 2019-08-21 16:22
 */
@Slf4j
@Aspect
@Service("LogServiceAspect")
public class LogServiceAspect {
  @Pointcut(value = "@annotation(jie.dian.wan.aoplog.LogServiceFilter)")
  public void logPointCut (){

  }
  @Around("logPointCut()")
  public Object around (ProceedingJoinPoint point) throws Throwable {
    Object result = null ;
    try{
      // 执行方法
      result = point.proceed();
      // 保存请求日志
      saveRequestLog(point);
    } catch (Exception e){
      // 保存异常日志
      saveExceptionLog(point,e.getMessage());
    }
    return result;
  }
  private void saveExceptionLog (ProceedingJoinPoint point,String exeMsg){
    log.info("捕获异常:"+exeMsg);
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    log.info("请求路径:"+request.getRequestURL());
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    log.info("请求方法:"+method.getName());
    // 获取方法上LogFilter注解
    LogServiceFilter logControllerFilter = method.getAnnotation(LogServiceFilter.class);
    String value = logControllerFilter.value() ;
    log.info("模块描述:"+value);
    Object[] args = point.getArgs();
    log.info("请求参数:"+ args);
  }
  private void saveRequestLog (ProceedingJoinPoint point){
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    log.info("请求路径:"+request.getRequestURL());
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    log.info("请求方法:"+method.getName());
    // 获取方法上LogFilter注解
    LogServiceFilter logControllerFilter = method.getAnnotation(LogServiceFilter.class);
    String value = logControllerFilter.value() ;
    log.info("模块描述:"+value);
    Object[] args = point.getArgs();
    log.info("请求参数:"+ args);
  }
}
