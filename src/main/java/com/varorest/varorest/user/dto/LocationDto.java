package com.varorest.varorest.user.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LocationDto {

    private final double x, y, z;

    public LocationDto(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
