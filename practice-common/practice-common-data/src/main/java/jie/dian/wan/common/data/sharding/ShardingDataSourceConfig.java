package jie.dian.wan.common.data.sharding;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 参考地址 https://blog.csdn.net/tuesdayma/article/details/81081666
 * 主数据源配置
 *
 * @author wan dianjie
 * @date 2020-05-27 17:42
 */
@Configuration
public class ShardingDataSourceConfig {



//  @Bean("sharding-db")
//  public DataSource druidDataSource1() {
//    DruidDataSource druidDataSource = ShardingDataSourceFactory
//        .createDataSource(dataSourceMap, shardingRuleConfig, props);
//    return druidDataSource;
//  }


}
