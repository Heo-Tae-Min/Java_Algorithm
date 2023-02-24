import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int L, vowel_limit;
	static List<Character> vowel, conso;
	static Set<String> result;
	static StringBuilder sb;
	
	static void vowel_select(int start, int cnt, int target) {
		if(cnt == target) {
			conso_select(0, 0, L-target);
			return;
		}
		
		for(int i=start; i<vowel.size(); i++) {
			sb.append(vowel.get(i));
			vowel_select(i+1, cnt+1, target);
			sb.setLength(sb.length()-1);
		}
	}
	
	static void conso_select(int start, int cnt, int target) {
		if(cnt == target) {
			String temp = sb.toString();
			char[] selected = temp.toCharArray();
			Arrays.sort(selected);
			result.add(String.valueOf(selected));
		}
		
		for(int i=start; i<conso.size(); i++) {
			sb.append(conso.get(i));
			conso_select(i+1, cnt+1, target);
			sb.setLength(sb.length()-1);
		}
	}
	
	static void select() {
		for(int i=1; i<=vowel_limit; i++) {
			if(i+conso.size() < L) continue;
			vowel_select(0, 0, i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		vowel = new ArrayList<Character>();
		conso = new ArrayList<Character>();
		result = new TreeSet<>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<C; i++) {
			char temp = st.nextToken().charAt(0);
			if(temp == 'a' || temp == 'e' || temp == 'i' || temp == 'o' || temp == 'u')
				vowel.add(temp);
			else
				conso.add(temp);
		}
			
		vowel_limit = Math.min(vowel.size(), L-2);
		
		select();
		for(String pw : result) {
			sb.append(pw).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}