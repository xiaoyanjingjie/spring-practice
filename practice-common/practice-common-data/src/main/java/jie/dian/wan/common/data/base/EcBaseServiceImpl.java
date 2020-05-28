package jie.dian.wan.common.data.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 公共业务基类
 *
 * @author wandianjie
 * @date 2019-03-27 10:55
 */
public class EcBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
