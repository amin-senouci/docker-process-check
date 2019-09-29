package com.example.simplerestapis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplerestapis.models.SampleResponse;

@RestController
public class WebController {

    @RequestMapping("/check")
    public SampleResponse Sample(
            @RequestParam(value = "processName", defaultValue = "update-notifier") String processName,
            @RequestParam(value = "programDuration", defaultValue = "10") String programDuration,
            @RequestParam(value = "samplingInterval", defaultValue = "2") String samplingInterval

    ) throws Exception {

        CheckPerformance check = new CheckPerformance();
        check.init(processName, Integer.valueOf(programDuration), Integer.valueOf(samplingInterval));
        System.out.println("Number of files description for the process " + processName + " is " + check.avgFilesDesc());
        System.out.println("Average rate CPU: " + check.avgRateCPU());
        System.out.println("Average private memory: " + check.avgPrivateMemory());

        SampleResponse response = new SampleResponse();
        response.setAvgRateCPU(check.avgRateCPU());
        response.setAvgPrivateMemory(check.avgPrivateMemory());
        response.setAvgFilesDesc(check.avgFilesDesc());
        response.setProcessusHealth(check.getProcessusHealth());
        return response;

    }
}