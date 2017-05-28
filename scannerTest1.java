/* 
 * SOURCES
 * Dijkstra's Algorithm: https://rosettacode.org/wiki/Dijkstra%27s_algorithm
 * Reading files: https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
 * Outputting to files: 
 **/

import java.io.*;
import java.util.*;

public class scannerTest1 {
	public static void main(String[] args) {
		
		// Store the na,e of the input file to fileName string
		String fileName = "C:\\Users\\Bea Mariano\\Desktop\\Codes\\FIRE EXIT MP\\123_MP-master\\MP.txt";

		// These are the references for each line read in the file
		String line = null;
		String line1 = null;

		try {
			// FileReader reads text files
			FileReader fileReader = new FileReader(fileName);

			// Wrap the FileReader in BufferedReader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//Scanner input = new Scanner(fileName);
			int floorCount = 0;
			int arrayDumpCount = 0;
			
			// As we traverse to the file, we count the number of occurrences of the two types of input
            		// Two types of input: room-path input and edge input
			while((line = bufferedReader.readLine()) != null) {
				int count = 0;

				// The number of Whitespaces between the characters in the line indicate the number of digits int the line
				for (int i = 0, len = line.length(); i < len; i++) {
					if (Character.isWhitespace(line.charAt(i))) {
						count++;
					}
				}

				if (count == 1) {
					// Increment floorCount if it is a room-path input
					floorCount++;
				} else if (count == 2) {
					// Increment arrayDumpCount if it is an edge input
					arrayDumpCount++;;
				}

			}

			// Close the file
			bufferedReader.close();
			
			/*
            		 * For checking of the counts: 
            		 * System.out.println("floorCount: " + floorCount);
            		 * System.out.println("arrayDumpCount: " + arrayDumpCount);
            		 **/

			// Initialize counters
			int count1 = 0;
			int count2 = 0;

			// Initialize arrays for two types of input, room-path input and edge input 
			String[] twoDigitArray = new String[floorCount];
			String[] threeDigitArray = new String[arrayDumpCount];

			// Open the file again to extract the lines
			String fName = "C:\\Users\\kyllecolumna\\Desktop\\MP.txt";
			FileReader fReader = new FileReader(fName);
			// Again wrap it in BufferedReader br
			BufferedReader br = new BufferedReader(fReader);

			// Traverse again through the file
			while ((line1 = br.readLine()) != null) {
				int count = 0;

				for (int i = 0, len = line1.length(); i < len; i++) {
					if (Character.isWhitespace(line1.charAt(i))) {
						count++;
					}
				}

				if (count == 1) {
					// If it is a room-path input, store it in the twoDigitArray
					twoDigitArray[count1] = line1;
					count1++;
				} else if (count == 2) {
					// If it is an edge input, store it in the threeDigitArray
					threeDigitArray[count2] = line1;
					count2++;
				}
			}

			// Close the file again
			br.close();

			/*
			 * As we separate the room-path input, we store the number of rooms in a floor 
            		 * in an element int the roomCount array with the floorCount as it's indices
           	 	 * the same argument applies with pathCount array
			 **/
			int[] roomCount = new int[floorCount]; 
			int[] pathCount = new int[floorCount];

			// Store each info in their respective array 
			for (int i = 0; i < floorCount; i++) {
				Scanner input = new Scanner(twoDigitArray[i]);
				roomCount[i] = input.nextInt();
				pathCount[i] = input.nextInt();
				System.out.println(twoDigitArray[i]);
			}

			Graph.Edge[] edgeGroup;
			edgeGroup = new Graph.Edge[arrayDumpCount];

			String START, END;

			List<Integer> x = new ArrayList<Integer>();    	
			Graph.Edge[] GRAPH;

			// As long as there is and element in the threeDigitArray, get the info
			for (int j = 0; j < arrayDumpCount; j++) {
				Scanner input2 = new Scanner(threeDigitArray[j]);
				// Convert the first int which is the first vertex to string 
				String temp1 = String.valueOf(input2.nextInt());
				// Convert the next vertex to string
				String temp2 = String.valueOf(input2.nextInt());
				
				// ERROR: The method Edge(String, String, int) is undefined for the type Graph
				// We create a new edge by inserting, the start vertex, the end vertex, and the cost to the edge method
				GRAPH.add(Graph.Edge(temp1, temp2, input2.nextInt()));
			}

			for (int y = 0; y < floorCount; y++) {
				for (int k = 0; k < (roomCount[y]); k++) {
					String temp = String.valueOf(k);
					START = temp;
					String temp1 = String.valueOf(roomCount[y] - 1);
					END = temp1;
					Graph g = new Graph(GRAPH);
					g.dijkstra(START);
					g.printPath(END);
				}
			}

		} catch(FileNotFoundException ex) {
			// If file cannot be found in location
			System.out.println("Unable to open file '" + fileName + "'");
		} catch(IOException ex) {
			// If an input or output operation is failed or interpreted
			System.out.println("Error reading file '" + fileName + "'");
		}
	}
}


class Graph {
	
	// Map vertex names to vertex objects
	private final Map<String, Vertex> graph;
		 
	// Create class for a graph edge (used by Graph constructor)
	public static class Edge {
		public final String v1, v2;
		public final int dist;
		public Edge(String v1, String v2, int dist) {
			this.v1 = v1;
		        this.v2 = v2;
		        this.dist = dist;
		}
	}

	// Create class for a graph vertex
	public static class Vertex implements Comparable<Vertex>{
		public final String name;
		
		// Assume MAX_VALUE is infinity
		public int dist = Integer.MAX_VALUE;
		public Vertex previous = null;
		public final Map<Vertex, Integer> neighbours = new HashMap<>();
		 
		// Reference name using this keyword
		public Vertex(String name) {
			this.name = name;
		}
		 
		// Create a method to output each succeesing vertex in each shortest path
		private void printPath() {
			if (this == this.previous) {
				System.out.printf("%s", this.name);
			} else if (this.previous == null) {
				System.out.printf("%s(unreached)", this.name);
			} else {
				this.previous.printPath();
				System.out.printf(" -> %s(%d)", this.name, this.dist);
			}
			
		}
		
		// Create method to compare edge costs
		public int compareTo(Vertex other) {
			if (dist == other.dist) {
				return name.compareTo(other.name);
			}
				
			return Integer.compare(dist, other.dist);
		}
		 
		@Override public String toString() {
			return "(" + name + ", " + dist + ")";
		}
	}
		 
	// Create method to generate graphs from a set of edges
	public Graph(Edge[] edges) {
		graph = new HashMap<>(edges.length);
		 
		// Find all vertices
		for (Edge e : edges) {
			if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
		        if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
		}
		 
		// Set all neighboring vertices of each vertex
		for (Edge e : edges) {
		        graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
		}
	}
		 
	// Create method for the Dijkstra's Algorithm proper (starting from the START vertex)
	public void dijkstra(String startName) {
		
		if (!graph.containsKey(startName)) {
			System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
		        return;
		}
		      
		final Vertex source = graph.get(startName);
		NavigableSet<Vertex> q = new TreeSet<>();
		 
		// set-up vertices
		for (Vertex v : graph.values()) {
			v.previous = v == source ? source : null;
		        v.dist = v == source ? 0 : Integer.MAX_VALUE;
		        q.add(v);
		}
		 
		dijkstra(q);
	}
		 
	private void dijkstra(final NavigableSet<Vertex> q) {      
		Vertex u, v;
		     
		while (!q.isEmpty()) {
		 
			u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
		        if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
		 
		        //look at distances to each neighbour
		        for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
		        	v = a.getKey(); //the neighbour in this iteration
		 
		            	final int alternateDist = u.dist + a.getValue();
		            
				if (alternateDist < v.dist) { // shorter path to neighbour found
		               		q.remove(v);
		               		v.dist = alternateDist;
		               		v.previous = u;
		               		q.add(v);
		            	} 
		         }
		}
	}
		 
	/** Prints a path from the source to the specified vertex */
	public void printPath(String endName) {
		if (!graph.containsKey(endName)) {
			System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
		     	return;
		}
		 
		graph.get(endName).printPath();
		System.out.println();
	}
		   
}
		
