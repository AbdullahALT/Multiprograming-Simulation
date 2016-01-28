import java.io.IOException;
import java.util.Random;

public class Process {
	private int id;
	private int size;
	private String state;
	private int executionTime;
	private int remainingTime;
	
	public Process(int id, int size, String state, int executionTime) {
		this.id = id;
		this.size = size;
		this.state = state;
		this.remainingTime = this.executionTime = executionTime;
	}
	
	public static void generateProcess(HardDisk hardDisk, Files file) throws IOException {
		file.openStreamWriter();
		Random ran = new Random();
		int id = 1;
		while ( true ){
			Process program = new Process(
					id++,
					ran.nextInt(16368) + 16,
					"New",
					ran.nextInt(496) + 16
					);
			if( program.getSize() > hardDisk.getFreesize() ) 
				break;
			hardDisk.returnProgram(program);
			file.writeProcess(program);
			
		}
		hardDisk.update();
		file.closeWriter();		
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public int getSize() {
		return size;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	
	
	
	
	
}
