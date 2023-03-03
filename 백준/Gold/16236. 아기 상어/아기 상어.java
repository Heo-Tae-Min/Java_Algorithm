import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, eaten = 0;
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	static int[] baby;
	static int[][] map;
	
	static int bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {return a[2] != b[2] ? a[2] - b[2] : a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];});
//		Queue<int[]> queue = new ArrayDeque<int[]>();
		int[][] visited = new int[N][N]; 
		
		pq.offer(new int[] {baby[0], baby[1], 1});
		map[baby[0]][baby[1]] = 0;
		visited[baby[0]][baby[1]] = 1;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(map[cur[0]][cur[1]] > 0 && map[cur[0]][cur[1]] < baby[2]) {
				eaten++;
				if(eaten == baby[2]) {
					eaten = 0;
					baby[2]++;
				}
				baby[0] = cur[0];
				baby[1] = cur[1];
				map[cur[0]][cur[1]] = 9;
				return cur[2] - 1;
			}
			
			for(int i=0; i<4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N && visited[nr][nc] == 0 && map[nr][nc] <= baby[2]) {
					visited[nr][nc] = visited[cur[0]][cur[1]] + 1;
					pq.offer(new int[] {nr, nc, visited[nr][nc]});
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		baby = new int[3];
		baby[2] = 2;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					baby[0] = i;
					baby[1] = j;
				}
			}
		}
		int time;
		int total = 0;
		while((time = bfs()) != 0) {
			total += time;
		}
		System.out.println(total);
	}

}
