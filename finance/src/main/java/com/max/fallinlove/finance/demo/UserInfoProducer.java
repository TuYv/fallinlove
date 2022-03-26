package com.max.fallinlove.finance.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Max.Tu
 * @program common
 * @description
 * @create 2022-03-20 17:50
 **/
@Component
public class UserInfoProducer {
    @Autowired private KafkaTemplate kafkaTemplate;

    public void sendMessage() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid("1").setUsername("邹雨薇").setState("躺在沙发上");

        System.err.println("发送用户日志数据:"+userInfo);
        kafkaTemplate.send("atour", JSON.toJSONString(userInfo));

    }
}
