public class RAM {
	private static int freesize;
	private static HardDisk hardDisk;
	public static PriorityQueue<Process> processes; // CPU Scheduler - Ready
													// Queue
	public static void fillMemory(HardDisk _hardDisk) {
		// Initialize attributes
		hardDisk = _hardDisk;
		freesize = 160 * 1024;
		processes = new PriorityQueue<Process>();

		// fill the memory
		Process process = hardDisk.uploadProcess();

		while (process.getSize() <= freesize) {
			process.setState("Ready");
			processes.enqueue(process, process.getExecutionTime());
			freesize -= process.getSize();
			if (hardDisk.getNumberOfPrograms() == 0)
				break;
			process = hardDisk.uploadProcess();
		}
		if(process.getSize() > freesize)
			hardDisk.returnProgram(process);

	}

	public static void loadProcessFromHardDisk() {
		if (hardDisk.getNumberOfPrograms() != 0) {

			Process process = hardDisk.uploadProcess();
			if (process.getSize() <= freesize) {
				process.setState("Ready");
				processes.enqueue(process, process.getExecutionTime());
				freesize -= process.getSize();
			} else
				hardDisk.returnProgram(process);
		}
	}

	public static Process uploadProcess() {
		Process p = processes.serve();
		freesize += p.getSize();
		return p;
	}

	public static void returnProcess(Process process) {
		processes.enqueue(process, process.getRemainingTime());
		freesize -= process.getSize();
	}

	public static int getNumberOfProcess() {
		return processes.length();
	}
}
