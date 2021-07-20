package com.varorest.varorest.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Time;
import java.util.List;

@Entity
@Table
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column
    private String uuid;

    @Column
    private boolean alive;

    @Column
    private Time lastLogging;

    @Column
    private boolean online;

    @Column
    private double x;

    @Column
    private double y;

    @Column
    private double z;

    @OneToMany(mappedBy = "killer")
    private List<UserKills> userKiller;

    @OneToOne(mappedBy = "killed")
    private UserKills userKilled;

    @OneToOne(mappedBy = "member1")
    private UserTeam member1;

    @OneToOne(mappedBy = "member2")
    private UserTeam member2;

    @Transient
    private int kills;

    public User() { }

    @Builder
    public User(boolean alive, Time lastLogging, boolean online, double x, double y, double z) {
        this.alive = alive;
        this.lastLogging = lastLogging;
        this.online = online;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
