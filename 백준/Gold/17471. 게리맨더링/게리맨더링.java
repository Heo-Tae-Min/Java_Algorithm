import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] parents, population;
	static List<Integer> selected;
	static List<Integer>[] adjList;
	static int N, min_diff = Integer.MAX_VALUE, total = 0;
	
	static void makeSet() {
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	static void select(int cnt, int sum) {
		if(cnt == N+1) {
			int size = selected.size();
			if(size == 0 || size == N) return;

			List<Integer> unselected = new ArrayList<>();
			for(int i=1; i<=N; i++) {
				if(!selected.contains(i)) unselected.add(i);
			}
			
			for(int i=1; i<size; i++) {
				union(selected.get(i-1), selected.get(i));
			}

			for(int i=1; i<N-size; i++) {
				union(unselected.get(i-1), unselected.get(i));
			}
			
			if(isConnected(selected.get(0), size) && isConnected(unselected.get(0), N-size)) {
				min_diff = Math.min(min_diff, Math.abs(total - sum*2));
			}
			
			makeSet();
			
			return;
		}
		
		// 현재 요소 포함
		selected.add(cnt);
		select(cnt+1, sum + population[cnt]);
		
		// 현재 요소 미포함
		selected.remove(selected.size()-1);
		select(cnt+1, sum);
	}
	
	static boolean isConnected(int start, int size) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		int cnt = 0;
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			cnt++;
			for(int next : adjList[now]) {
				if(!visited[next] && findSet(now) == findSet(next)) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
		
		if(cnt == size) return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); new StringBuilder();
		StringTokenizer st;
		StringTokenizer st2;
		
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		selected = new ArrayList<>();
		adjList = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			total += population[i];
			
			st2 = new StringTokenizer(br.readLine(), " ");
			int neighbor_num = Integer.parseInt(st2.nextToken());
			
			for(int j=0; j< neighbor_num; j++) {
				int temp = Integer.parseInt(st2.nextToken());
				adjList[i].add(temp);
			}
		}
		
		parents = new int[N+1];
		makeSet();
		select(1, 0);
		
		if(min_diff == Integer.MAX_VALUE) min_diff = -1;
		System.out.println(min_diff);
	}

}