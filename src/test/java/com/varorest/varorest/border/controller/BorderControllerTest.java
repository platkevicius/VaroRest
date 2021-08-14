package com.varorest.varorest.border.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varorest.varorest.border.model.Border;
import com.varorest.varorest.border.services.BorderService;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BorderController.class)
public class BorderControllerTest {

    @MockBean
    private BorderService borderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBorder() throws Exception {
        // given
        Border expected = Border.builder().x(5).y(2).z(10).build();

        // when
        Mockito.when(borderService.getBorderById(1L)).thenReturn(Optional.of(expected));

        // then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/border?id=1")
                                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writer().writeValueAsString(expected);

        String result = mvcResult.getResponse().getContentAsString();

        assertEquals(expectedJson, result);
    }

}