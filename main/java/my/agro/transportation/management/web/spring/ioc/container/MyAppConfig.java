package my.agro.transportation.management.web.spring.ioc.container;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {
    "my.agro.transportation.management.service",
    "my.agro.transportation.management.controller",
    "my.agro.transportation.management.dao.repository",
    "my.agro.transportation.management.web.spring.ioc.container",
    "my.agro.transportation.management.test"
})

@EnableJpaRepositories(basePackages = {"my.agro.transportation.management.dao.repository"}, entityManagerFactoryRef = "entityManagerFactoryRef")
@EnableTransactionManagement
@EnableJpaAuditing

public class MyAppConfig {

	@Bean
    public EntityManagerFactory entityManagerFactoryRef() {
        try {    
        	String persistenceUnitName = "my.orders.persistance.unit";
            InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
			Map properties = new HashMap();
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
			return Persistence.createEntityManagerFactory(persistenceUnitName, properties);
        }
        catch (Exception e) {
        	throw new RuntimeException(e);
        } 
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
