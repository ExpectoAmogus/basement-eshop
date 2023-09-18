package com.eshop.inventoryservice.listener;

import com.eshop.inventoryservice.facade.InventoryFacade;
import com.eshop.inventoryservice.models.dto.InventoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
@RequiredArgsConstructor
public class InventoryListener {
    private final InventoryFacade inventoryFacade;

    @KafkaListener(topics = "product-topic", groupId = "inventoryId", containerFactory = "kafkaListenerContainerFactory")
    public void handleEvent(@Payload InventoryRequest request) {
        switch (request.getEventType()){
            case PRODUCT_CREATED -> inventoryFacade.create(request);
            case PRODUCT_UPDATED -> inventoryFacade.update(request);
            case PRODUCT_DELETED -> inventoryFacade.delete(request);
        }

    }
}
