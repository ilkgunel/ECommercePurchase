package com.ecommercepurchase.service;

import com.ecommercepurchase.entities.SalesPerson;
import com.ecommercepurchase.exception.UnacceptableBillException;
import com.ecommercepurchase.record.BillRequest;
import com.ecommercepurchase.record.BillResponse;
import com.ecommercepurchase.entities.Bill;
import com.ecommercepurchase.repository.BillRepository;
import com.ecommercepurchase.repository.SalesPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final SalesPersonRepository salesPersonRepository;

    private final String ACCEPTED = "ONAYLANDI";
    private final String ACCEPTED_RESULT_MESSAGE = "Fatura Kabul Edildi";


    @Value("${com.ecommercepurchase.limit}")
    private int salesPersonLimit;

    @Autowired
    public BillService(BillRepository billRepository, SalesPersonRepository salesPersonRepository) {
        this.billRepository = billRepository;
        this.salesPersonRepository = salesPersonRepository;
    }

    public BillResponse addBill(BillRequest billRequest) {

        SalesPerson salesPerson = salesPersonRepository
                .findByFirstNameAndLastNameAndEmail(billRequest.firstName(), billRequest.lastName(), billRequest.email());

        Bill bill = convertBillRequestToBill(billRequest);

        long totalAmount = getApprovedSumAmount(salesPerson.getId()) + bill.getAmount();
        if (totalAmount > salesPersonLimit) {
            UnacceptableBillException unacceptableBillException = new UnacceptableBillException("Limit aşımı nedeniyle fatura kabul edilmedi!");
            unacceptableBillException.setBill(bill);
            throw unacceptableBillException;
        }

        bill.setStatus(true);
        billRepository.save(bill);

        return new BillResponse(ACCEPTED, ACCEPTED_RESULT_MESSAGE);
    }

    private Long getApprovedSumAmount(Long salesPersonId) {
        return billRepository.getSumAmount(salesPersonId).orElse(0L);
    }

    private Bill convertBillRequestToBill(BillRequest billRequest) {

        Bill bill = new Bill();
        bill.setBillNo(billRequest.billNo());
        bill.setAmount(billRequest.amount());
        bill.setProductName(billRequest.productName());

        return bill;

    }
}
