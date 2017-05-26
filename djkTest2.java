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
			
			

			while ((sCurrentLine = br.readLine()) != null) {
				
				int level = 0;
				Scanner s = new Scanner(sCurrentLine);
				
				int roomCount = s.nextInt();
				System.out.println("roomCount = " + roomCount);
				
				int pathCount = s.nextInt();
				System.out.println("pathCount = " + pathCount);
				
				//int i;
				
				//sCurrentLine = br.readLine();
				int start = s.nextInt();
				System.out.println("start = " + start);
				int end = s.nextInt();
				System.out.println("end = " + end);
				int cost = s.nextInt();
				System.out.println("cost = " + cost);
				
				/*for (i = 0; i < pathCount; i++) {
					 start = s.nextInt();
					System.out.println("start = " + start);
					 end = s.nextInt();
					System.out.println("end = " + end);
					 cost = s.nextInt();
					System.out.println("cost = " + cost);
					
					sCurrentLine = br.readLine();
				}*/
				
				
				//Scanner s = new Scanner(sCurrentLine);
				
				//while (s.hasNext()) {
					//  System.out.println(s.nextInt());
				//}
				
				
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

