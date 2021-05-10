package bg.infosys.daeu.ws.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan({"bg.infosys.daeu.ws", "bg.infosys.daeu.db"})
@PropertySource(value = {"classpath:application.properties"})
@EnableAspectJAutoProxy
public class SpringConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}