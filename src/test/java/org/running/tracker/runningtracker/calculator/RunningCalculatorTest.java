package org.running.tracker.runningtracker.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.running.tracker.runningtracker.service.RunningCalculator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunningCalculatorTest {

    private RunningCalculator runningCalculator;

    @BeforeEach
    public void init() {
        runningCalculator = new RunningCalculator();
    }

    @Test
    public void testCalculateDistance_SameLocation() {
        double startLatitude = 0.0;
        double startLongitude = 0.0;
        double finishLatitude = 0.0;
        double finishLongitude = 0.0;

        double distance = runningCalculator.calculateDistance(startLatitude, startLongitude, finishLatitude, finishLongitude);

        assertEquals(0.0, distance, 0.01, "Distance should be 0 when start and finish are the same location.");
    }

    @Test
    public void testCalculateDistance_DifferentLocations() {
        double startLatitude = 52.5200; // Berlin
        double startLongitude = 13.4050;
        double finishLatitude = 48.8566; // Paris
        double finishLongitude = 2.3522;

        double distance = runningCalculator.calculateDistance(startLatitude, startLongitude, finishLatitude, finishLongitude);

        assertEquals(878, distance,5, "Distance between Berlin and Paris should be around 878 km.");
    }

    @Test
    public void testCalculateAverageSpeed_ValidDuration() {
        double distance = 10000.0; // 10 km
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 31, 8, 0, 0);
        LocalDateTime finishDateTime = LocalDateTime.of(2024, 8, 31, 9, 0, 0);

        double averageSpeed = runningCalculator.calculateAverageSpeed(distance, startDateTime, finishDateTime);

        assertEquals(10000.0, averageSpeed, "Average speed should be 10,000 meters per hour.");
    }

    @Test
    public void testCalculateDurationInHours_ValidDuration() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 31, 8, 0, 0);
        LocalDateTime finishDateTime = LocalDateTime.of(2024, 8, 31, 10, 0, 0);

        double durationInHours = runningCalculator.calculateAverageSpeed(10, startDateTime, finishDateTime);

        assertEquals(5.0, durationInHours, "Duration should be 2 hours.");
    }

    @Test
    public void testCalculateDurationInHours_ZeroDuration() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 31, 8, 0, 0);
        LocalDateTime finishDateTime = LocalDateTime.of(2024, 8, 31, 8, 0, 0);

        double durationInHours = runningCalculator.calculateAverageSpeed(0, startDateTime, finishDateTime);

        assertEquals(0.0, durationInHours, "Duration should be 0 hours when start and finish times are the same.");
    }
}
