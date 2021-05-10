package bg.infosys.daeu.ws.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PublicPersistenceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(PublicPersistenceConfig.class);

    @Autowired private Environment environment;

    @Bean(name = "secondaryEntityManager")
    public LocalContainerEntityManagerFactoryBean secondaryContainerEntityManagerFactoryBean() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(comboPooledDataSource());
        em.setJpaVendorAdapter(getJpaVendorAdapter());
        em.setPackagesToScan("bg.infosys.daeu.ws.pub.entity", "bg.infosys.daeu.ws.pub.dao");
        em.setJpaProperties(getProperties());
        return em;
    }

    @Bean(name = "secondaryDataSource")
    public DataSource comboPooledDataSource() throws PropertyVetoException {
        String driverClass = environment.getProperty("pubdb.datasource.driver-class-name");

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverClass);
        comboPooledDataSource.setJdbcUrl(environment.getProperty("pubdb.jdbcUrl"));
        comboPooledDataSource.setPassword(environment.getProperty("pubdb.password"));
        comboPooledDataSource.setUser(environment.getProperty("pubdb.user"));

        comboPooledDataSource.setMinPoolSize(environment.getProperty("pubdb.minPoolSize", Integer.class));
        comboPooledDataSource.setMaxPoolSize(environment.getProperty("pubdb.maxPoolSize", Integer.class));
        comboPooledDataSource.setInitialPoolSize(environment.getProperty("pubdb.initialPoolSize", Integer.class));
        comboPooledDataSource.setMaxStatements(environment.getProperty("pubdb.maxStatements", Integer.class));

        return comboPooledDataSource;
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        try {
        	jpaTransactionManager.setDataSource(comboPooledDataSource());
            jpaTransactionManager.setEntityManagerFactory(secondaryContainerEntityManagerFactoryBean().getObject());
        } catch (PropertyVetoException e) {
        	LOGGER.error("Failed creating Transaction Manager : {}", e.getMessage());
        }

        return jpaTransactionManager;
    }

    private Properties getProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", environment.getProperty("pub.hibernate.dialect"));
        jpaProperties.setProperty("spring.jpa.database-platform", environment.getProperty("pub.spring.jpa.database-platform"));
        jpaProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        jpaProperties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        jpaProperties.setProperty("hibernate.generate-ddl", environment.getProperty("hibernate.generate-ddl"));
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.ddl-auto"));
        jpaProperties.setProperty("hibernate.globally_quoted_identifiers", environment.getProperty("hibernate.globally_quoted_identifiers"));
        jpaProperties.setProperty("hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));

        return jpaProperties;
    }
    
    private JpaVendorAdapter getJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
}
