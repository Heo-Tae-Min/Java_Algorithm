import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken())-1;
			int[] visited = new int[100];
			
			// 인접 리스트 생성
			List<Integer>[] adjList = new List[100];
			for(int i=0; i<100; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			// 간선 정보 입력 (단방향)
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				adjList[from].add(to);
			}
			
			// BFS
			Queue<Integer> queue = new ArrayDeque<Integer>();
			
			queue.offer(start);
			visited[start] = 1;
			
			int cnt = 1;
			while(!queue.isEmpty()) {
				int now = queue.poll();
				
				for(int next : adjList[now]) {
					if(visited[next] != 0) continue;
					
					queue.offer(next);
					visited[next] = visited[now] + 1;
					cnt = visited[now] + 1;
				}
			}
			
			int last = 0;
			for(int i=0; i<100; i++) {
				if(visited[i] == cnt)
					last = i;
			}
			
			sb.append("#").append(tc).append(" ").append(last+1).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}
