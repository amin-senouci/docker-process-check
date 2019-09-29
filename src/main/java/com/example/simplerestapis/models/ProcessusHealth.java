package com.example.simplerestapis.models;

import java.sql.Timestamp;

public class ProcessusHealth {
    private float cpu;
    private float mem;
    private int nbFilesDesc;

    private Timestamp timestamps;

    public ProcessusHealth(float cpu, float mem, int nbFilesDesc) {
        this.cpu = cpu;
        this.mem = mem;
        this.nbFilesDesc = nbFilesDesc;
        this.timestamps = new Timestamp(System.currentTimeMillis());
    }

    public float getCpu() {
        return cpu;
    }

    public void setCpu(float cpu) {
        this.cpu = cpu;
    }

    public float getMem() {
        return mem;
    }

    public void setMem(float mem) {
        this.mem = mem;
    }

    public Timestamp getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Timestamp timestamps) {
        this.timestamps = timestamps;
    }

    public int getNbFilesDesc() {
        return nbFilesDesc;
    }

    public void setNbFilesDesc(int nbFilesDesc) {
        this.nbFilesDesc = nbFilesDesc;
    }


}
