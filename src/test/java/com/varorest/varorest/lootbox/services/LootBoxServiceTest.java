package com.varorest.varorest.lootbox.services;

import com.varorest.varorest.lootbox.model.LootBox;
import com.varorest.varorest.lootbox.repositories.LootBoxRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class LootBoxServiceTest {

    private LootBoxRepository lootBoxRepository;
    private LootBoxService lootBoxService;

    @Before
    public void setUp() {
        lootBoxRepository = Mockito.mock(LootBoxRepository.class);
        lootBoxService = new LootBoxService(lootBoxRepository);
    }

    @Test
    public void getAllLootboxes() {
        //given
        List<LootBox> lootBoxes = List.of(LootBox.builder().build(), LootBox.builder().build(), LootBox.builder().build());

        //when
        Mockito.when(lootBoxRepository.findAll()).thenReturn(lootBoxes);

        //then
        assertEquals(lootBoxes.size(), lootBoxService.getAllLootBoxes()
                                                     .orElseThrow(() -> new EntityNotFoundException("")).size());
    }

}