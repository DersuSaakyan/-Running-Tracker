package org.running.tracker.runningtracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.running.tracker.runningtracker.persistence.entity.RunEntity;
import org.running.tracker.runningtracker.persistence.entity.UserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class StartRunDto {
    @NotNull
    private Long userId;
    @NotNull
    private Double startLatitude;
    @NotNull
    private Double startLongitude;
    @NotNull
    private LocalDateTime startDateTime;


}
