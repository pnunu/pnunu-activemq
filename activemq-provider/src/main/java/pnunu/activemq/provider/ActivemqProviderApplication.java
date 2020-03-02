package pnunu.activemq.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms //启动消息队列
@SpringBootApplication
public class ActivemqProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqProviderApplication.class, args);
	}

}
