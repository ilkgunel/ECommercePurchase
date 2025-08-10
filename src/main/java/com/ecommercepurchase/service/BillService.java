package com.ecommercepurchase.service;

import com.ecommercepurchase.entities.SalesPerson;
import com.ecommercepurchase.exception.UnacceptableBillException;
import com.ecommercepurchase.interfaces.BillInterface;
import com.ecommercepurchase.record.BillRequest;
import com.ecommercepurchase.record.BillResponse;
import com.ecommercepurchase.entities.Bill;
import com.ecommercepurchase.repository.BillRepository;
import com.ecommercepurchase.repository.SalesPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements BillInterface {

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

    /**
     * @author ilkgunel93@gmail.com
     * This method is being used to add a new bill record to database.
     * If the bill amount is less than sales person's approved bill amount, this method will save is without any problem
     * If the bill amount is less than sales person's approved bill amount, this method will throw an UnacceptableBillException
     * @param billRequest contains the bill information which will be saved or rejected
     * @return billResponse gives information about the bill which is accepted or rejected
     */
    @Override
    public BillResponse addBill(BillRequest billRequest) {

        SalesPerson salesPerson = salesPersonRepository
                .findByFirstNameAndLastNameAndEmail(billRequest.firstName(), billRequest.lastName(), billRequest.email());

        Bill bill = convertBillRequestToBill(billRequest);
        bill.setSalesPersonId(salesPerson);

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

    @Override
    public List<Bill> getBillsByStatus(String userName, boolean status) {
        SalesPerson salesPerson = salesPersonRepository.findByEmail(userName);

        return billRepository.findAllBysalesPersonIdIdAndStatus(salesPerson.getId(), status);
    }

    private Long getApprovedSumAmount(Long salesPersonId) {
        return billRepository.getSumAmount(salesPersonId, true).orElse(0L);
    }

    private Bill convertBillRequestToBill(BillRequest billRequest) {

        Bill bill = new Bill();
        bill.setBillNo(billRequest.billNo());
        bill.setAmount(billRequest.amount());
        bill.setProductName(billRequest.productName());

        return bill;

    }
}
