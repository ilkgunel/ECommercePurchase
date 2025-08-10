package com.ecommercepurchase.controller;

import com.ecommercepurchase.interfaces.BillInterface;
import com.ecommercepurchase.record.BillRequest;
import com.ecommercepurchase.record.BillResponse;
import com.ecommercepurchase.entities.Bill;
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

    /**
     * @author ilkgunel93@gmail.com
     * This is a REST API endpoint to take a BillRequest from client and to insert a Bill to database
     * @param billRequest contains the bill information which is being wanted to save.
     * @return a parameter which is in ResponseEntity<BillResponse> type to inform client bill saved or rejected.
     */
    @PostMapping("/create")
    public ResponseEntity<BillResponse> createBill(@RequestBody BillRequest billRequest) {
        BillResponse billResponse = billInterface.addBill(billRequest);

        return new ResponseEntity<BillResponse>(billResponse, HttpStatus.CREATED);
    }

    /**
     * @author ilkgunel93@gmail.com
     * This is a REST API endpoint to return approved bills of sales person who is autheticated.
     * @param userDetails is not coming from client, Spring Boot fill is for us to detect who authenticated user is. We're getting userName property of authenticated user.
     * @return a parameter which is in ResponseEntity<List<Bill>>. The client will get a list to get approved bills.
     * @apiNote this service and below service can be combined, the bill status can be obtained from customer as path param or path variable. Actually, no need write 2 services.
     */
    @GetMapping("/list/approved")
    public ResponseEntity<List<Bill>> getApprovedBills(@AuthenticationPrincipal UserDetails userDetails) {
        List<Bill> approvedBills = billInterface.getBillsByStatus(userDetails.getUsername(), true);
        return ResponseEntity.ok(approvedBills);
    }

    /**
     * @author ilkgunel93@gmail.com
     * This is a REST API endpoint to return rejected bills of sales person who is autheticated.
     * @param userDetails is not coming from client, Spring Boot fill is for us to detect who authenticated user is. We're getting userName property of authenticated user.
     * @return a parameter which is in ResponseEntity<List<Bill>>. The client will get a list to get rejected bills.
     * @apiNote this service and above service can be combined, the bill status can be obtained from customer as path param or path variable. Actually, no need write 2 services.
     */
    @GetMapping("/list/rejected")
    public ResponseEntity<List<Bill>> getRejectedBills(@AuthenticationPrincipal UserDetails userDetails) {
        List<Bill> rejectedBillList = billInterface.getBillsByStatus(userDetails.getUsername(), false);
        return ResponseEntity.ok(rejectedBillList);
    }

}
