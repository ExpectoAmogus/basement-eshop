package com.eshop.productqueryservice.config.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaGroupId;

    public Map<String, Object> consumerConfig() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);

        Map<String, Object> productEventProps = new HashMap<>();
        productEventProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.eshop.productqueryservice.models.dto.ProductEvent");
        productEventProps.put(JsonDeserializer.TYPE_MAPPINGS, "com.eshop.productcommandservice.models.dto.ProductEvent:com.eshop.productqueryservice.models.dto.ProductEvent");
        productEventProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.eshop.productcommandservice");
        props.put("productEvent", productEventProps);

        Map<String, Object> categoryEventProps = new HashMap<>();
        categoryEventProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.eshop.productqueryservice.models.dto.CategoryEvent");
        categoryEventProps.put(JsonDeserializer.TYPE_MAPPINGS, "com.eshop.productcommandservice.models.dto.CategoryEvent:com.eshop.productqueryservice.models.dto.CategoryEvent");
        categoryEventProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.eshop.productcommandservice");
        props.put("categoryEvent", categoryEventProps);

        return props;
    }

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ?> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }
}
