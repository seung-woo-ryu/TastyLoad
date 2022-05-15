package blockchainstudy.example.tastyload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class TastyLoadApplication {

	public static void main(String[] args) {
		SpringApplication.run(TastyLoadApplication.class, args);
	}

}
