import java.io.IOException;

public class HardDisk {
	private int freesize;
	private PriorityQueue<Process> programs; // Job Scheduler

	public HardDisk() throws IOException {
		freesize = 2097152; // 2GB in KB
		programs = new PriorityQueue<Process>();
	}

	public void returnProgram(Process program) {
		programs.enqueue(program, program.getSize());
		freesize -= program.getSize();
	}

	public void loadFromFile(Files file) throws IOException {
		programs =  file.readProcess();
		freesize -= programs.getTotalPriorities();
		update();
	}
	
	public void update(){
		Statistic.NUMBER_OF_JOPS = programs.length();
		Statistic.AVARAGE_SIZE = programs.getTotalPriorities() / programs.length();
	}

	public Process uploadProcess() {
		Process p = programs.serve();
		freesize += p.getSize();
		return p;
	}

	public int getFreesize() {
		return freesize;
	}

	public int getNumberOfPrograms() {
		return programs.length();
	}

	

}
