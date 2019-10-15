package communityApplication;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baidu.aip.ocr.AipOcr;

@SpringBootApplication(scanBasePackages="communtiy.controller,provider")
@MapperScan("mapper")
public class community_run {

	public static void main(String[] args) {
		SpringApplication.run(community_run.class, args);
	}

}
