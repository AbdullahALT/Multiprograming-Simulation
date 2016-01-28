import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Files {
	private String path;
	private BufferedWriter bufferWriter;
	private BufferedReader bufferReader;

	public Files(String location) throws IOException {
		path = location;
		bufferReader = new BufferedReader(new FileReader(path));
	}

	public String[] readFile() throws IOException {
		try {

			int numberOfLines = getNumberOfLines();

			String[] textData = new String[numberOfLines];

			for (int i = 0; i < numberOfLines; i++) {
				textData[i] = bufferReader.readLine();

			}

			bufferReader.close();
			return textData;
		} catch (FileNotFoundException e) {
			throw new IOException();
		}

	}

	private int getNumberOfLines() throws IOException {
		FileReader f = new FileReader(path);
		BufferedReader b = new BufferedReader(f);
		int nblines = 0;
		while ((b.readLine()) != null) {
			nblines++;
		}
		b.close();

		return nblines;
	}

	public void openStreamWriter() throws IOException {
		bufferWriter = new BufferedWriter(new FileWriter(path));
	}

	public void writeProcess(Process process) throws IOException {
		bufferWriter.write("{" + process.getId() + " ," + process.getSize()
				+ " ," + process.getState() + " ," + process.getExecutionTime()
				+ "}");
		bufferWriter.newLine();
	}

	public PriorityQueue<Process> readProcess() throws IOException {
		String[] file = readFile();
		PriorityQueue<Process> programs = new PriorityQueue<Process>();

		for (int i = 0; i < file.length; i++) {
			String supstring = file[i].substring(1, file[i].length() - 1);
			String value[] = supstring.split(" ,");
			Process process = new Process(Integer.parseInt(value[0]),
					Integer.parseInt(value[1]), value[2],
					Integer.parseInt(value[3]));

			programs.enqueue(process, process.getSize());
		}

		return programs;
	}

	public void closeWriter() throws IOException {
		bufferWriter.close();
	}

}
