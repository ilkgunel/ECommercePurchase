package com.ecommercepurchase.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UnacceptableBillEventConsumer implements ApplicationListener<UnacceptableBillEvent> {

    @Override
    public void onApplicationEvent(UnacceptableBillEvent unacceptableBillEvent) {
        System.out.println("Received the UnacceptableBillEvent. The message is: " + unacceptableBillEvent.getMessage());
    }
}
