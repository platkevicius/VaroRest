package com.varorest.varorest.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class LocationDto {

    private final double x, y, z;

}
