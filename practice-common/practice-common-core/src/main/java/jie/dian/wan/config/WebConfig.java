package jie.dian.wan.config;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import jie.dian.wan.fiter.ValueDesensitizeFilter;
import jie.dian.wan.messageconverter.OAuth2AccessTokenMessageConverter;
import jie.dian.wan.messageconverter.Oauth2HttpMessageConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wandianjie
 * @create 2018-03-12
 **/
@Slf4j
@Configuration
@Component
@AllArgsConstructor
public class WebConfig extends WebMvcConfigurerAdapter {
//    private RequestLimitInterceptor requestLimitInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//        registry.addInterceptor(new TraceIdInterceptor());
//        registry.addInterceptor(requestLimitInterceptor);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

    /**
     * 配置 FastJsonHttpMessageConverter 消息转换器 会默认覆盖掉jackson
     *
     * springboot升级到2 后 升级为springboot2后，响应 FastJsonHttpMessageConverter 未起作用，@JSONField(name = "Code")注释后的code返回还是小写。
     * https://blog.csdn.net/qq_26878363/article/details/97389275
     * 可以看出自定义的消息转换器【FastJsonJsonpHttpMessageConverter】在列表最后。
     *
     * 根据消息转换器的应用规则，会顺序选择符合要求的消费转换器，MappingJackson2HttpMessageConverter 在 FastJsonJsonpHttpMessageConverter 前面，这样就会使用 MappingJackson2HttpMessageConverter  进行消费转换，为了确认想法正确，我在 MappingJackson2HttpMessageConverter  -> writeInternal 方法进行了debug，运行后，确实与想法一致。
     *
     * 找到原因，那就只要把 自定义的消息转换器【FastJsonJsonpHttpMessageConverter】添加到 MappingJackson2HttpMessageConverter 前面就可以，而对于自定义的消息转换器配置还有另一种方式
     * ————————————————
     * 版权声明：本文为CSDN博主「LeoSong121」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_26878363/article/details/97389275
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        {FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
            List<MediaType> supportedMediaTypes = new ArrayList<>();
            supportedMediaTypes.add(MediaType.APPLICATION_JSON);
            supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
            supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
            supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
            supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
            supportedMediaTypes.add(MediaType.APPLICATION_PDF);
            supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
            supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
            supportedMediaTypes.add(MediaType.APPLICATION_XML);
            supportedMediaTypes.add(MediaType.IMAGE_GIF);
            supportedMediaTypes.add(MediaType.IMAGE_JPEG);
            supportedMediaTypes.add(MediaType.IMAGE_PNG);
            supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
            supportedMediaTypes.add(MediaType.TEXT_HTML);
            supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
            supportedMediaTypes.add(MediaType.TEXT_PLAIN);
            supportedMediaTypes.add(MediaType.TEXT_XML);
            supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
            converter.setSupportedMediaTypes(supportedMediaTypes);
            converter.setDefaultCharset(Charset.forName("UTF-8"));

            FastJsonConfig config = new FastJsonConfig();
            //添加自己写的拦截器
            config.setSerializeFilters(new ValueDesensitizeFilter());
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
            config.setDateFormat(JSON.DEFFAULT_DATE_FORMAT);
            config.setSerializerFeatures(
                //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //字符类型字段如果为null,输出为"",而非null
                // SerializerFeature.WriteNullStringAsEmpty,
                //Boolean字段如果为null,输出为falseJ,而非null
                // SerializerFeature.WriteNullBooleanAsFalse,
                //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                //是否输出值为null的字段,默认为false。
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.PrettyFormat
            );
            // 3.在converter中添加配置信息
            converter.setFastJsonConfig(config);
            converters.add(converter);
            // 根据消息转换器的应用规则，会顺序选择符合要求的消费转换器，MappingJackson2HttpMessageConverter 在 FastJsonJsonpHttpMessageConverter 前面
          //  converters.add(0,converter);
            //  log.info("HTTP JSON 消息转换器特性: {}", ArrayUtil.toString(Util.getJsonFeature()));
            log.info("注册 HTTP JSON 消息转换器: {}", converter.getSupportedMediaTypes());}
    }

    /**
     * 参考地址 https://blog.csdn.net/yuelangyc/article/details/88235639
     *  Spring Security Oauth2 返回非标准数据结构 OAuth2AccessToken 序列化问题
     *  在Spring框架中，自定义配置了 FastJsonHttpMessageConverter ，覆盖掉
     *  读过源码后发现 configureMessageConverters 配置，如果有配置，就会使用用户配置的两种消息处理器，如果没有配置，则会默认添加8个消息处理器。
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Oauth2HttpMessageConverter oauth2HttpMessageConverter = new Oauth2HttpMessageConverter();
        converters.add(0, oauth2HttpMessageConverter);
//        converters.add(0, new OAuth2AccessTokenMessageConverter());
        for (HttpMessageConverter<?> messageConverter : converters) {
            log.info("messageConverter:{}",messageConverter);
        }

    }

}