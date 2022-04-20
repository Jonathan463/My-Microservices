package com.company.notification;

import com.company.amqp.RabbitMQMessageProducer;
import com.company.notification.notificationConfig.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.company.notification",
                "com.company.amqp",
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig notificationConfig){
        return args -> {
            producer.publish(
                    new Person("Jonathan", 27),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey());

        };
    }

    record Person(String name, int age){

    }
}
