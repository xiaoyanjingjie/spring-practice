package jie.dian.wan.util;

import cn.hutool.core.util.StrUtil;

/**
 * 脱敏工具类
 *
 * @author meng ran
 * @date 2019-04-12 14:15
 */
public class DesensitizedUtils {

    /**
     * 对字符串进行脱敏操作
     *
     * @param origin    原字符串
     * @param prefixLen 前边要保留位数
     * @param suffixLen 后边要保留位数
     * @param mask      遮罩'*'
     * @return 脱敏后的内容
     */
    public static String desValue(String origin, int prefixLen, int suffixLen, String mask) {
        if (origin == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = origin.length(); i < n; i++) {
            if (i < prefixLen) {
                sb.append(origin.charAt(i));
                continue;
            }
            if (i > (n - suffixLen - 1)) {
                sb.append(origin.charAt(i));
                continue;
            }
            sb.append(mask);
        }
        return sb.toString();
    }

    /**
     * 中文姓名---只显示最后一个汉字
     *
     * @param fullName 姓名
     * @return 结果
     */
    public static String cnName(String fullName) {
        if (fullName == null) {
            return null;
        }
        return desValue(fullName, 0, 1, "*");
    }

    /**
     * 身份证号---显示前六位, 四位，其他隐藏。共计18位或者15位，比如：340304*******1234
     *
     * @param id 身份证号码
     * @return 结果
     */
    public static String idCardNum(String id) {
        return desValue(id, 6, 4, "*");
    }

    /**
     * 固定电话---后四位
     *
     * @param num 固定电话
     * @return 结果
     */
    public static String fixedPhone(String num) {
        return desValue(num, 0, 4, "*");
    }

    /**
     * 手机号码---前三位，后四位
     *
     * @param num 手机号码
     * @return 结果
     */
    public static String mobilePhone(String num) {
        return desValue(num, 3, 4, "*");
    }

    /**
     * 地址---只显示到地区
     *
     * @param address 地址
     * @return 结果
     */
    public static String address(String address) {
        return desValue(address, 6, 0, "*");
    }

    /**
     * 电子邮箱--- 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替
     *
     * @param email 电子邮箱
     * @return 结果
     */
    public static String email(String email) {
        if (email == null) {
            return null;
        }
        int index = StrUtil.indexOf(email, '@');
        if (index <= 1) {
            return email;
        }
        String preEmail = desValue(email.substring(0, index), 1, 0, "*");
        return preEmail + email.substring(index);

    }

    /**
     * 银行卡号---前六位，后四位
     *
     * @param cardNum 银行卡号
     * @return 结果
     */
    public static String bankCard(String cardNum) {
        return desValue(cardNum, 6, 4, "*");
    }

    /**
     * 密码---密码的全部隐藏
     *
     * @param password 密码
     * @return 结果
     */
    public static String password(String password) {
        if (password == null) {
            return null;
        }
        return "******";
    }

    /**
     * 密钥---密钥除了最后三位，全部都用*代替
     *
     * @param key 密钥
     * @return 结果
     */
    public static String secret(String key) {
        if (key == null) {
            return null;
        }
        int viewLength = 6;
        StringBuilder tmpKey = new StringBuilder(desValue(key, 0, 3, "*"));
        if (tmpKey.length() > viewLength) {
            return tmpKey.substring(tmpKey.length() - viewLength);
        } else if (tmpKey.length() < viewLength) {
            int buffLength = viewLength - tmpKey.length();
            for (int i = 0; i < buffLength; i++) {
                tmpKey.insert(0, "*");
            }
            return tmpKey.toString();
        } else {
            return tmpKey.toString();
        }
    }

}