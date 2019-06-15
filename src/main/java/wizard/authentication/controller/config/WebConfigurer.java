package wizard.authentication.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import wizard.authentication.controller.RequestInterceptor;

@Configuration
public class WebConfigurer {
    private RequestInterceptor requestInterceptor;

    @Autowired
    public WebConfigurer(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    @Bean
    public WebMvcConfigurer corsConfiguration() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {
                corsRegistry
                        .addMapping("/**")
                        .exposedHeaders("token")
                        .allowedMethods(
                                RequestMethod.POST.name(),
                                RequestMethod.GET.name(),
                                RequestMethod.PUT.name(),
                                RequestMethod.DELETE.name()
                        );
            }

            @Override
            public void addInterceptors(InterceptorRegistry interceptorRegistry) {
                interceptorRegistry.addInterceptor(requestInterceptor);
            }
        };
    }
}
