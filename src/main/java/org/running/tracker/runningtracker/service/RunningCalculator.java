package org.running.tracker.runningtracker.service;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class RunningCalculator {

    private final static int EARTH_RADIUS_IN_METERS = 6371000;

    public double calculateDistance(double startLatitude, double startLongitude, double finishLatitude, double finishLongitude) {
        // Use Haversine formula to calculate distance in meters
        final double latDistance = Math.toRadians(finishLatitude - startLatitude);
        final double lonDistance = Math.toRadians(finishLongitude - startLongitude);
        final double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                         + Math.cos(Math.toRadians(startLatitude)) * Math.cos(Math.toRadians(finishLatitude))
                           * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_IN_METERS * c / 1000;
    }

    public double calculateAverageSpeed(double distance, LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        final double durationInHours = this.calculateDurationInHours(startDateTime, finishDateTime);
        if (durationInHours == 0) {
            return 0;
        }
        return distance / durationInHours;  // Speed in meters per hour
    }

    private double calculateDurationInHours(LocalDateTime startDatetime, LocalDateTime finishDatetime) {
        return Duration.between(startDatetime, finishDatetime).toHours();
    }
}
