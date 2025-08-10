package com.ecommercepurchase.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UnacceptableBillEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishUnacceptableBillEvent(final String message) {
        System.out.println("Publishing the UnacceptableBillEvent!");
        UnacceptableBillEvent unacceptableBillEvent = new UnacceptableBillEvent(this, message);
        applicationEventPublisher.publishEvent(unacceptableBillEvent);
    }
}
