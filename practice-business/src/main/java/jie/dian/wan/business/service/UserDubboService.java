package jie.dian.wan.business.service;

import com.alibaba.dubbo.config.annotation.Reference;
import java.util.List;

public interface UserDubboService {
   List getUserInfo();
}
