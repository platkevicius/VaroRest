package com.varorest.varorest.lootbox.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.varorest.varorest.lootbox.model.LootBox;
import com.varorest.varorest.lootbox.services.LootBoxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LootBoxController.class)
public class LootBoxControllerTest {

    @MockBean
    private LootBoxService lootBoxService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findLootBoxes() throws Exception {
        // given
        List<LootBox> expected = List.of(LootBox.builder().build(), LootBox.builder().build(), LootBox.builder().build());

        // when
        Mockito.when(lootBoxService.getAllLootBoxes()).thenReturn(Optional.of(expected));

        // then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/lootbox")
                                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();


        byte[] result = mvcResult.getResponse().getContentAsByteArray();
        ObjectMapper objectMapper = new ObjectMapper();

        List<LootBox> actual = objectMapper.readValue(result, new TypeReference<List<LootBox>>() {});

        assertEquals(expected, actual);
    }

}