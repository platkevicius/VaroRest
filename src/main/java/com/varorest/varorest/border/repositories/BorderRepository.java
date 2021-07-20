package com.varorest.varorest.border.repositories;

import com.varorest.varorest.border.model.Border;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorderRepository extends JpaRepository<Border, Long> {
}
