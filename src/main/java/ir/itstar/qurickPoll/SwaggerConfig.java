package ir.itstar.qurickPoll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
	    ApiInfo apiInfo = new ApiInfo(
	    		 "Quick Poll REST API",
	    	      "An API to produce Quick Poll Resources",
	    	      "0.1 BETA",
	    	      "Public Usage",
	    	      mycontact(),
	    	      "GPLv3",
	    	      "https://www.gnu.org/licenses/gpl-3.0.html"
	    		);
	    return apiInfo;
	}
	
	private Contact mycontact(){
		return new Contact("Hamed Nourhani","itstar.ir","itstarir@gamil.com");
	}
}
