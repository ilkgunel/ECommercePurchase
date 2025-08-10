package com.ecommercepurchase.interfaces;

import com.ecommercepurchase.entities.Bill;
import com.ecommercepurchase.record.BillListResponse;
import com.ecommercepurchase.record.BillRequest;
import com.ecommercepurchase.record.BillResponse;

import java.util.List;

public interface BillInterface {

    BillResponse addBill(BillRequest billRequest);
    List<BillListResponse> getBillsByStatus(String userName, boolean billStatus);

}
