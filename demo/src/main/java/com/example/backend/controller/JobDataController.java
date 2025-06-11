package com.example.backend.controller;

import com.example.backend.dto.JobDataDto;
import com.example.backend.service.JobDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobDataController {

    private final JobDataService jobDataService;

    public JobDataController(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    @GetMapping("/getJobData")
    public ResponseEntity<List<JobDataDto>> getJobData() {
        List<JobDataDto> jobDataList = jobDataService.getAllJobData();
        return new ResponseEntity<>(jobDataList, HttpStatus.OK);
    }

}
