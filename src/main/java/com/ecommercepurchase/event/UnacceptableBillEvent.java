package com.ecommercepurchase.event;

import org.springframework.context.ApplicationEvent;

public class UnacceptableBillEvent extends ApplicationEvent {

    private String message;

    public UnacceptableBillEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
