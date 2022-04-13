import java.util.*;
import java.io.*;

public class bj22354{
	static int N;
	static int[] A;
	static ArrayList<Integer> L;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		int left=0;
		while (left <N && str.charAt(left)==str.charAt(0)) left++;
		int right =N-1;
		while (right >=0 && str.charAt(right)==str.charAt(N-1)) right--;

		L = new ArrayList<Integer>();
		while (left <= right) {
			int mm = A[left];
			int ll = left+1;
			while(ll <= right && str.charAt(ll)==str.charAt(left)) {
				mm = Math.max(mm, A[ll]);
				ll++;
			}
			L.add(-mm);
			left = ll;
		}
		Collections.sort(L);
		int ss = L.size()/2;
		if (L.size()%2 ==1) ss++;
		long sum =0;
		for (int i=0; i<ss; i++)
			sum -= 1L* L.get(i);
		System.out.println(sum);
	}
}