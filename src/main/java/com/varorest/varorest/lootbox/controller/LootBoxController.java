package com.varorest.varorest.lootbox.controller;

import com.varorest.varorest.lootbox.model.LootBox;
import com.varorest.varorest.lootbox.services.LootBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lootbox")
@CrossOrigin(origins = "*")
public class LootBoxController {

    private final LootBoxService lootBoxService;

    @Autowired
    public LootBoxController(LootBoxService lootBoxService) {
        this.lootBoxService = lootBoxService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<LootBox>> getLootBoxes() {
        Optional<List<LootBox>> lootBoxes = lootBoxService.getAllLootBoxes();

        if (lootBoxes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(lootBoxes.get() ,HttpStatus.OK);
    }

}
