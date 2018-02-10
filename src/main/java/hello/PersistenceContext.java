package hello;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class PersistenceContext {

//	@Bean(destroyMethod = "close")
//	private DataSource dataSource(Environment env) {
//		HikariConfig dataSourceConfig = new HikariConfig();
//		dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
//		dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
//		dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
//		dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
//
//		return new HikariDataSource(dataSourceConfig);
//	}
//
//	@Bean
//	private LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
//		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//		entityManagerFactoryBean.setDataSource(dataSource);
//		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//		entityManagerFactoryBean.setPackagesToScan("hello");
//
//		Properties jpaProperties = new Properties();
//		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//		jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.naming_strategy"));
//
//		entityManagerFactoryBean.setJpaProperties(jpaProperties);
//
//		return entityManagerFactoryBean;
//	}
}
