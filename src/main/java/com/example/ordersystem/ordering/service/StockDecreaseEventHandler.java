//package com.example.ordersystem.ordering.service;
//
//import com.example.ordersystem.common.configs.RabbitMqConfig;
//import com.example.ordersystem.ordering.dto.StockDecreaseEvent;
//import com.example.ordersystem.product.domain.Product;
//import com.example.ordersystem.product.repository.ProductRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//
//@Component
//public class StockDecreaseEventHandler {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private ProductRepository productRepository;
//    public void publish(StockDecreaseEvent event){
//        rabbitTemplate.convertAndSend(RabbitMqConfig.STOCK_DECREASE_QUEUE, event);
//    }
//
////    트랜잭션이 완료된 이후에 그다음 메시지 수신하므로, 동시성이슈발생X
//    @Transactional
//    @RabbitListener(queues = RabbitMqConfig.STOCK_DECREASE_QUEUE)
//    public void listen(Message message){
//        String messageBody = new String(message.getBody());
////        json메시지를 ObjectMapper로 직접 parsing
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            StockDecreaseEvent stockDecreaseEvent = objectMapper.readValue(messageBody, StockDecreaseEvent.class);
////        재고update
//            Product product = productRepository.findById(stockDecreaseEvent.getProductId()).orElseThrow(()->new EntityNotFoundException("not found"));
//            product.updateStockQuantity(stockDecreaseEvent.getProductCount());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//}
