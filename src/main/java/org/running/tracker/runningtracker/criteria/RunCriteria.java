package org.running.tracker.runningtracker.criteria;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RunCriteria {
    LocalDateTime fromDatetime;
    LocalDateTime toDatetime;
}
