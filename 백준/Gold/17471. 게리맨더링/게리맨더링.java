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
			// 선택된 구역의 수
			int size = selected.size();
			// 선택된 지역구가 없는 경우, 모두 선택된 경우는 다루지 않음
			if(size == N) return;

			// 선택되지 않은 지역 리스트 생성
			List<Integer> unselected = new ArrayList<>();
			for(int i=1; i<=N; i++) {
				if(!selected.contains(i)) unselected.add(i);
			}
			
			// 선택된 구역끼리 union
			for(int i=1; i<size; i++) {
				union(selected.get(i-1), selected.get(i));
			}

			// 선택되지 않은 구역끼리 union
			for(int i=1; i<N-size; i++) {
				union(unselected.get(i-1), unselected.get(i));
			}
			
			// 두 선거구 모두 끊겨 있지 않은 경우
			if(isConnected(selected.get(0), size) && isConnected(unselected.get(0), N-size)) {
				// 선거구끼리의 인구수 차이를 계산 & 갱신
				min_diff = Math.min(min_diff, Math.abs(total - sum*2));
			}
			
			// parent 배열 초기화
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
				// 연결된 정점끼리 같은 선거구일 때만 queue에 삽입 => 다른 선거구라면 끊어진 걸로 봄
				if(!visited[next] && findSet(now) == findSet(next)) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
		
		// 연결된 같은 집합 내의 지역 수가 size와 같다면 true, 아니면 false
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
		
		// 인구수, 인접 관계 입력
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
		// 1이 선택된 경우가 나머지 경우의 반대이기 때문에
		// 겹치는 작업을 제거하기 위해 1을 삽입 & 나머지만 부분집합 처리
		selected.add(1);
		select(2, population[1]);
		
		// 최소 인구수 차이가 초기화 상태 그대로라면 => 가능한 경우가 없는 것이기 때문에 -1로 대체
		if(min_diff == Integer.MAX_VALUE) min_diff = -1;
		
		System.out.println(min_diff);
	}

}