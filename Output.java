

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class WriteToFileExample2 {

	private static final String FILENAME = "C:\\Users\\Bea Mariano\\Desktop\\Codes\\FIRE EXIT MP\\Output.txt";

	public static void printToFile(String content) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
			bw.write(content);
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}