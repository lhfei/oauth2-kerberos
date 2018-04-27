package cn.lhfei.auth.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ImportResource(locations = "classpath:spring/application-context.xml")
@EnableJpaAuditing
public class LdapAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdapAuthApplication.class, args);
	}
}
