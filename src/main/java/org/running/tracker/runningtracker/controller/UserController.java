package org.running.tracker.runningtracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.running.tracker.runningtracker.criteria.RunCriteria;
import org.running.tracker.runningtracker.dto.RunDto;
import org.running.tracker.runningtracker.dto.UserDto;
import org.running.tracker.runningtracker.dto.UserStatisticsDto;
import org.running.tracker.runningtracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable Long id,
                                            @RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.editUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/runs")
    public ResponseEntity<List<RunDto>> getUserAllRuns(@PathVariable Long id, RunCriteria criteria) {
        return ResponseEntity.ok(userService.getUserAllRuns(id, criteria));
    }

    @GetMapping("/{id}/run/statistics")
    public ResponseEntity<UserStatisticsDto> getUserStatistics(@PathVariable Long id, RunCriteria criteria) {
        return ResponseEntity.ok(userService.getUserStatistics(id, criteria));
    }
}
