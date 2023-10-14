import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		int tLen = text.length;
		int pLen = pattern.length;
		
		// 패턴에서 접두사-접미사 일치 길이를 담을 배열
		// 처음은 0으로 초기화되어 있음
		int[] fail = new int[pLen];
		for(int i=1, j=0; i<pLen; i++) {
			while(j>0 && pattern[i] != pattern[j]) {
				j = fail[j-1];
			}
			if(pattern[i] == pattern[j]) {
				fail[i] = ++j;
			}
		}
		
		int j = 0;
		int cnt = 0;
		List<Integer> index = new ArrayList<>();
		for(int i=0; i<tLen; i++) {
			
			while(j>0 && text[i] != pattern[j]) j = fail[j-1];
			
			if(text[i] == pattern[j]) {
				if(j==pLen-1) {
					cnt++;
					index.add(i - (pLen - 1) + 1);
					j = fail[j];
				} else {
					j++;
				}
			}
		}
		
		sb.append(cnt).append("\n");
		for(int i : index) sb.append(i).append(" ");
		sb.append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close(); br.close();
	}

}