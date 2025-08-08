package com.ecommercepurchase.record;

public record BillRequest(String firstName, String lastName, String email, Long amount, String productName, Long billNo) {
}
