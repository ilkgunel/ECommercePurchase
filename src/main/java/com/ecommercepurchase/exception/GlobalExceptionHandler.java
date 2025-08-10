package com.ecommercepurchase.exception;

import com.ecommercepurchase.entities.Bill;
import com.ecommercepurchase.event.UnacceptableBillEventPublisher;
import com.ecommercepurchase.record.BillResponse;
import com.ecommercepurchase.repository.BillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final String REJECTED_STATUS = "REDDEDİLDİ";
    private final BillRepository billRepository;
    private final UnacceptableBillEventPublisher unacceptableBillEventPublisher;

    public GlobalExceptionHandler(BillRepository billRepository, UnacceptableBillEventPublisher unacceptableBillEventPublisher) {
        this.billRepository = billRepository;
        this.unacceptableBillEventPublisher = unacceptableBillEventPublisher;
    }

    @ExceptionHandler(UnacceptableBillException.class)
    public ResponseEntity<BillResponse> handleUnprocessableBillException(UnacceptableBillException ex) {
        Bill bill = ex.getBill();
        bill.setStatus(false);
        billRepository.save(bill);

        unacceptableBillEventPublisher.publishUnacceptableBillEvent(ex.getMessage());

        BillResponse billResponse = new BillResponse(REJECTED_STATUS, ex.getMessage());
        return new ResponseEntity<>(billResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
