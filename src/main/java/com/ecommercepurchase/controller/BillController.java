package com.ecommercepurchase.controller;

import com.ecommercepurchase.record.BillResponse;
import com.ecommercepurchase.entities.Bill;
import com.ecommercepurchase.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/create")
    public ResponseEntity<BillResponse> createBill(@RequestBody Bill bill) {
        BillResponse billResponse = billService.addBill(bill);

        return new ResponseEntity<BillResponse>(billResponse, HttpStatus.CREATED);
    }
}
