import java.util.*;
import java.io.*;

class BiMatch{
	int N, M;
	int [] A, B;
	boolean V[];
	ArrayList<ArrayList<Integer>> G;
	BiMatch(int n, int m, ArrayList<ArrayList<Integer>> g) {
		N = n; M = m; G =g;
		A = new int[N]; 
		B = new int[M];
	}
	boolean dfs(int a) {
		if(V[a] == true) return false;
		V[a] = true;
		for(int b: G.get(a))
			if(B[b] == -1 || !V[B[b]] && dfs(B[b])) {
				A[a] = b;
				B[b] = a;
				return true;
			}
		return false;
	}
	int match() {
		Arrays.fill(A, -1);
		Arrays.fill(B, -1);
		
		int cnt = 0; 
		for(int i =0; i<N; i++) {
			if(A[i] == -1) {
				V = new boolean[N];
				if(dfs(i) == true)
					cnt++;
			}
		}
		return cnt;
	}
}
public class bj1298 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i<N; i++)
			G.add(new ArrayList<Integer>());
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			G.get(a).add(b);
		}
		BiMatch bm = new BiMatch(N,N,G);
		System.out.println(bm.match());
	}
}