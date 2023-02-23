import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int R, C, max;
	static char[][] board;
	static int[] dr = {1, 0, 0, -1};
	static int[] dc = {0, 1, -1, 0};
	
	static void move(int cnt, int r, int c) {
		int nr, nc;
		for(int i=0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			
			if(nr>=0 && nc>=0 && nr<R && nc<C && !visited[board[nr][nc]]) {
				visited[board[nr][nc]] = true;
				move(cnt+1, nr, nc);
				visited[board[nr][nc]] = false;
			}
		}
		
		max = Math.max(max, cnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][];
		// A:65 ~ Z:90
		visited = new boolean[91];
		
		for(int i=0; i<R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		visited[board[0][0]] = true;
		
		move(1, 0, 0);
		
		System.out.println(max);
	}

}