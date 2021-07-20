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
    @Column
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    private int x;

    @Column
    private int y;

    @Column
    private int z;

    @Column
    private boolean opened;

    @Builder
    public LootBox(int x, int y, int z, boolean opened) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.opened = opened;
    }

    public LootBox() { }
}
