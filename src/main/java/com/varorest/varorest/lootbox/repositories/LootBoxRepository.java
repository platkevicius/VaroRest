package com.varorest.varorest.lootbox.repositories;

import com.varorest.varorest.lootbox.model.LootBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LootBoxRepository extends JpaRepository<LootBox, Long> {
}
