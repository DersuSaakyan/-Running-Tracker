package org.running.tracker.runningtracker.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "run")
public class RunEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "start_latitude", nullable = false)
    private Double startLatitude;

    @Column(name = "start_longitude", nullable = false)
    private Double startLongitude;

    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "finish_latitude")
    private Double finishLatitude;

    @Column(name = "finish_longitude")
    private Double finishLongitude;

    @Column(name = "finish_datetime")
    private LocalDateTime finishDateTime;

    @Column(name = "distance")
    private Double distance;

    @JsonIgnore
    public Long getUserId() {
        return user.getId();
    }
}