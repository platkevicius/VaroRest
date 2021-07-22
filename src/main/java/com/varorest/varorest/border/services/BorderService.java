package com.varorest.varorest.border.services;

import com.varorest.varorest.border.model.Border;
import com.varorest.varorest.border.repositories.BorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorderService {

    private final BorderRepository borderRepository;

    @Autowired
    public BorderService(BorderRepository borderRepository) {
        this.borderRepository = borderRepository;
    }

    public Optional<Border> getBorderById(long id) {
        Optional<Border> border = borderRepository.findById(id);

        if (border.isEmpty())
            return Optional.empty();

        return border;
    }

}
