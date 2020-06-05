package jie.dian.wan.sensitive;

import lombok.AllArgsConstructor;

/**
 * 敏感信息枚举类
 *
 * @author mayee
 * @version v1.0
 **/
@AllArgsConstructor
public enum SensitiveType {

    /**
     * 自定义
     */
    CUSTOMER,
    /**
     * 用户名
     */
    CN_NAME,
    /**
     * 身份证号
     */
    ID_CARD,
    /**
     * 密码
     */
    PASSWORD,
    /**
     * 密钥
     */
    SECRET,
    /**
     * 手机号
     */
    MOBILE_PHONE,
    /**
     * 电话座机号
     */
    FIXED_PHONE,
    /**
     * 地址
     */
    ADDRESS,
    /**
     * 电子邮件
     */
    EMAIL,
    /**
     * 银行卡号
     */
    BANK_CARD;



}
