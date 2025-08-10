package com.ecommercepurchase.repository;

import com.ecommercepurchase.entities.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesPersonRepository extends JpaRepository<SalesPerson, Long> {

    SalesPerson findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
    SalesPerson findByEmail(String email);
}
