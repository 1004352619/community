package communityApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="communtiy.controller,provider")
public class community_run {

	public static void main(String[] args) {
		SpringApplication.run(community_run.class, args);
	}

}
