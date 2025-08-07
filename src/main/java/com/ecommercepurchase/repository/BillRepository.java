package com.ecommercepurchase.repository;

import com.ecommercepurchase.compositekey.BillId;
import com.ecommercepurchase.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, BillId> {

    @Query(value = "select SUM(b.amount) from Bill b where b.billId.firstName = :firstName")
    Optional<Long> getSumAmount(String firstName, String lastName, String email);
}
