import java.io.*;
import java.util.*;
public class scannerTest1 {
	public static void main(String[] args) {

        // The name of the file to open.
        String fileName = "C:\\Users\\Bea Mariano\\Desktop\\Codes\\FIRE EXIT MP\\123_MP-master\\MP.txt";

        // This will reference one line at a time
        String line = null;
        String line1 = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Scanner input = new Scanner(fileName);
            int floorCount = 0;
            int arrayDumpCount = 0;

            while((line = bufferedReader.readLine()) != null) {
            	//System.out.println(line);
            	//Scanner input = new Scanner(line);
                //System.out.println(line);
                int count = 0;
                for (int i = 0, len = line.length(); i < len; i++) {
                    if (Character.isWhitespace(line.charAt(i))) {
                        count++;
                    }
                }
                
                if (count == 1) {
                  //System.out.println("2 digits: " + line);
                  //twoDigitArray[floorCount - 1] = line;
                	floorCount++;
                } else if (count == 2) {
                  //System.out.println("3 digits: " + line);
                  //threeDigitArray[arrayDumpCount - 1] = line;
                	arrayDumpCount++;;
                }
            
            }
            
            // Always close files.
            bufferedReader.close();
            
            System.out.println("floorCount: " + floorCount);
            System.out.println("arrayDumpCount: " + arrayDumpCount);
            
            int count1 = 0;
            int count2 = 0;
            
            String[] twoDigitArray = new String[floorCount];
            String[] threeDigitArray = new String[arrayDumpCount];
            
            String fName = "C:\\Users\\Bea Mariano\\Desktop\\Codes\\FIRE EXIT MP\\123_MP-master\\MP.txt";
            FileReader fReader = new FileReader(fName);
            BufferedReader br = new BufferedReader(fReader);
            while ((line1 = br.readLine()) != null) {
            	int count = 0;
                for (int i = 0, len = line1.length(); i < len; i++) {
                    if (Character.isWhitespace(line1.charAt(i))) {
                        count++;
                    }
                }
                
                if (count == 1) {
                	twoDigitArray[count1] = line1;
                	count1++;
                } else if (count == 2) {
                	threeDigitArray[count2] = line1;
                	count2++;
                }
            }
            
            br.close();
            
            int[] roomCount = new int[floorCount]; 
            int[] pathCount = new int[floorCount];
            
            for (int i = 0; i < floorCount; i++) {
            	Scanner input = new Scanner(twoDigitArray[i]);
            	roomCount[i] = input.nextInt();
            	pathCount[i] = input.nextInt();
            	System.out.println(twoDigitArray[i]);
            	//System.out.println(input.nextInt());
            	//System.out.println(input.nextInt());
            }
            
            edge[] edgeGroup = new edge[arrayDumpCount];
           
            	for (int j = 0; j < arrayDumpCount; j++) {
            		Scanner input2 = new Scanner(threeDigitArray[j]);
            		edgeGroup[j].start = input2.nextInt();
            		edgeGroup[j].end  = input2.nextInt();
            		edgeGroup[j].cost = input2.nextInt();
            		System.out.println(threeDigitArray[j]);
			input2.close();
            	}	        
            	
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
}
