package com.varorest.varorest.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varorest.varorest.user.dto.LocationDto;
import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserTeam;
import com.varorest.varorest.user.services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Tests whether the controller returns the correct value")
    public void givenUser_whenIsAlive_thenReturnTrue() throws Exception {
        User expected = User.builder().uuid("test")
                    .alive(true)
                    .build();

        Mockito.when(userService.isAlive(expected.getUuid())).thenReturn(Optional.of(expected.isAlive()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/alive?uuid=" + expected.getUuid())
                                                        .contentType(MediaType.APPLICATION_JSON))
                                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                                        .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(String.valueOf(expected.isAlive()), content);
    }

    @Test
    @DisplayName("Tests whether the controller returns the correct amount of kills")
    public void returnCorrectAmountOfKills() throws Exception {
        User killer = User.builder().uuid("test").build();
        int kills = 4;

        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(userService.getKills(killer)).thenReturn(Optional.of(kills));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/kills").contentType(MediaType.APPLICATION_JSON)
                                     .content(objectMapper.writeValueAsString(killer)))
                                     .andExpect(MockMvcResultMatchers.status().isOk())
                                     .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(kills, Integer.parseInt(content));
    }

    @Test
    @DisplayName("Test whether the controller returns the correct coordinates for a player")
    public void returnCorrectCoordinates() throws Exception {
        User user = User.builder().uuid("test")
                        .x(20)
                        .y(15)
                        .z(10)
                        .build();

        Mockito.when(userService.getPlayerLocation(user.getUuid())).thenReturn(
                Optional.of(new LocationDto(user.getX(), user.getY(), user.getZ()))
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/location?uuid=test")
                                              .contentType(MediaType.APPLICATION_JSON))
                                              .andExpect(MockMvcResultMatchers.status().isOk())
                                              .andExpect(MockMvcResultMatchers.jsonPath("$.x").value(user.getX()))
                                              .andExpect(MockMvcResultMatchers.jsonPath("$.y").value(user.getY()))
                                              .andExpect(MockMvcResultMatchers.jsonPath("$.z").value(user.getZ()));
    }

    @Test
    @DisplayName("Test whether the controller returns the correct List of Teams")
    public void returnCorrectTeams() throws Exception {
        User member1 = User.builder().uuid("test1").build();
        User member2 = User.builder().uuid("test2").build();

        UserTeam userTeam = UserTeam.builder().member1(member1).member2(member2).build();
        List<UserTeam> actual = List.of(userTeam);

        Mockito.when(userService.getAllTeams()).thenReturn(Optional.of(actual));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/teams").contentType(MediaType.APPLICATION_JSON))
                                              .andExpect(MockMvcResultMatchers.status().isOk())
                                              .andExpect(MockMvcResultMatchers.jsonPath("$[0].member1.uuid").value(userTeam.getMember1().getUuid()))
                                              .andExpect(MockMvcResultMatchers.jsonPath("$[0].member2.uuid").value(userTeam.getMember2().getUuid()));
    }

}