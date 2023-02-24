import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<Integer>[] list;
	static boolean[] visited;
	
	public static int dfs(int current, int cnt) {
		
		if(cnt == 5)
			return 1;
		
		for(int next : list[current]) {
			if(!visited[next]) {
				visited[next] = true;
				if(dfs(next, cnt+1) == 1)
					return 1;
				visited[next] = false;
			}
		}
		
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new List[N];
		visited = new boolean[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
			
		}
		
		int result = 0;
		for(int i=0; i<N; i++) {
			visited[i] = true;
			result = dfs(i, 1);
			if(result == 1) break;
			visited[i] = false;
		}
		System.out.println(result);
	}

}