package com.ecommercepurchase.service;

import com.ecommercepurchase.record.BillResponse;
import com.ecommercepurchase.entities.Bill;
import com.ecommercepurchase.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    private final BillRepository billRepository;

    @Value("${com.ecommercepurchase.limit}")
    private int salesPersonLimit;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public BillResponse addBill(Bill bill) {
        String resultMessage = "";
        String status;

        long totalAmount = getApprovedSumAmount(bill) + bill.getAmount();
        if (totalAmount > salesPersonLimit) {
            bill.setStatus(false);
            status = "REDDEDİLDİ";
            resultMessage = "Limit aşımı nedeniyle fatura kabul edilmedi!";
        } else {
            bill.setStatus(true);
            status = "ONAYLANDI";
            resultMessage = "Fatura Kabul Edildi.";
        }
        billRepository.save(bill);

        return new BillResponse(status, resultMessage);
    }

    private Long getApprovedSumAmount(Bill bill) {
        String firstName = bill.getBillId().getFirstName();
        String lastName = bill.getBillId().getLastName();
        String email = bill.getBillId().getEmail();

        return billRepository.getSumAmount(firstName, lastName, email).orElse(0L);
    }
}
