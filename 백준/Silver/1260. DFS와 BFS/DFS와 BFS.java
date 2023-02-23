import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static List<Integer>[] graph;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken()) - 1;
		
		graph = new ArrayList[V];
		for(int i=0; i<V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			
			graph[s].add(e);
			graph[e].add(s);
		}
		
		for(int i=0; i<V; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(start);
		bfs(start);
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}
	
	static void dfs(int start) {
		boolean[] visited = new boolean[V];
		visited[start] = true;
		dfs_recur(start, 0, visited);
		sb.append("\n");
	}
	
	static void dfs_recur(int current, int cnt, boolean[] visited) {
		if(cnt == V) {
			sb.append("\n");
			return;
		};
		
		sb.append(current+1).append(" ");
		
		for(int vertex : graph[current]) {
			if(!visited[vertex]) {
				visited[vertex] = true;
				dfs_recur(vertex, cnt+1, visited);
			}
		}
	}

	static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[V];
		
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current+1).append(" ");
			for(int vertex : graph[current]) {
				if(!visited[vertex]) {
					visited[vertex] = true;
					queue.offer(vertex);
				}
			}
		}
		sb.append("\n");
	}
}