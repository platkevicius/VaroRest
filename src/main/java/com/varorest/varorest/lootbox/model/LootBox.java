package com.varorest.varorest.lootbox.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class LootBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    private double x;

    @Column
    private double y;

    @Column
    private double z;

    @Column
    private boolean opened;

    @Column
    private boolean created;

    @Builder
    public LootBox(double x, double y, double z, boolean opened) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.opened = opened;
    }

    public LootBox() { }
}
