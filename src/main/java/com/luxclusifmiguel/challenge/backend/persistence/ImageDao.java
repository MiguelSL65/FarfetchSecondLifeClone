package com.luxclusifmiguel.challenge.backend.persistence;

import com.luxclusifmiguel.challenge.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<Image, Integer> {

}
