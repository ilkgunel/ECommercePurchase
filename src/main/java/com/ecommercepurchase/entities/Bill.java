package com.ecommercepurchase.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.processing.Exclude;

@Entity
public class Bill {

    @Id
    @Column(name = "bill_no")
    private Long billNo;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "salesPersonId", referencedColumnName = "id")
    @JsonIgnore
    private SalesPerson salesPersonId;

    public Long getBillNo() {
        return billNo;
    }

    public void setBillNo(Long billNo) {
        this.billNo = billNo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SalesPerson getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(SalesPerson salesPersonId) {
        this.salesPersonId = salesPersonId;
    }
}
