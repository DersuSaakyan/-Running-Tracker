package org.running.tracker.runningtracker.persistence.repository;

import org.running.tracker.runningtracker.persistence.entity.RunEntity;
import org.running.tracker.runningtracker.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RunRepository extends JpaRepository<RunEntity, Long> {

    @Query("""
            SELECT r FROM RunEntity r
            WHERE r.user.id = :userId
                AND r.startDateTime IS NOT NULL
                AND r.finishDateTime IS NULL
            ORDER BY r.startDateTime DESC LIMIT 1""")
    Optional<RunEntity> findUserStartRun(Long userId);

    List<RunEntity> findByUserAndStartDateTimeBetween(UserEntity user, LocalDateTime from, LocalDateTime to);
}
