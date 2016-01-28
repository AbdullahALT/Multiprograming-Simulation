import java.io.IOException;

public class testCPU {
	public static void main(String[] args) {
		try {

			// Initialization
			HardDisk hardDisk = new HardDisk();
			Files file = new Files("C:/Users/abo_tam/Desktop/processes.txt");
			hardDisk.loadFromFile(file);
			//Process.generateProcess(hardDisk, file);
			
			CPU cpu = new CPU();
			System.out.println("Number of programs: "
					+ hardDisk.getNumberOfPrograms());
			
			
			RAM.fillMemory(hardDisk);
			while (hardDisk.getNumberOfPrograms() != 0 || RAM.getNumberOfProcess() != 0) {
				
				cpu.MEC2(RAM.uploadProcess());
				
			}
			cpu.finish();
			
			
			System.out.println("NUMBER_OF_EXECUTED_PROCESSES: "
					+ Statistic.NUMBER_OF_EXECUTED_PROCESSES);
			System.out.println("AVARAGE_SIZE: "
					+ Statistic.AVARAGE_SIZE);
						System.out.println("ABNORMALLY: " + Statistic.ABNORMALLY);
			System.out.println("NORMALLY: " + Statistic.NORMALLY);
			System.out.println("CPU_BOUND_JOP: " + cpu.getNumberOfCBUBoundJobs());
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
