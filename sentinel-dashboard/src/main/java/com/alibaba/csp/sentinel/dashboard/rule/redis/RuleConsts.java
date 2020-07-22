package com.alibaba.csp.sentinel.dashboard.rule.redis;

/**
 * @author wan dianjie
 * @date 2020-05-14 11:30
 */
public interface RuleConsts {
  //流控规则key前缀
  public final String RULE_FLOW = "sentinel_rule_flow_";
  //限流规则key前缀
  public final String RULE_DEGRADE = "sentinel_rule_degrade_";
  //系统规则key前缀
  public final String RULE_SYSTEM = "sentinel_rule_system_";


}
