package org.running.tracker.runningtracker.dto;

import lombok.Getter;

@Getter
public class UserStatisticsDto {
    private final int numberOfRuns;
    private final double totalMeters;
    private final double averageSpeed;

    public UserStatisticsDto(int numberOfRuns, double totalMeters, double averageSpeed) {
        this.numberOfRuns = numberOfRuns;
        this.totalMeters = totalMeters;
        this.averageSpeed = averageSpeed;
    }
}