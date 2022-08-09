package service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprintBootDemoApplication implements CommandLineRunner {

	public static final Log LOGGER = LogFactory.getLog(SprintBootDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SprintBootDemoApplication.class, args);
	}
		@Override
		public void run (String...args) throws Exception {
			LOGGER.info("Run method executed");


	}
}
