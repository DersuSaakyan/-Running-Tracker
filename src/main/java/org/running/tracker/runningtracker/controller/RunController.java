package org.running.tracker.runningtracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.running.tracker.runningtracker.dto.FinishRunDto;
import org.running.tracker.runningtracker.dto.RunDto;
import org.running.tracker.runningtracker.dto.StartRunDto;
import org.running.tracker.runningtracker.service.RunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/runs")
@RequiredArgsConstructor
public class RunController {

    private final RunService runService;

    @PostMapping("/start")
    public ResponseEntity<RunDto> startRun(@RequestBody @Valid StartRunDto dto) {
        return ResponseEntity.ok(runService.startRun(dto));
    }

    @PostMapping("/finish")
    public ResponseEntity<RunDto> finishRun(@RequestBody @Valid FinishRunDto dto) {
        return ResponseEntity.ok(runService.finishRun(dto));
    }
}
