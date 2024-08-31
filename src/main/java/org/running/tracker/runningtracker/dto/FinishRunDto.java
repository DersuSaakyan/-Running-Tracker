package org.running.tracker.runningtracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.running.tracker.runningtracker.persistence.entity.RunEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class FinishRunDto {
    @NotNull
    private Long userId;
    @NotNull
    private Double finishLatitude;
    @NotNull
    private Double finishLongitude;
    @NotNull
    private LocalDateTime finishDateTime;
    private double distance;

    public static FinishRunDto from(RunEntity entity) {
        final FinishRunDto dto = new FinishRunDto();
        dto.setUserId(entity.getUserId());
        dto.setFinishLatitude(entity.getFinishLatitude());
        dto.setFinishLongitude(entity.getFinishLongitude());
        dto.setFinishDateTime(entity.getFinishDateTime());
        dto.setDistance(entity.getDistance());

        return dto;
    }
}
