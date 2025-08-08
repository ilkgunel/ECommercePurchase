package com.ecommercepurchase.entities;

import jakarta.persistence.*;

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
    @JoinColumn(name = "bill_sales_person_id", referencedColumnName = "id")
    private SalesPerson salesPerson;

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

    public SalesPerson getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(SalesPerson salesPerson) {
        this.salesPerson = salesPerson;
    }
}
