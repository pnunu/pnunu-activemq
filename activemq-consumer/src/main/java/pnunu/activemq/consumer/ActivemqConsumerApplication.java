package pnunu.activemq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @Author: pnunu
 * @Date: 2020/3/2 11:06
 */
@EnableJms //启动消息队列
@SpringBootApplication
public class ActivemqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivemqConsumerApplication.class, args);
    }
}
