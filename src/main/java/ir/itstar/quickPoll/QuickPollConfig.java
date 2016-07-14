package ir.itstar.quickPoll;

import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class QuickPollConfig extends WebMvcConfigurerAdapter {
	
	
	
	@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
         return slr;

    }
 
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(localeChangeInterceptor());
	}
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    	
//    	
    	
    	if (!registry.hasMappingForPattern("/*.*")) {
    		registry.addResourceHandler("/*.*")
    		.addResourceLocations(new String[]{"classpath:/static/", "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/public"});
    	}
    	
    	registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");

	}
    
    @Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    	PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
    	phmar.setFallbackPageable(new PageRequest(0,5));
    	argumentResolvers.add(phmar);
    	super.addArgumentResolvers(argumentResolvers);
    	
	}
    
    
    
//	@Bean
//	public ServletRegistrationBean h2servletRegistration() {
//	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//	    registration.addUrlMappings("/console/*");
//	    return registration;
//	}
	
// @Bean
//	 public ResourceBundleMessageSource messageSource() {
//		  ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//		  source.setUseCodeAsDefaultMessage(true);
//		  source.setBasenames(new String[]{"i18n/messages","i18n/ValidationMessages"});
//		  return source;
//	 }
}
