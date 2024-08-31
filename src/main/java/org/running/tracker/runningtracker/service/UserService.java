package org.running.tracker.runningtracker.service;

import lombok.RequiredArgsConstructor;
import org.running.tracker.runningtracker.criteria.RunCriteria;
import org.running.tracker.runningtracker.dto.RunDto;
import org.running.tracker.runningtracker.dto.UserDto;
import org.running.tracker.runningtracker.dto.UserStatisticsDto;
import org.running.tracker.runningtracker.exception.EntityNotFoundException;
import org.running.tracker.runningtracker.persistence.entity.RunEntity;
import org.running.tracker.runningtracker.persistence.entity.UserEntity;
import org.running.tracker.runningtracker.persistence.repository.RunRepository;
import org.running.tracker.runningtracker.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RunRepository runRepository;
    private final RunningCalculator runningCalculator;

    public UserDto addUser(UserDto userDto) {
        return UserDto.from(userRepository.save(UserDto.from(userDto)));
    }

    public UserDto editUser(Long userId, UserDto userDto) {
        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user", userId));

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setSex(userDto.getSex());
        userEntity.setBirthDate(userDto.getBirthDate());

        userRepository.save(userEntity);
        return UserDto.from(userEntity);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public UserDto getUserById(Long userId) {
        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user", userId));

        return UserDto.from(userEntity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserDto::from)
                .sorted(Comparator.comparing(UserDto::getFirstName))
                .toList();
    }

    public List<RunDto> getUserAllRuns(Long id, RunCriteria criteria) {
        final UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user", id));

        final List<RunEntity> listOfRuns = runRepository.findByUserAndStartDateTimeBetween(userEntity,
                criteria.getFromDatetime(), criteria.getToDatetime());

        return listOfRuns.stream().map(entity -> {
                    final RunDto dto = RunDto.from(entity);
                    dto.setAverageSpeed(runningCalculator.calculateAverageSpeed(entity.getDistance(),
                            entity.getStartDateTime(), entity.getFinishDateTime()));
                    return dto;
                }).sorted(Comparator.comparing(RunDto::getStartDateTime))
                .toList();
    }

    public UserStatisticsDto getUserStatistics(Long userId, RunCriteria criteria) {

        final List<RunDto> runs = this.getUserAllRuns(userId, criteria);

        final int numberOfRuns = runs.size();
        final double totalMeters = runs.stream().mapToDouble(RunDto::getDistance).sum();
        final double averageSpeed = runs.stream()
                .mapToDouble(RunDto::getAverageSpeed)
                .average().orElse(0);

        return new UserStatisticsDto(numberOfRuns, totalMeters, averageSpeed);
    }
}
