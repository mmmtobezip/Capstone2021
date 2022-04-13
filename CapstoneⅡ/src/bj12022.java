import java.util.*;
import java.io.*;

class Boy {
	int[] pref; int pos;
	Boy(int[] p){
		pref = p;
		pos = 0;
	}
}
class Girl {
	int[] pref; int match;
	Girl(int[] p){
		pref = p; 
		match = -1; 
	}
}
public class bj12022 {
	static int N; 
	static Boy[] boys; static Girl[] girls;
	static int[] Fight(int c, Girl g) { //challenger & previously matched girl
		int tmp = g.match;
		int[] res = new int[2]; 
		for(int i = 0; i<N; i++) { 
			if(g.pref[i] == c) { //도전자의 우선순위가 높은 경우 도전자 승
				res[0] = c; res[1] = tmp; break;
			}
			else if(g.pref[i] == tmp) { //기존 매칭된 여자가 우선순위 높을 경우 기존 매칭자 승
				res[0] = tmp; res[1] = c; break;
			}
		}
		return res;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine()); 
		boys = new Boy[N]; girls = new Girl[N];
		for(int i = 0; i<N; i++) { //남자 -> 여자 선호도 
		StringTokenizer	st = new StringTokenizer(br.readLine());
			int[] pp = new int[N];
			for(int j = 0; j<N; j++)
				pp[j]  = Integer.parseInt(st.nextToken())-1; 
			boys[i] = new Boy(pp);
		}
		for(int i = 0; i<N; i++) { //여자 -> 남자 선호도 
			StringTokenizer	st = new StringTokenizer(br.readLine());
			int[] pp = new int[N]; 
			for(int j = 0; j<N; j++)
				pp[j] = Integer.parseInt(st.nextToken())-1; 
			girls[i] = new Girl(pp);
		}
		
		int chal, tar; 
		for(int i = 0; i<N; i++) {
			chal = i;
			while(true) {
				Boy t = boys[chal]; 
				tar = t.pref[t.pos]; 
				if(girls[tar].match == -1) { 
					t.pos++; 
					girls[tar].match = chal;
					break;
				}
				else { 
					int [] w = Fight(chal, girls[tar]); 
					girls[tar].match = w[0];
					chal = w[1];
					t.pos++;
				}
			}
		}
		int[] res = new int[N];
		for(int i = 0; i<N; i++) 
			res[girls[i].match] = i+1;
		for(int i = 0; i<N; i++) {
			sb.append(res[i] + "\n");
		}System.out.println(sb.toString());	
	}
}