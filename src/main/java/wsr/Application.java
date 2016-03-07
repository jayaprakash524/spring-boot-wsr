package wsr;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	private static Class<Application> applicationClass = Application.class;
	private static final Logger logger = LoggerFactory.getLogger(applicationClass);
	
    public static void main(String[] args) {
    	System.out.println(System.getProperty("user.name"));
        SpringApplication.run(applicationClass, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    
   /* @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }*/
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }
}
