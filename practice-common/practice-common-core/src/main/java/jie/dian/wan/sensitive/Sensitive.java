
package jie.dian.wan.sensitive;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 脱敏自定义注解
 *
 * @author meng ran
 * @date 2019-03-04 13:52
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface Sensitive {

	/**
	 * 脱敏数据类型, 非Customer时, 将忽略 refixNoMaskLen 和 suffixNoMaskLen 和 maskStr
	 */
	SensitiveType type() default SensitiveType.CUSTOMER;

	/**
	 * 前不打码的长度
	 */
	int prefixLen() default 0;

	/**
	 * 后不打码的长度
	 */
	int suffixLen() default 0;

	/**
	 * 用什么打码
	 */
	String mask() default "*";

}
