package com.eshop.productcommandservice.config.kafka.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic productTopic() {
        return TopicBuilder.name("product-topic")
                .build();
    }

    @Bean
    public NewTopic productQueryTopic() {
        return TopicBuilder.name("product-query-topic")
                .build();
    }

    @Bean
    public NewTopic categoryQueryTopic() {
        return TopicBuilder.name("category-query-topic")
                .build();
    }
}
