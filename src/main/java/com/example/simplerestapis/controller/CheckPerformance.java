package com.example.simplerestapis.controller;


import com.example.simplerestapis.models.ProcessusHealth;

import java.sql.Timestamp;
import java.util.*;


public class CheckPerformance {


    private static Map<Timestamp, ProcessusHealth> processusHealth = new HashMap<>();

    private static final String CPU = "CPU";
    private static final String MEM = "MEM";


    public void init(String processName, int programRunDuration, int samplingInterval) throws Exception {
        if (samplingInterval == 0) {
            samplingInterval = 5;
        }

        Timer timer = new Timer();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, programRunDuration);
        Date to = cal.getTime();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //call the method
                try {
                    processusHealth.put(new Timestamp(System.currentTimeMillis()), getProcessusHealth(processName));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());

                if (cal.getTime().compareTo(to) > 0) {
                    timer.cancel();
                }
            }
        }, new Date(), Long.valueOf(samplingInterval));//new Date(System.currentTimeMillis() + programRunDuration * 1000), new Date(System.currentTimeMillis() + samplingInterval * 1000));
        Thread.sleep(programRunDuration * 1000 + 5000);
    }


    public float avgRateCPU() throws Exception {
        int total = processusHealth.size();
        float cpu = 0;
        for (Map.Entry<Timestamp, ProcessusHealth> entry : processusHealth.entrySet()) {
            ProcessusHealth ps = entry.getValue();
            cpu = cpu + ps.getCpu();
        }
        return (cpu / total);
    }

    public float avgPrivateMemory() {
        int total = processusHealth.size();
        float mem = 0;
        for (Map.Entry<Timestamp, ProcessusHealth> entry : processusHealth.entrySet()) {
            ProcessusHealth ps = entry.getValue();
            mem = mem + ps.getMem();
        }
        return (mem / total);

    }


    public int avgFilesDesc() {
        int total = processusHealth.size();
        int nbFilesDesc = 0;
        for (Map.Entry<Timestamp, ProcessusHealth> entry : processusHealth.entrySet()) {
            ProcessusHealth ps = entry.getValue();
            nbFilesDesc = nbFilesDesc + Integer.valueOf(ps.getNbFilesDesc());
        }
        return (nbFilesDesc / total);

    }

    /**
     * Result to parse
     * // %CPU %MEM
     * // 0.0  1.3
     *
     * @param cpuMem
     * @param metric
     * @return
     * @throws Exception
     */
    private float extractCPUMem(List<String> cpuMem, String metric) throws Exception {


        String[] vals = cpuMem.get(1).split(" ");

        switch (metric) {
            case CPU:
                return Float.valueOf(vals[1]);
            case MEM:
                return Float.valueOf(vals[3]);
            default:
                throw new Exception("Unkown metric: " + metric);
        }
    }


    private int extractNbFilesDesc(List<String> lines) {
        return Integer.valueOf(lines.get(0));

    }

    private ProcessusHealth getProcessusHealth(String processName) throws Exception {
        List<String> cpuMem = ExecuteShellComand.run("ps -p $(pidof " + processName + ") -o %cpu,%mem");
        float cpu = extractCPUMem(cpuMem, CPU);
        float mem = extractCPUMem(cpuMem, MEM);

        List<String> lines = ExecuteShellComand.run("lsof -p  $(pidof " + processName + ") | wc -l");
        int nbFilesDesc = extractNbFilesDesc(lines);

        return new ProcessusHealth(cpu, mem, nbFilesDesc);
    }

    public Map<Timestamp, ProcessusHealth> getProcessusHealth() {
        return processusHealth;
    }


}
