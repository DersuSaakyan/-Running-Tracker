package org.running.tracker.runningtracker.dto;

import lombok.Getter;
import lombok.Setter;
import org.running.tracker.runningtracker.persistence.entity.RunEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class RunDto {
    private Long userId;
    private Double startLatitude;
    private Double startLongitude;
    private LocalDateTime startDateTime;
    private Double finishLatitude;
    private Double finishLongitude;
    private LocalDateTime finishDateTime;
    private double distance;
    private double averageSpeed;

    public static RunDto toStartRunDto(RunEntity entity) {
        final RunDto dto = new RunDto();
        dto.setUserId(entity.getUserId());
        dto.setStartLatitude(entity.getStartLatitude());
        dto.setStartLongitude(entity.getStartLongitude());
        dto.setStartDateTime(entity.getStartDateTime());

        return dto;
    }

    public static RunDto from(RunEntity entity) {
        final RunDto dto = toStartRunDto(entity);
        dto.setFinishLatitude(entity.getFinishLatitude());
        dto.setFinishLongitude(entity.getFinishLongitude());
        dto.setFinishDateTime(entity.getFinishDateTime());
        dto.setDistance(entity.getDistance());

        return dto;
    }
}
