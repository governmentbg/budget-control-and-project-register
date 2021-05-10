package bg.infosys.daeu.ws.config;

import bg.infosys.daeu.ws.interceptors.IISDAControllerAccessInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"bg.infosys.daeu.ws.controllers"})
/**
 * Web related configs -> csrf, mediaTypes etc
 */
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IISDAControllerAccessInterceptor()).addPathPatterns("/iisda/getHierarchy");
    }

}
