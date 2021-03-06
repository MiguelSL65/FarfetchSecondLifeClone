package com.luxclusifmiguel.challenge.backend.persistence;

import com.luxclusifmiguel.challenge.backend.model.Image;
import com.luxclusifmiguel.challenge.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Common interface for Product data access objects
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
}
