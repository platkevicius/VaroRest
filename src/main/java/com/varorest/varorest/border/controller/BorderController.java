package com.varorest.varorest.border.controller;

import com.varorest.varorest.border.model.Border;
import com.varorest.varorest.border.services.BorderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/Border")
@CrossOrigin(origins = "*")
public class BorderController {

    private final BorderService borderService;

    @Autowired
    public BorderController(BorderService borderService) {
        this.borderService = borderService;
    }

    @ApiResponse(
            description = "Endpoint for retrieving a border by id"
    )
    @GetMapping
    @ResponseBody
    public ResponseEntity<Border> getBorder(@RequestParam long id) {
        Optional<Border> border = borderService.getBorderById(id);

        if (border.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(border.get(), HttpStatus.OK);
    }

}
