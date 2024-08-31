package org.running.tracker.runningtracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.running.tracker.runningtracker.persistence.entity.UserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDateTime birthDate;
    @NotNull
    private String sex;

    public static UserEntity from(UserDto dto) {
        final UserEntity entity = new UserEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setSex(dto.getSex());
        entity.setBirthDate(dto.getBirthDate());

        return entity;
    }

    public static UserDto from(UserEntity entity) {
        final UserDto dto = new UserDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setSex(entity.getSex());
        dto.setBirthDate(entity.getBirthDate());

        return dto;
    }
}
