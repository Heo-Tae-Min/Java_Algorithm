import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] room;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R, C, T, machine;
	

	static void diffuse() {
		int[][] next = new int[R][C];
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(room[r][c] > 0) {
					if(room[r][c] <= 4) {
						next[r][c] += room[r][c];
						continue;
					}
					
					int cur = room[r][c];
					int near = room[r][c] / 5;
					// 미세먼지가 있는 칸이면 4방으로 확산
					for(int d=0; d<4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr>=0 && nr<R && nc>=0 && nc<C && room[nr][nc] != -1) {
							next[nr][nc] += near;
							cur -= near;
						}
					}
					next[r][c] += cur;
				}
			}
		}
		room = next;
		room[machine][0] = -1;
		room[machine+1][0] = -1;
	}
	
	static void circulation(int start) {
		int up = start;
		int down = start + 1;
		
		// 윗부분 순환
		for(int r=up-2; r>=0; r--) room[r+1][0] = room[r][0];
		for(int c=1; c<C; c++) room[0][c-1] = room[0][c];
		for(int r=1; r<=up; r++) room[r-1][C-1] = room[r][C-1];
		for(int c=C-2; c>=1; c--) room[up][c+1] = room[up][c];
		room[up][1] = 0;
		
		// 아랫부분 순환
		for(int r=down+2; r<R; r++) room[r-1][0] = room[r][0];
		for(int c=1; c<C; c++) room[R-1][c-1] = room[R-1][c];
		for(int r=R-2; r>=down; r--) room[r+1][C-1] = room[r][C-1];
		for(int c=C-2; c>=1; c--) room[down][c+1] = room[down][c];
		room[down][1] = 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
			
		machine = 0;
		for(int i=0; i<R; i++) {
			if(room[i][0] == -1) {
				machine = i;
				break;
			}
		}
		while(T-->0) {
			diffuse();
			circulation(machine);
		}
		
		int sum = 0;
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				if(room[i][j] > 0)
					sum += room[i][j];
		
		System.out.println(sum);
	}

}
