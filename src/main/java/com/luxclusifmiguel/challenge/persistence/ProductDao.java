package com.luxclusifmiguel.challenge.persistence;

import com.luxclusifmiguel.challenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Common interface for Product data access objects
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

}
