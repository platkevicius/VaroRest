package com.varorest.varorest.user.controller;

import com.varorest.varorest.user.dto.LocationDto;
import com.varorest.varorest.user.dto.UserStat;
import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserLocationResponse;
import com.varorest.varorest.user.model.UserTeam;
import com.varorest.varorest.user.services.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiResponse(
            description = "Endpoint for retrieving whether a User is alive by his uuid" +
                          "; The uuid is being passed as a parameter"
    )
    @GetMapping("/alive")
    @ResponseBody
    public ResponseEntity<Boolean> isAlive(@RequestParam String uuid) {
        Optional<Boolean> alive = userService.isAlive(uuid);

        if (alive.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(alive.get(), HttpStatus.OK);
    }

    @ApiResponse(
            description = "Endpoint for retrieving the number of kills, that a player currently possesses"
    )
    @PostMapping("/kills")
    @ResponseBody
    public ResponseEntity<Integer> getKills(@RequestParam String uuid) {
        Optional<Integer> kills = userService.getKills(uuid);

        if (kills.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(kills.get(), HttpStatus.OK);
    }

    @ApiResponse(
            description = "Endpoint for retrieving the number of kills, that a player currently possesses"
    )
    @GetMapping("/stats")
    @ResponseBody
    public ResponseEntity<List<UserStat>> getStats() {
        return new ResponseEntity<>(userService.getUserStats(), HttpStatus.OK);
    }

    @ApiResponse(
            description = "Endpoint for retrieving the number of kills, that a player currently possesses"
    )
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<String>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ApiResponse(
            description = "Endpoint for retrieving the location of a player"
    )
    @GetMapping("/location")
    @ResponseBody
    public ResponseEntity<LocationDto> getLocation(@RequestParam String uuid) {
        Optional<LocationDto> location = userService.getPlayerLocation(uuid);
        if (location.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(location.get(), HttpStatus.OK);
    }

    @ApiResponse(
            description = "Endpoint for retrieving a list of all teams and their respective members"
    )
    @GetMapping("/teams")
    @ResponseBody
    public ResponseEntity<List<UserTeam>> getTeams() {
        Optional<List<UserTeam>> teams = userService.getAllTeams();

        if (teams.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(teams.get(), HttpStatus.OK);
    }

    @ApiResponse(
            description = "Endpoint for retrieving a list of all teams and their respective members"
    )
    @GetMapping("/locations")
    @ResponseBody
    public ResponseEntity<List<UserLocationResponse>> getLocations() {
        List<UserLocationResponse> locations = userService.getLocations();

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }



}