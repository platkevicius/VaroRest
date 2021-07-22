package com.varorest.varorest.lootbox.services;

import com.varorest.varorest.lootbox.model.LootBox;
import com.varorest.varorest.lootbox.repositories.LootBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LootBoxService {

    private LootBoxRepository lootBoxRepository;

    @Autowired
    public LootBoxService(LootBoxRepository lootBoxRepository) {
        this.lootBoxRepository = lootBoxRepository;
    }

    public Optional<List<LootBox>> getAllLootBoxes() {
        List<LootBox> lootBoxes = lootBoxRepository.findAll();

        if (lootBoxes.isEmpty())
            return Optional.empty();

        return Optional.of(lootBoxes);
    }

}
