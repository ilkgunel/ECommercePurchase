package com.ecommercepurchase.interfaces;

import com.ecommercepurchase.entities.Bill;
import com.ecommercepurchase.record.BillRequest;
import com.ecommercepurchase.record.BillResponse;

import java.util.List;

public interface BillInterface {

    public BillResponse addBill(BillRequest billRequest);
    public List<Bill> getRejectedBills(String userName);

}
