package communityApplication;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.baidu.aip.ocr.AipOcr;

@SpringBootApplication(scanBasePackages="communtiy.controller,provider,service,dto,interceptor")
@MapperScan("mapper")
public class community_run {

	public static void main(String[] args) {
		SpringApplication.run(community_run.class, args);
	}

}
