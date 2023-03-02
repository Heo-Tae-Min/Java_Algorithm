import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine()) - 1;
		int[] distance = new int[V];
		boolean[] visited = new boolean[V];
		List<int[]>[] adjList = new List[V];
		
		for(int i=0; i<V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			adjList[s].add(new int[] {e, w});
		}
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		distance[start] = 0;
		
		for(int i=0; i<V; i++) {
			int min = INF, cur = -1;
			for(int j=0; j<V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					cur = j;
				}
			}
			
			if(cur == -1) break;
			visited[cur] = true;		
			
			for(int[] temp : adjList[cur]) {
				if(!visited[temp[0]] && distance[temp[0]] > min + temp[1])
					distance[temp[0]] = min + temp[1];
			}
		}
		
		for(int i=0; i<V; i++) {
			String result = distance[i] == INF ? "INF" : String.valueOf(distance[i]);
			sb.append(result).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}