package ir.itstar.quickPoll;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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