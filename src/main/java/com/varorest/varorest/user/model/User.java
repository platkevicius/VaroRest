package com.varorest.varorest.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Time;
import java.util.List;

@Entity
@Table
@Getter
@EqualsAndHashCode
public class User {

    @Id
    @Setter(AccessLevel.NONE)
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

    public User() {
    }

    @Builder
    public User(String uuid, boolean alive, Time lastLogging, boolean online, double x, double y, double z) {
        this.uuid = uuid;
        this.alive = alive;
        this.lastLogging = lastLogging;
        this.online = online;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean isAlive() {
        return alive;
    }

    public Time getLastLogging() {
        return lastLogging;
    }

    public boolean isOnline() {
        return online;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
