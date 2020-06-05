
package jie.dian.wan.sensitive;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.util.Objects;
import jie.dian.wan.util.DesensitizedUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 时间格式序列化
 *
 * @author meng ran
 * @date 2019-03-04 13:52
 */
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveType type;
    private Integer prefixLen;
    private Integer suffixLen;
    private String mask;

    @Override
    public void serialize(final String origin, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        switch (type) {
            case CN_NAME:
                jsonGenerator.writeString(DesensitizedUtils.cnName(origin));
                break;
            case ID_CARD:
                jsonGenerator.writeString(DesensitizedUtils.idCardNum(origin));
                break;
            case PASSWORD:
                jsonGenerator.writeString(DesensitizedUtils.password(origin));
                break;
            case SECRET:
                jsonGenerator.writeString(DesensitizedUtils.secret(origin));
                break;
            case MOBILE_PHONE:
                jsonGenerator.writeString(DesensitizedUtils.mobilePhone(origin));
                break;
            case FIXED_PHONE:
                jsonGenerator.writeString(DesensitizedUtils.fixedPhone(origin));
                break;
            case ADDRESS:
                jsonGenerator.writeString(DesensitizedUtils.address(origin));
                break;
            case EMAIL:
                jsonGenerator.writeString(DesensitizedUtils.email(origin));
                break;
            case BANK_CARD:
                jsonGenerator.writeString(DesensitizedUtils.bankCard(origin));
                break;
            case CUSTOMER:
                jsonGenerator.writeString(DesensitizedUtils.desValue(origin, prefixLen, suffixLen, mask));
                break;
            default:
                throw new IllegalArgumentException("类型有误" + type);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
                                              final BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                Sensitive sensitive = beanProperty.getAnnotation(Sensitive.class);
                if (sensitive == null) {
                    sensitive = beanProperty.getContextAnnotation(Sensitive.class);
                }
                if (sensitive != null) {
                    return new SensitiveSerialize(sensitive.type(), sensitive.prefixLen(), sensitive.suffixLen(), sensitive.mask());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}

