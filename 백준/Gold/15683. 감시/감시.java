import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<CCTV> cctvs;
	static int N, M, min = Integer.MAX_VALUE;
	
	static class CCTV {
		int num;
		int x;
		int y;
		
		public CCTV(int num, int x, int y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "CCTV [num=" + num + ", x=" + x + ", y=" + y + "]";
		}
	}
	
	static int[][] marking(int x, int y, int[][] map, int[] directions) {
		// 깊은 복사
		int[][] cpMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				cpMap[i][j] = map[i][j];
			}
		}
		
		for(int dir : directions) {
			int nr = x + dr[dir];
			int nc = y + dc[dir];
			
			while(nr>=0 && nr<N && nc>=0 && nc<M) {
				if(cpMap[nr][nc] == 6) break;
				if(cpMap[nr][nc] == 0) cpMap[nr][nc] = -1; 
				
				nr += dr[dir];
				nc += dc[dir];
			}
		}
		
		return cpMap;
	}
	
	static void watch(int cnt, int[][] map) {
		if(cnt == cctvs.size()) {
			// 사각지대 개수 세기
			int total = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) total++;
				}
			}
			
			min = Math.min(min, total);
			return;
		}
		
		CCTV now = cctvs.get(cnt);
		
		if(now.num == 1) {
			for(int i=0; i<4; i++) {
				int[][] cpMap = marking(now.x, now.y, map, new int[] {i});
				watch(cnt+1, cpMap);
			}
		} else if(now.num == 2) {
			for(int i=0; i<2; i++) {
				int[][] cpMap = marking(now.x, now.y, map, new int[] {i, i+2});
				watch(cnt+1, cpMap);
			}
		} else if(now.num == 3) {
			for(int i=0; i<4; i++) {
				int[][] cpMap = marking(now.x, now.y, map, new int[] {i, (i+1)%4});
				watch(cnt+1, cpMap);
			}
		} else if(now.num == 4) {
			for(int i=0; i<4; i++) {
				int[][] cpMap = marking(now.x, now.y, map, new int[] {i, (i+1)%4, (i+3)%4});
				watch(cnt+1, cpMap);
			}
		} else {
			int[][] cpMap = marking(now.x, now.y, map, new int[] {0, 1, 2, 3});
			watch(cnt+1, cpMap);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		cctvs = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5)
					cctvs.add(new CCTV(map[i][j], i, j));
			}
		}
		
		watch(0, map);
		System.out.println(min);
	}

}