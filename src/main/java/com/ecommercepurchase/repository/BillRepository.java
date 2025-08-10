package com.ecommercepurchase.repository;

import com.ecommercepurchase.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "select SUM(b.amount) from Bill b where b.sales_person_id = :salesPersonId and b.status = :status", nativeQuery = true)
    Optional<Long> getSumAmount(Long salesPersonId, boolean status);

    List<Bill> findAllBysalesPersonIdIdAndStatus(Long billSalesPersonId, boolean status);
}
