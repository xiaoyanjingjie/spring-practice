package com.alibaba.csp.sentinel.dashboard.rule.redis;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSON;
/**
 * @author wan dianjie
 * @date 2020-05-14 11:28
 */
@Component("flowRuleRedisPublisher")
public class FlowRuleRedisPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {

  @Autowired
  private RedisUtil redisConfigUtil;

  @Override
  public void publish(String app, List<FlowRuleEntity> rules) throws Exception {
    AssertUtil.notEmpty(app, "app name cannot be empty");
    if (rules == null) {
      return;
    }
    String strs = JSON.toJSONString(rules);
    redisConfigUtil.setString(RuleConsts.RULE_FLOW + app, strs);
  }
}