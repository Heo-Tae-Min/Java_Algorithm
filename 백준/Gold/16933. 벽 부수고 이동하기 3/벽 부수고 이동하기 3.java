import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] map;
	
	static int bfs(int sr, int sc) {
		Queue<int[]> queue = new ArrayDeque<>();
		int[][][] visited = new int[N][M][K+1];
		
		// r, c, 부순 횟수, 낮밤
		queue.offer(new int[] {sr, sc, 0, 0});
		visited[sr][sc][0] = 1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(r == N-1 && c == M-1)
				return visited[r][c][cur[2]];
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr<N && nr>=0 && nc<M && nc>=0) {
					if(map[nr][nc] == 0) {
						if(visited[nr][nc][cur[2]] != 0) continue;
						queue.offer(new int[] {nr, nc, cur[2], cur[3]^1});
						visited[nr][nc][cur[2]] = visited[r][c][cur[2]] + 1;
					}
					else if(map[nr][nc] == 1 && cur[3] == 0 && cur[2] < K) {
						if(visited[nr][nc][cur[2]+1] != 0) continue;
						queue.offer(new int[] {nr, nc, cur[2]+1, cur[3]^1});
						visited[nr][nc][cur[2]+1] = visited[r][c][cur[2]] + 1;
					}
				}
			}
			
			if(cur[3] == 1) {
				queue.offer(new int[] {r, c, cur[2], cur[3]^1});
				visited[r][c][cur[2]] = visited[r][c][cur[2]] + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			char[] row = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = row[j] - '0';
			}
		}
		
		System.out.println(bfs(0, 0));
	}

}