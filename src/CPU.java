import java.io.IOException;
import java.util.Random;

public class CPU {
	private Files file;
	private Random ran ;
	private PriorityQueue<Process> IOBoundJob;
	private PriorityQueue<Process> CPUBoundJob;

	public CPU() throws IOException {
		file = new Files("C:/Users/abo_tam/Desktop/executed.txt");
		IOBoundJob = new PriorityQueue<Process>();
		CPUBoundJob = new PriorityQueue<Process>();
		ran = new Random();
		file.openStreamWriter();
	}

	public void MEC(Process process) throws IOException {
		process.setState("Running");
		int clock = 0;
		
		//while loop to execute instructions
		while (clock <= process.getRemainingTime()) {
			clock++;
			if(isTermenated()){
				// if the process terminates, then break the method
				process.setState("Terminated");
				RAM.loadProcessFromHardDisk();
				return;
			}
			if (isInterrupted(process)) {
				// if the process interrupted, then return the process to the Ready Queue (RAM)
				process.setState("Ready");
				process.setRemainingTime(process.getRemainingTime() - clock);
				RAM.returnProcess(process);
				return;
			}
			
			
		}
		// if the process not in the IOBoundJob Queue, then enter it to CPUBoundJob
		if(!IOBoundJob.isExist(process)){
			CPUBoundJob.enqueue(process, 0);
		}
		
		// the CPU finished executing the process
		Statistic.NUMBER_OF_EXECUTED_PROCESSES ++;
		process.setState("Terminated");
		file.writeProcess(process);
		RAM.loadProcessFromHardDisk();
	}
	
	public void MEC2(Process process) throws IOException {
		process.setState("Running");
		int clock = 0;
		
		//while loop to execute instructions
		while (true) {
			clock++;
			if(isTermenated()){
				// if the process terminates, then break the method
				process.setState("Terminated");
				if(!IOBoundJob.isExist(process))
					CPUBoundJob.enqueue(process, 0);
				Statistic.NUMBER_OF_EXECUTED_PROCESSES ++;
				file.writeProcess(process);
				RAM.loadProcessFromHardDisk();
				return;
			}
			if (isInterrupted(process)) {
				// if the process interrupted, then return the process to the Ready Queue (RAM)
				process.setState("Ready");
				process.setRemainingTime(process.getRemainingTime() - clock);
				RAM.returnProcess(process);
				return;
			}
			
			
		}
	}
	
	

	/**
	 * this method must be called after finishing from MEC method, otherwise the
	 * processes won't be saved in the file
	 * 
	 * @throws IOException
	 */
	public void finish() throws IOException {
		file.closeWriter();
	}

	private boolean isInterrupted(Process process) {
		
		int num = ran.nextInt(99);

		if (num < 20) {
			int interrupt = ran.nextInt(99);

			if (interrupt >= 0 && interrupt < 20) {
				// I/O request
				IOBoundJob.enqueue(process, 0);

			} else if (interrupt >= 20 && interrupt < 40) {
				// the busy IO device will terminate

			} else 
			
			return true;
		}
		return false;
	}
	
	private boolean isTermenated(){
		int terminated = ran.nextInt(99);
		if (terminated >= 0 && terminated < 5) {
			// the program terminates normally
			Statistic.NORMALLY++;
			return true;
			
		} else if (terminated == 6) {
			// the program terminates abnormally
			Statistic.ABNORMALLY++;
			return true;
		}
		return false;
	}
	
	public int getNumberOfCBUBoundJobs(){
		return CPUBoundJob.length();
	}

}
