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
            
           edge[] edgeGroup;
           edgeGroup = new edge[arrayDumpCount];
           
           for (int k = 0; k < arrayDumpCount; k++) {
        	   edgeGroup[k] = new edge();
           }
           
           String START, END;
           
            	for (int j = 0; j < arrayDumpCount; j++) {
            		Scanner input2 = new Scanner(threeDigitArray[j]);
            		String temp1 = String.valueOf(input2.nextInt());
            		System.out.println("temp1: " + temp1);
            		edgeGroup[j].start = temp1;
            		String temp2 = String.valueOf(input2.nextInt());
            		System.out.println("temp2: " + temp2);
            		edgeGroup[j].end  = temp2;
            		edgeGroup[j].cost = input2.nextInt();
            		System.out.println(threeDigitArray[j]);
            	}
		
            	List<Integer> x = new ArrayList<Integer>();    	
            	Graph.Edge[] GRAPH = {};
            	
            	// ERROR: The method Edge(String, String, int) is undefined for the type Graph
            	for (int j = 0; j < arrayDumpCount; j++) {
            		GRAPH.add(Graph.Edge(edgeGroup[j].start, edgeGroup[j].end, edgeGroup[j].cost));
            	}
            	
            	END = /* fire exit AKA last room */;
            	
            	for (int k = 0; k < (numOfRooms - 1); k++) {
            		START = /* each other room */;
            		
            		Graph g = new Graph(GRAPH);
                    g.dijkstra(START);
                    g.printPath(END);
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


class Graph {
		   private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges
		 
		   /** One edge of the graph (only used by Graph constructor) */
		   public static class Edge {
		      public final String v1, v2;
		      public final int dist;
		      public Edge(String v1, String v2, int dist) {
		         this.v1 = v1;
		         this.v2 = v2;
		         this.dist = dist;
		      }
		   }
		 
		   /** One vertex of the graph, complete with mappings to neighbouring vertices */
		  public static class Vertex implements Comparable<Vertex>{
			public final String name;
			public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
			public Vertex previous = null;
			public final Map<Vertex, Integer> neighbours = new HashMap<>();
		 
			public Vertex(String name)
			{
				this.name = name;
			}
		 
			private void printPath()
			{
				if (this == this.previous)
				{
					System.out.printf("%s", this.name);
				}
				else if (this.previous == null)
				{
					System.out.printf("%s(unreached)", this.name);
				}
				else
				{
					this.previous.printPath();
					System.out.printf(" -> %s(%d)", this.name, this.dist);
				}
			}
		 
			public int compareTo(Vertex other)
			{
				if (dist == other.dist)
					return name.compareTo(other.name);
		 
				return Integer.compare(dist, other.dist);
			}
		 
			@Override public String toString()
			{
				return "(" + name + ", " + dist + ")";
			}
		}
		 
		   /** Builds a graph from a set of edges */
		   public Graph(Edge[] edges) {
		      graph = new HashMap<>(edges.length);
		 
		      //one pass to find all vertices
		      for (Edge e : edges) {
		         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
		         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
		      }
		 
		      //another pass to set neighbouring vertices
		      for (Edge e : edges) {
		         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
		         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
		      }
		   }
		 
		   /** Runs dijkstra using a specified source vertex */ 
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
		 
		   /** Implementation of dijkstra's algorithm using a binary heap. */
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
		
