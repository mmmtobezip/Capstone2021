import java.util.*;
import java.io.*;

public class bj6086{
	   static ArrayList<HashMap<Integer, Integer>> G;
	   static int T, S=0, D;
	   static int M, N;
	   static int[] from;
	   static void AddEdge(int f, int t, int w) {
		   int val = G.get(f).getOrDefault(t, 0);
		   G.get(f).put(t,  val+w);

		   val = G.get(t).getOrDefault(f, 0);
		   G.get(t).put(f,  val+w);
	   }
	   static int GetNode(String s) {
		   if (s.charAt(0) < 'a')
			return ((int) (s.charAt(0) -'A'));
		   else 
			return ((int) (s.charAt(0) -'a')) + 26;
		}
	   static boolean bfs() {
		   boolean[] V = new boolean[T];
		   for (int i=0; i<T; i++) 
		   		V[i] = false;
		   int[] que = new int[T];
		   int front =0, rear = 0; from[S] = -1;
		   que[rear++] = S; V[S]=true; 
		   while (front != rear) {
			   int u = que[front++];
			   for(Integer k : G.get(u).keySet()){
				   if (V[k] == true || G.get(u).get(k) == 0) 
		            	continue;
				   que[rear++] = k; 
				   V[k]= true; from[k] = u; 
				   if (k == D) return (true); 
			   }
		   }
		   return (false);
		}   
	   public static void main(String[] args) {
		   int n, f, min= 10000000;
		   int sum =0;
		   Scanner sc = new Scanner(System.in);
		   N = sc.nextInt(); 
		   T = 52; S = 0; D = 25;

		   G = new ArrayList<HashMap<Integer, Integer>>();
		   for (int i=0;i<T; i++)
		      G.add(new HashMap<Integer, Integer>());
		   from  = new int[T];

		   for(int i = 0; i<N; i++) {
			   int d1 = GetNode(sc.next());
			   int d2 = GetNode(sc.next());
			   int d3 = sc.nextInt();
			   AddEdge(d1,d2,d3);
		   }
		   sc.close();
		   while (bfs() == true) { 
			   n = D; f = from[D]; 
		       while (n !=S) {	 
		    	   min = Math.min(min, G.get(f).get(n));
		    	   n= f; f = from[n]; 
		      }
		      n = D; f = from[D];
		      while (n != S) {
		    	  G.get(f).put(n, G.get(f).get(n)-min);
		    	  G.get(n).put(f, G.get(n).get(f)+min);
		    	  n= f; f = from[n]; 
		      }
		      sum += min;
		   } 
		   System.out.println(sum);
	  }
}