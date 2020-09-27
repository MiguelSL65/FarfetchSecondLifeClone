package com.luxclusifmiguel.challenge.persistence;

import com.luxclusifmiguel.challenge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Common interface for User data access objects
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
}
