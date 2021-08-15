package com.varorest.varorest.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserStat {

    private String name;
    private int kills;
    private boolean alive;

}
