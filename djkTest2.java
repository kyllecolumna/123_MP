//https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/

import java.io.*;
import java.util.*;

public class djkTest2 {

	private static final String FILENAME = "C:\\Users\\kyllecolumna\\Desktop\\MP.txt";
	
	public void readFile() {

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));
			
			
			int level = 0;
			
			while ((sCurrentLine = br.readLine()) != null) {
					
				Scanner s = new Scanner(sCurrentLine);
				
				System.out.println("Level " + level);
				level++;
				
				int roomCount = s.nextInt();
				System.out.println("roomCount = " + roomCount);
				
				int pathCount = s.nextInt();
				System.out.println("pathCount = " + pathCount);
				
				sCurrentLine = br.readLine();
				
				for (int i = 0; i < pathCount; i++) {
					int start = s.nextInt();
					System.out.println("start = " + start);
					int end = s.nextInt();
					System.out.println("end = " + end);
					int cost = s.nextInt();
					System.out.println("cost = " + cost);
					
					sCurrentLine = br.readLine();
				}
				
				
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
	public static void main(String[] args) {

		// call ReadFile method
		djkTest2 rf = new djkTest2();
		rf.readFile();
		
		
	}

}

