import java.util.*;
import java.io.*;

public class bj16287 {
	static int N,W;
	static int[] A;
	static boolean Exists() {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 2; i<N; i++) {
			for(int j = 0; j<i-1; j++) {
				set.add(A[i-1]+A[j]);
			}
			for(int j = i+1; j<N; j++) {
				if(set.contains(W-A[i]-A[j])==true) 
					return true;
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) 
			A[i] = Integer.parseInt(st.nextToken());
		
		if(Exists() == true) System.out.println("YES");
		else System.out.println("NO");
	}
}