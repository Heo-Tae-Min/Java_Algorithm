import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[100001];
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(N);
		visited[N] = true;
		
		int cnt = 0;
		loop:
		while(!queue.isEmpty()) {
			int len = queue.size();
			for(int i=0; i<len; i++) {
				int current = queue.poll();
				if(current == K) break loop;
				int[] nexts = {current-1, current+1, current*2};
				for(int j=0; j<3; j++) {
					if(nexts[j] >= 0 && nexts[j] <= 100000 && !visited[nexts[j]]) {
						queue.offer(nexts[j]);
						visited[nexts[j]] = true;
					}
				}
			}
			cnt++;
		}
		System.out.println(cnt);
	}

}