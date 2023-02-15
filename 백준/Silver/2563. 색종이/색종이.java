import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		boolean[][] visited = new boolean[100][100];
		
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			for(int r=R; r<R+10; r++)
				for(int c=C; c<C+10; c++) {
					if(visited[r][c]) continue;
					visited[r][c] = true;
					count++;
				}
			
		}
		System.out.println(count);
	}

}