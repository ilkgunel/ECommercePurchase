package com.ecommercepurchase.exception;

import com.ecommercepurchase.entities.Bill;

public class UnacceptableBillException extends RuntimeException {

    public UnacceptableBillException(String message) {
        super(message);
    }

    private Bill bill;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
