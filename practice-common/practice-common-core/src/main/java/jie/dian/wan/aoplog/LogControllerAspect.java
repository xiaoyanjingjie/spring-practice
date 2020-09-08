package jie.dian.wan.aoplog;

import cn.hutool.json.JSONUtil;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 日志切面
 * https://blog.csdn.net/u010502101/article/details/78823056
 * @author wan dianjie
 * @date 2019-08-21 11:00
 */
@Slf4j
@Aspect
@Component
public class LogControllerAspect {
  @Pointcut(value = "@annotation(jie.dian.wan.aoplog.LogControllerFilter)")
  public void logPointCut (){

  }
  /**
   * 这是因为在返回后通知（@AfterReturning）
   * 和抛出异常后通知（@AfterThrowing）
   * 的方法中不能使用ProceedingJoinPoint，
   * 使用JoinPoint即可解决
   * @param point
   * @return
   * @throws Throwable
   */
  @Before("logPointCut()")
  public void before (JoinPoint point) {
      // 执行方法
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    log.info("请求路径:{}",request.getRequestURL());
    log.info("headers:{}",request.getHeaderNames());
      String methodName = point.getSignature().getName();
      List<Object> args = Arrays.asList(point.getArgs());
      System.out.println("调用前连接点方法为：" + methodName + ",参数为：" + args);
      // 保存请求日志
      //saveRequestLog(point);

  }
//
  @AfterThrowing(value = "logPointCut()",throwing = "e")
  public void afterThrowing (JoinPoint point,Throwable e) {
    // 执行方法
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    log.info("请求路径:{}",request.getRequestURL());
    log.info("headers:{}",request.getHeaderNames());
    String methodName = point.getSignature().getName();
    List<Object> args = Arrays.asList(point.getArgs());
    System.out.println("调用前连接点方法为：" + methodName + ",参数为：" + args);
    // 保存请求日志
    //saveRequestLog(point);

  }

//  @Around("logPointCut()")
//  public Object around (ProceedingJoinPoint point) throws Throwable {
//    Object result = null ;
//    try{
//      // 执行方法
//      result = point.proceed();
//      // 保存请求日志
//      saveRequestLog(point);
//    } catch (Exception e){
//      // 保存异常日志
//      saveExceptionLog(point,e.getMessage());
//    }
//    return result;
//  }

  private void saveExceptionLog (ProceedingJoinPoint point,String exeMsg){
    log.info("捕获异常:"+exeMsg);
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    log.info("请求路径:"+request.getRequestURL());
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    log.info("请求方法:"+method.getName());
    // 获取方法上LogFilter注解
    LogControllerFilter logControllerFilter = method.getAnnotation(LogControllerFilter.class);
    String value = logControllerFilter.value() ;
    log.info("模块描述:"+value);
    Object[] args = point.getArgs();
    log.info("请求参数:"+ JSONUtil.toJsonStr(args));
  }
  private void saveRequestLog (ProceedingJoinPoint point){
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    log.info("请求路径:"+request.getRequestURL());
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    log.info("请求方法:"+method.getName());
    // 获取方法上LogFilter注解
    LogControllerFilter logControllerFilter = method.getAnnotation(LogControllerFilter.class);
    String value = logControllerFilter.value() ;
    log.info("模块描述:"+value);
    Object[] args = point.getArgs();
    log.info("请求参数:"+ JSONUtil.toJsonStr(args));
  }
}
