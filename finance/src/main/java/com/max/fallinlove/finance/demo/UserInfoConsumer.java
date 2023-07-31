//package com.max.fallinlove.finance.demo;
//
//import java.util.Optional;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author Max.Tu
// * @program common
// * @description
// * @create 2022-03-20 17:53
// **/
//@Component
//@Slf4j
//public class UserInfoConsumer {
//    @KafkaListener(topics = {"atour"},groupId = "atourGroup1")
//    public void consumer(ConsumerRecord<?,?> consumerRecord){
//        //判断是否为null
//        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
//        log.info(">>>>>>>>>> record =" + kafkaMessage);
//        if(kafkaMessage.isPresent()){
//            //得到Optional实例中的值
//            Object message = kafkaMessage.get();
//            System.err.println("消费消息:"+message);
//        }
//
//    }
//}
