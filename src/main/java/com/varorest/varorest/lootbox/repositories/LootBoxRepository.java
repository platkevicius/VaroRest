package com.varorest.varorest.lootbox.repositories;

import com.varorest.varorest.lootbox.model.LootBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LootBoxRepository extends JpaRepository<LootBox, Long> {
}
