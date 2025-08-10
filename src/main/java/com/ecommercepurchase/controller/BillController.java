package com.ecommercepurchase.controller;

import com.ecommercepurchase.interfaces.BillInterface;
import com.ecommercepurchase.record.BillRequest;
import com.ecommercepurchase.record.BillResponse;
import com.ecommercepurchase.entities.Bill;
import com.ecommercepurchase.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillInterface billInterface;

    public BillController(BillInterface billInterface) {
        this.billInterface = billInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<BillResponse> createBill(@RequestBody BillRequest billRequest) {
        BillResponse billResponse = billInterface.addBill(billRequest);

        return new ResponseEntity<BillResponse>(billResponse, HttpStatus.CREATED);
    }

    @GetMapping("/list/rejected")
    public ResponseEntity<List<Bill>> getRejectedBills(@AuthenticationPrincipal UserDetails userDetails) {
        List<Bill> rejectedBillList = billInterface.getRejectedBills(userDetails.getUsername());

        return ResponseEntity.ok(rejectedBillList);
    }

}
