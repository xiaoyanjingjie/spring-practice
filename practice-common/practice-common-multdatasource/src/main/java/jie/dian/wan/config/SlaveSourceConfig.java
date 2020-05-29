package jie.dian.wan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 从数据库配置
 *
 * @author wan dianjie
 * @date 2020-05-27 17:46
 */
@ConditionalOnProperty(prefix = "spring.mult.datasource", name = "switch", havingValue = "true")
@Configuration
@MapperScan(basePackages = SlaveSourceConfig.PACKAGE,sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveSourceConfig {
  static final String PACKAGE = "jie.dian.wan.**.mapper.slave";
  static final String MAPPER_LOCATION = "classpath*:mapper/slavedb/*.xml";

  @Bean("db2")
  @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
  public DataSource druidDataSource1() {
    DruidDataSource druidDataSource = new DruidDataSource();
    return druidDataSource;
  }

  @Bean("slaveSqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory1(@Qualifier("db2")DataSource db2) throws Exception {
    MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(db2);
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources(SlaveSourceConfig.MAPPER_LOCATION));
    return sqlSessionFactoryBean.getObject();

  }

}
