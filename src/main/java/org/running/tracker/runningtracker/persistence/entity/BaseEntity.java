package org.running.tracker.runningtracker.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;
}
