package com.ead.authuser.publishers;

import com.ead.authuser.dtos.UserEventDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    final RabbitTemplate rabbitTemplate;
    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "ead.userevent")
    private String exchangeUserEvent;

    public void publisherUserEvent(UserEventDto userEventDto){
        rabbitTemplate.convertAndSend(exchangeUserEvent, "",userEventDto);
    }
}
