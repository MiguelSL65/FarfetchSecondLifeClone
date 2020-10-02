package com.luxclusifmiguel.challenge.backend.persistence;

import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Common interface for User data access objects
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {


}
