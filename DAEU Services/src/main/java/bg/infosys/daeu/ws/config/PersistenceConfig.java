package bg.infosys.daeu.ws.config;


import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    @Autowired
    private Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(PersistenceConfig.class);

    @Primary
    @Bean(name = "primaryEntityManager")
    public LocalContainerEntityManagerFactoryBean primaryContainerEntityManagerFactoryBean() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(comboPooledDataSource());
        em.setJpaVendorAdapter(getJpaVendorAdapter());
        em.setPackagesToScan("bg.infosys.daeu.db.entity", "bg.infosys.security.db.entity", "bg.infosys.daeu.db.dao", "bg.infosys.daeu.ws", "bg.infosys.daeu.db.dao.pub");
        em.setJpaProperties(getProperties());
        return em;
    }

    private JpaVendorAdapter getJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource comboPooledDataSource() throws PropertyVetoException {
        String driverClass = environment.getProperty("reldb.datasource.driver-class-name");

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverClass);
        comboPooledDataSource.setJdbcUrl(environment.getProperty("reldb.jdbcUrl"));
        comboPooledDataSource.setPassword(environment.getProperty("reldb.password"));
        comboPooledDataSource.setUser(environment.getProperty("reldb.user"));


        comboPooledDataSource.setMinPoolSize(environment.getProperty("reldb.minPoolSize", Integer.class));
        comboPooledDataSource.setMaxPoolSize(environment.getProperty("reldb.maxPoolSize", Integer.class));
        comboPooledDataSource.setInitialPoolSize(environment.getProperty("reldb.initialPoolSize", Integer.class));
        comboPooledDataSource.setMaxStatements(environment.getProperty("reldb.maxStatements", Integer.class));

        return comboPooledDataSource;
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        try {
            jpaTransactionManager.setEntityManagerFactory(primaryContainerEntityManagerFactoryBean().getObject());
        } catch (PropertyVetoException e) {
            logger.error("Failed creating Transaction Manager : {}", e.getMessage());
        }

        return jpaTransactionManager;
    }

    private Properties getProperties() {

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        jpaProperties.setProperty("spring.jpa.database-platform", environment.getProperty("spring.jpa.database-platform"));
        jpaProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        jpaProperties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        jpaProperties.setProperty("hibernate.generate-ddl", environment.getProperty("hibernate.generate-ddl"));
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.ddl-auto"));
        jpaProperties.setProperty("hibernate.globally_quoted_identifiers", environment.getProperty("hibernate.globally_quoted_identifiers"));
        jpaProperties.setProperty("hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));

        jpaProperties.setProperty("org.hibernate.envers.default_schema", environment.getProperty("org.hibernate.envers.default_schema"));
        jpaProperties.setProperty("org.hibernate.envers.audit_table_prefix", environment.getProperty("org.hibernate.envers.audit_table_prefix"));
        jpaProperties.setProperty("org.hibernate.envers.audit_table_suffix", environment.getProperty("org.hibernate.envers.audit_table_suffix"));
        jpaProperties.setProperty("org.hibernate.envers.global_with_modified_flag", environment.getProperty("org.hibernate.envers.global_with_modified_flag"));
        jpaProperties.setProperty("org.hibernate.envers.modified_flag_suffix", environment.getProperty("org.hibernate.envers.modified_flag_suffix"));
        jpaProperties.setProperty("org.hibernate.envers.doNotAuditOptimisticLockingField", environment.getProperty("org.hibernate.envers.doNotAuditOptimisticLockingField"));
        jpaProperties.setProperty("org.hibernate.envers.revision_field_name", environment.getProperty("org.hibernate.envers.revision_field_name"));
        jpaProperties.setProperty("hibernate.integration.envers.enabled", environment.getProperty("hibernate.integration.envers.enabled"));

        return jpaProperties;
    }

}
