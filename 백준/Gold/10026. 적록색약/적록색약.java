import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {

	static char[][] color;
	static boolean[][] visited, visited_weak;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Map<Character, Integer> map;
	
	static int N;
	
	static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				
				if(nr>=0 && nc>=0 && nr<N && nc<N && !visited[nr][nc] && color[nr][nc] == color[r][c]) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;					
				}
			}
		}
	}
	
	static void bfs_weak(int r, int c) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.offer(new int[] {r, c});
		visited_weak[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				
				if(nr>=0 && nc>=0 && nr<N && nc<N && !visited_weak[nr][nc] && map.get(color[nr][nc]) == map.get(color[r][c])) {
					q.offer(new int[] {nr, nc});
					visited_weak[nr][nc] = true;					
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		color = new char[N][];
		visited = new boolean[N][N];
		visited_weak = new boolean[N][N];
		
		map = new HashMap<Character, Integer>();
		map.put('R', 1);
		map.put('G', 1);
		map.put('B', 2);
		
		for(int i=0; i<N; i++) {
			color[i] = br.readLine().toCharArray();
		}
		
		int cnt=0, cnt_weak=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
					cnt++;
				}
				if(!visited_weak[i][j]) {
					bfs_weak(i, j);
					cnt_weak++;
				}
			}
		}
		sb.append(cnt).append(" ").append(cnt_weak).append("\n");
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}