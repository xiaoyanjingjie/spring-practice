package jie.dian.wan.fiter;

import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 在fastjson中使用此过滤器进行脱敏操作
 *
 * @author wan dianjie
 * @date 2020-06-04 09:54
 */
@Component
@Slf4j
public class ValueDesensitizeFilter implements ValueFilter {


  @Override
  public Object process(Object object, String name, Object value) {
    log.info("打印字段 0：字段名称：{}，字段值：{} 字段类型：{}",name,value,object);
//    if (ObjectUtil.isNull(value) ||  !(value instanceof String) || ((String) value).length() == 0) {
//       log.info("打印字段 1：字段名称：{}，字段值：{} 字段类型：{}",name,value,object);
//      return value;
//    }
//    if(object instanceof Exception){
//       log.info("脱敏操作捕获到异常：{}",object);
//    }
//    try {
//      Field field = object.getClass().getDeclaredField(name);
//      Desensitized desensitization;
//      if (String.class != field.getType() || (desensitization = field.getAnnotation(Desensitized.class)) == null) {
//        log.info("打印字段 2：字段名称：{}，字段值：{} 字段类型：{}",name,value,object);
//        return value;
//      }
//      log.info("打印字段 3：字段名称：{}，字段值：{} 字段类型：{}",name,value,object);
//      String valueStr = (String) value;
//      SensitiveTypeEnum type = desensitization.type();
//      switch (type) {
//        case ID_CARD:
//          return DesensitizedUtils.idCardNum(valueStr);
//        case MOBILE_PHONE:
//          return DesensitizedUtils.mobilePhone(valueStr);
//        case BANK_CARD:
//        default:
//      }
//    } catch (NoSuchFieldException e) {
//      log.info("打印字段 4：字段名称：{}，字段值：{} 字段类型：{}",name,value,object);
//      log.error("当前数据类型为{},值为{}", object.getClass(), value);
//      return value;
//    }
//    log.info("打印字段 5：字段名称：{}，字段值：{} 字段类型：{}",name,value,object);
    return value;
  }

}
