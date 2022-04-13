import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class bj11438{
    static int N, Q, K;
    static int[] depth; 
    static int[][] P; 
    static ArrayList<ArrayList<Integer>> G; 
    static void fillParents() {
    	   for (int i = 1; i < K; i++) {
    		for (int j = 1; j <= N; j++) 
    		    P[j][i] = P[P[j][i - 1]][i - 1];
    	   }
    	   /*
    	   for(int i = 1; i<N+1; i++) {
    		   for(int j = 0; j<K; j++) {
    			   System.out.println(P[i][j]+ " ");
    		   }
    		   System.out.println();
    	   }
    	   */
    }
   static void dfs(int n, int cnt) {
    		   depth[n] = cnt;
    		 
    		   for (int e : G.get(n)) {
    			   if (depth[e] == 0) {	//초기
    				   dfs(e, cnt + 1);
    				   P[e][0] = n; 
    			   }
    		   }
    		} 
    static int lca(int a, int b) {
    		   if (depth[a] < depth[b]) {
    			int temp = a;  a = b; b = temp;
    		   }       
    		   int diff = depth[a] -depth[b], step = 0;
    			while (diff > 0) {
    		           int t = diff %2;
    		           if (t == 1) a = P[a][step];
    		           step = step +1;
    		           diff = diff /2;
    		        }
    		   if (a == b) return a;
    		   for (int i = K - 1; i >= 0; i--) 
    			   if (P[a][i] != P[b][i]) {
    				   a = P[a][i]; b = P[b][i];
    			}
    		   return P[a][0];
    		}
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        N = Integer.parseInt(br.readLine());
        G = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N + 1; i++) 
            G.add(new ArrayList<Integer>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            G.get(a).add(b);
            G.get(b).add(a);
        }
        K = (int) Math.ceil(Math.log(N+1)/Math.log(2));
        depth = new int[N + 1]; 
        P = new int[N + 1][K]; 
         
        dfs(1, 1);		
        fillParents();		
         
        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
         
            int ll = lca(a, b);		
            sb.append(ll + "\n");
        }
        System.out.println(sb.toString());
	}
}