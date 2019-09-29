# test-nexthink-ws

This program allow to track process performance.
•	% of CPU
•	private memory
•	number of handles/file descriptors


For this, you need to specify as input information:

•	Process Name (running on the system)
•	Program Run Duration
•	Sampling Interval (default 5 sec)



## Technical prerequisites

To run this project, you need:

* An UNIX OS distribution
* The Docker software to install from here (distribution to modify if needed): https://docs.docker.com/install/linux/docker-ce/ubuntu/
* Maven version 5 or more recent
* JDK 8

 ### Command Line Interface
1. Start all in one : 
The following command will :

1. run build and run the project
 ```
./runner.sh  -DprocessName="processName=update-notifier" -DprogramDuration="programDuration=10" -DsamplingInterval="samplingInterval=2" 
 ```

Or with maven directly:
```
 mvn clean test -DprocessName="processName=update-notifier" -DprogramDuration="programDuration=10" -DsamplingInterval="samplingInterval=2" 
``` 
