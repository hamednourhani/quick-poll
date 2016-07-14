package ir.itstar.qurickPoll;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class QuickPollApplication extends WebMvcConfigurerAdapter {
 
	public static void main(String[] args) {
		SpringApplication.run(QuickPollApplication.class, args);
	}
	

	

}
