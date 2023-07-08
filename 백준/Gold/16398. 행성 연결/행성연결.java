/*

	MST 문제

	weight가 Matrix 형태로 주어졌기 때문에 Vertex 중심 방식인 PRIM 알고리즘을 사용하는 것이 보통이지만
	간선 List로 바꾸어 KRUSKAL로 풀었음

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_16398_행성연결 {
	static int N;
	static int[] parent;
	
	static void init() {
		parent = new int[N+1];
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parent[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{return a[2]-b[2];});
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// pq에 중복된 간선을 넣지 않도록 빼는 코드
			for(int j=0; j<i; j++) {
				st.nextToken();
			}
			
			// Kruskal로 풀기 위해 간선리스트로 바꿈
			for(int j=i; j<N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if(i == j) continue;
				pq.offer(new int[] {i, j, cost});
			}
		}
		
		init();
		
		int edge = 0;
		long sum = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			if(union(now[0], now[1])) {
				edge++;
				sum += now[2];
			}
			if(edge == N-1) break;
		}
		
		System.out.println(sum);
	}

}
