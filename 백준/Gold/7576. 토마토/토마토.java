import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N;
	static int[][] box;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int bfs(List<Integer> r, List<Integer> c) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		int cnt=0;
		
		for(int i=0; i<r.size(); i++) {
			queue.offer(new int[] {r.get(i), c.get(i)});
		}
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if(nr>=0 && nr<N && nc>=0 && nc<M && box[nr][nc] == 0) {
					queue.offer(new int[] {nr, nc});
					box[nr][nc] = box[now[0]][now[1]] + 1;
					cnt = box[nr][nc] - 1;
				}
			}
		}
		return cnt;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		List<Integer> r = new ArrayList<Integer>();
		List<Integer> c = new ArrayList<Integer>();
		
		// 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) {
					r.add(i);
					c.add(j);
				}
			}
		}
		int result = bfs(r,c);
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(box[i][j] == 0) {
					result = -1;
					break;
				}
		
		System.out.println(result);
	}
}
