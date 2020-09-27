package com.luxclusifmiguel.challenge.persistence;

import com.luxclusifmiguel.challenge.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<Image, String> {

}
