package com.example.simplerestapis.models;

import java.util.Map;

public class SampleResponse {

    private float avgRateCPU;
    private float avgPrivateMemory;
    private int avgFilesDesc;


    private Map processusHealth;

    public float getAvgRateCPU() {
        return avgRateCPU;
    }

    public void setAvgRateCPU(float avgRateCPU) {
        this.avgRateCPU = avgRateCPU;
    }

    public float getAvgPrivateMemory() {
        return avgPrivateMemory;
    }

    public void setAvgPrivateMemory(float avgPrivateMemory) {
        this.avgPrivateMemory = avgPrivateMemory;
    }

    public int getAvgFilesDesc() {
        return avgFilesDesc;
    }

    public void setAvgFilesDesc(int avgFilesDesc) {
        this.avgFilesDesc = avgFilesDesc;
    }


    public Map getProcessusHealth() {
        return processusHealth;
    }

    public void setProcessusHealth(Map processusHealth) {
        this.processusHealth = processusHealth;
    }
}