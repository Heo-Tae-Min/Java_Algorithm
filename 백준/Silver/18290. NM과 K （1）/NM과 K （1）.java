import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int K, M, size;
	static long max = Long.MIN_VALUE;
	static int[] input;
	static boolean[] visited;
	
	static void select(int cnt, int start, long sum) {
		if(cnt == K) {
			max = Math.max(max, sum);
			return;
		}
		
		
		for(int i=start; i<size; i++) {
			if(visited[i]) continue;
			
			int[] near;
			
			if(i%M == M-1)
				near = new int[] {i, i-1, i-M, i+M};
			else if(i%M == 0)
				near = new int[] {i, i+1, i-M, i+M};
			else
				near = new int[] {i, i-1, i+1, i-M, i+M};
			
			List<Integer> nearList = new ArrayList<Integer>();
			for(int j=0; j<near.length; j++) {
				if(near[j] >= 0 && near[j] < size && !visited[near[j]]) {
					nearList.add(near[j]);
					visited[near[j]] = true;
				}
			}
			select(cnt+1, i+1, sum+input[i]);
			
			for(int index : nearList) {
				visited[index] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		size = N*M;
		
		input = new int[size];
		visited = new boolean[size];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				input[M*i + j] = Integer.parseInt(st.nextToken());
			}
		}
		
		select(0, 0, 0);
		
		System.out.println(max);
	}

}