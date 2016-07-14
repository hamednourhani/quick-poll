package ir.itstar.qurickPoll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ir.itstar.quickPoll.QuickPollApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuickPollApplication.class)
@WebAppConfiguration
public class QuickPollApplicationTests {

	@Test
	public void contextLoads() {
	}

}
