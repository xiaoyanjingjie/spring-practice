package com.alibaba.csp.sentinel.dashboard.rule.redis;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @author wan dianjie
 * @date 2020-05-14 11:27
 */


@Component("flowRuleRedisProvider")
public class FlowRuleRedisProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {

  @Autowired
  private RedisUtil redisConfigUtil;

  @Override
  public List<FlowRuleEntity> getRules(String appName) throws Exception {
    String rules = redisConfigUtil.getString(RuleConsts.RULE_FLOW + appName);
    if (StringUtil.isEmpty(rules)) {
      return new ArrayList<>();
    }
    return JSONObject.parseArray(rules,FlowRuleEntity.class);
  }
}