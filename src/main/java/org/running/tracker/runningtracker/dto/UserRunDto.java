package org.running.tracker.runningtracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRunDto {
    private UserDto user;
    private List<RunDto> runs;
}
