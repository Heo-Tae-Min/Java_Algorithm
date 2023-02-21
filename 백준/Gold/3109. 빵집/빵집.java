import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] isVisited;
	static int R, C, cnt=0;
	static int[] dr = {-1, 0, 1};
	
	static boolean pipe(int row, int col) {
		isVisited[row][col] = true;
		if(col == C-1) return true;
		
		int nr, nc;
		for(int i=0; i<3; i++) {
			nr = row + dr[i];
			nc = col + 1;
			if(nr >= 0 && nr < R && nc >= 0 && nc < C && !isVisited[nr][nc]) {
				if(pipe(nr, nc))
					return true;
			}
		}
		return false;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		isVisited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(temp[j] == 'x') isVisited[i][j] = true;
			}
		}
		
		for(int i=0; i<R; i++) {
			if(pipe(i, 0)) cnt++;
		}
		
		System.out.println(cnt);
	}

}