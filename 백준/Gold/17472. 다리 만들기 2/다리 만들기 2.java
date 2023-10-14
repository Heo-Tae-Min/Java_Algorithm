import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, num = 1;
	static int[] parent;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map, island, adj;
	static PriorityQueue<Edge> edges;
	
	static class Edge {
		public int start;
		public int end;
		public int w;
		
		public Edge(int start, int end, int w) {
			super();
			this.start = start;
			this.end = end;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", w=" + w + "]";
		}
	}
	
	static void bfs(int r, int c, int num) {
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] {r, c});
		island[r][c] = num;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr<N && nr>=0 && nc<M && nc>=0 && map[nr][nc]==1 && island[nr][nc] == 0) {
					island[nr][nc] = num;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
	}
	static void classify() {
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(map[i][j] == 1 && island[i][j] == 0)
					bfs(i, j, num++);
		
		num -= 1;
	}
	
	static void makeSet() {
		parent = new int[num+1];
		for(int i=1; i<=num; i++) parent[i] = i;
	}
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	static boolean union(int a, int b) {
		int a_root = find(a);
		int b_root = find(b);
		
		if(a_root == b_root) return false;
		
		parent[b_root] = a_root;
		return true;
	}
	
	static void makeBridge(int r, int c) {
		outer:
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			int length = 0;
			while(true) {
				if(nr>=N || nr<0 || nc>=M || nc<0) continue outer;
				if(island[nr][nc] != 0) break;
				
				nr += dr[i];
				nc += dc[i];
				length++;
			}
			
			if(length < 2) continue;
			
			int start = island[r][c];
			int end = island[nr][nc];
			if(adj[start][end] == 0 || length < adj[start][end]) {
				adj[start][end] = length;
				adj[end][start] = length;
			}
		}
	}
	
	static void allBridge() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(island[i][j] != 0) {
					makeBridge(i, j);
				}
			}
		}
		
		for(int i=1; i<=num; i++) {
			for(int j=1; j<=num; j++) {
				if(adj[i][j] != 0) {
					edges.offer(new Edge(i, j, adj[i][j]));
				}
			}
		}
	}
	
	static int Kruskal() {
		classify();
		makeSet();
		adj = new int[num+1][num+1];
		edges = new PriorityQueue<>((a,b)->{return a.w - b.w;});
		
		allBridge();
		
		int cnt = 0, total = 0;
		while(cnt < num-1) {
			if(edges.isEmpty()) break;
			Edge e = edges.poll();
			if(union(e.start, e.end)) {
				cnt++;
				total += e.w;
			}
		}
		
		int temp = find(1);
		for(int i=2; i<=num; i++) {
			if(find(i) != temp) return -1;
		}
		
		return total;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		island = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(Kruskal());
	}

}