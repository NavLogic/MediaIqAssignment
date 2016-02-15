//package com.mediaiq.aggregator.configurations;
//
//import java.util.Properties;
//
//import javax.annotation.Resource;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = { "com.mediaiq.*" })
//public class DataBaseConfiguration {
//    @Resource
//    private Environment environment;
//
//    /**
//     * Primary because if we have activated embedded databases, we do not want the application to
//     * connect to an external database.
//     */
//    @Primary
//    @Bean(name = "toFromDataSource")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
//        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
//        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
//        return dataSource;
//    }
//
//    @Primary
//    @Bean(name = "entityManager")
//    public EntityManager entityManager() {
//        return entityManagerFactory().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public EntityManagerFactory entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean containerEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        containerEntityManagerFactory.setDataSource(dataSource());
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setDatabase(Database.HSQL);
//        containerEntityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
//        containerEntityManagerFactory.setPackagesToScan("com.mediaiq.*");
//        containerEntityManagerFactory.setPersistenceUnitName("persistenceUnit");
//        containerEntityManagerFactory.setJpaProperties(additionalTofromProperties());
//        containerEntityManagerFactory.afterPropertiesSet();
//        return containerEntityManagerFactory.getObject();
//    }
//
//    @Primary
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager() {
//        return new JpaTransactionManager(entityManagerFactory());
//    }
//
//    private Properties additionalTofromProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("spring.jpa.show-sql", environment.getProperty("spring.jpa.show-sql"));
//        properties.setProperty("spring.datasource.test-on-borrow", environment.getProperty("spring.datasource.test-on-borrow"));
//        properties.setProperty("spring.jpa.database-platform", environment.getProperty("spring.jpa.database-platform"));
//        return properties;
//    }
//
//}
