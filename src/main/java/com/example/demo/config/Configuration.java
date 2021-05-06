package com.example.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@org.springframework.context.annotation.Configuration
@ComponentScan("com.example.demo.entity")
@PropertySource("classpath:application.properties")
public class Configuration {

	@Value("${spring.data.mongodb.driver}")
	private String mangoDbDriver;
	@Value("${spring.data.mongodb.uri}")
	private String mangoDbUri;
	@Value("${spring.data.mongodb.dialect}")
	private String mangoDbDialect;

	@Bean(name = "mongoDbDataSource")
	public DataSource getDataSource() {
		System.out.println("");
		DataSourceBuilder builder = DataSourceBuilder.create();
		builder.driverClassName(mangoDbDriver);
		builder.url(mangoDbUri);
		return builder.build();
	}

	@Bean(name = "mongoDbSessionFactory")
	@Primary
	public LocalSessionFactoryBean sessionFactory(@Autowired @Qualifier("mongoDbDataSource") DataSource datasource) {

		LocalSessionFactoryBean annotationSfBean = new LocalSessionFactoryBean();
		annotationSfBean.setDataSource(datasource);
		
		annotationSfBean.setPackagesToScan(new String[] { "com.example.demo.entity" });

		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", mangoDbDialect);
		annotationSfBean.setHibernateProperties(hibernateProperties);
		return annotationSfBean;
	}

	@Bean
		    public HibernateTemplate getHibernateTemplate(@Autowired @Qualifier("mongoDbSessionFactory") SessionFactory  sessionFactory)
		    {
		        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		        return hibernateTemplate;
		    }
}