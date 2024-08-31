package org.running.tracker.runningtracker.service;

import lombok.RequiredArgsConstructor;
import org.running.tracker.runningtracker.dto.FinishRunDto;
import org.running.tracker.runningtracker.dto.RunDto;
import org.running.tracker.runningtracker.dto.StartRunDto;
import org.running.tracker.runningtracker.exception.EntityNotFoundException;
import org.running.tracker.runningtracker.persistence.entity.RunEntity;
import org.running.tracker.runningtracker.persistence.entity.UserEntity;
import org.running.tracker.runningtracker.persistence.repository.RunRepository;
import org.running.tracker.runningtracker.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RunService {

    private final UserRepository userRepository;
    private final RunRepository runRepository;
    private final RunningCalculator distanceCalculator;

    public RunDto startRun(StartRunDto dto) {
        final UserEntity userEntity = this.getUserEntity(dto.getUserId());

        final RunEntity runEntity = new RunEntity();
        runEntity.setUser(userEntity);
        runEntity.setStartDateTime(dto.getStartDateTime());
        runEntity.setStartLatitude(dto.getStartLatitude());
        runEntity.setStartLongitude(dto.getStartLongitude());

        return RunDto.toStartRunDto(runRepository.save(runEntity));
    }

    public RunDto finishRun(FinishRunDto dto) {
        final UserEntity userEntity = this.getUserEntity(dto.getUserId());

        final RunEntity runEntity = runRepository.findUserStartRun(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User start run is not found"));

        runEntity.setUser(userEntity);
        runEntity.setFinishLatitude(dto.getFinishLatitude());
        runEntity.setFinishLongitude(dto.getFinishLongitude());
        runEntity.setFinishDateTime(dto.getFinishDateTime());
        runEntity.setDistance(dto.getDistance());

        if (runEntity.getDistance() == null) {
            runEntity.setDistance(distanceCalculator.calculateDistance(
                    runEntity.getStartLatitude(),
                    runEntity.getStartLongitude(),
                    runEntity.getFinishLatitude(),
                    runEntity.getFinishLongitude()
            ));
        }

        return RunDto.from(runRepository.save(runEntity));
    }

    private UserEntity getUserEntity(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user", userId));
    }
}
