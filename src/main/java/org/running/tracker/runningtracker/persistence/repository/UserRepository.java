package org.running.tracker.runningtracker.persistence.repository;

import org.running.tracker.runningtracker.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
