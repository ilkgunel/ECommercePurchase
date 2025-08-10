package com.ecommercepurchase.record;

public record BillListResponse(Long billNo, Long amount, String productName,
                               boolean status, Long salesPersonId, String salesPersonFirstName,
                               String salesPersonLastName, String salesPersonEmail) {
}
