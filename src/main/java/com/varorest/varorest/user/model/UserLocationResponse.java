package com.varorest.varorest.user.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLocationResponse {

    private final String name;
    private final double x, y, z;

}
