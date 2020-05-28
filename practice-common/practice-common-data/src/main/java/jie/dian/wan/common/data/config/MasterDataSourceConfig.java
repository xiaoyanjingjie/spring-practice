package jie.dian.wan.common.data.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
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
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {
  static final String PACKAGE = "jie.dian.wan.**.mapper.master";
  static final String MAPPER_LOCATION = "classpath*:mapper/masterdb/*.xml";


  @Bean("db1")
  @ConfigurationProperties(prefix = "spring.datasource.druid.master")
  public DataSource druidDataSource1() {
    DruidDataSource druidDataSource = new DruidDataSource();
    return druidDataSource;
  }

  @Bean("masterSqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory1(@Qualifier("db1")DataSource db1) throws Exception {
    MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(db1);
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
    return sqlSessionFactoryBean.getObject();

  }


}
