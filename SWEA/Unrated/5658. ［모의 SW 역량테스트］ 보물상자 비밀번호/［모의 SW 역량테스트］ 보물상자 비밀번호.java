import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int length = N/4;
			
			List<Character> list = new LinkedList<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			
			String input = br.readLine(); 
			for(int i=0; i<N; i++) {
				list.add(input.charAt(i));
			}
			
			for(int i=0; i<length; i++) {
				for(int j=0; j+length<=N; j += length) {
					for(char data : list.subList(j, j+length)) {
						sb.append(data);
					}
					pq.offer(Integer.parseInt(sb.toString(), 16));
					sb.setLength(0);
				}
				list.add(0, list.get(N-1));
				list.remove(N);
			}
			
			int result = 0;
			int prev = 0;
			while(K > 0) {
				result = pq.poll();
				if(prev == result) continue;
				
				prev = result;
				K--;
			}
			sb2.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		bw.write(sb2.toString());
		bw.flush(); bw.close(); br.close();
	}

}