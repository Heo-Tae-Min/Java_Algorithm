import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static StringBuilder sb;
	static int[][] spaces;

	static void zip(int r, int c, int size) {
		int sum = 0;
		for (int i = r, rEnd = r+size; i < rEnd; i++) {
			for (int j = c, cEnd = c+size; j < cEnd; j++) {
				sum += spaces[i][j];
			}
		}
		if(sum == size * size) { // 모두 1인 경우
			sb.append(1);
		}else if(sum == 0) { // 모두 0인 경우
			sb.append(0);
		}else { // 혼합된 상황
			// 4분할
			int half = size/2;
			sb.append("(");
			zip(r, c, half);
			zip(r, c+half, half);
			zip(r+half, c, half);
			zip(r+half, c+half, half);
			sb.append(")");
		}
		
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		spaces = new int[N][N];
		
		for(int i=0; i<N; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				spaces[i][j] = temp[j] - '0';
			}
		}
		
		zip(0, 0, N);
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}