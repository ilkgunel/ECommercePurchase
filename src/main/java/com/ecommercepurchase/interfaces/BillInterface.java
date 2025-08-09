package com.ecommercepurchase.interfaces;

import com.ecommercepurchase.record.BillRequest;
import com.ecommercepurchase.record.BillResponse;

public interface BillInterface {

    public BillResponse addBill(BillRequest billRequest);

}
