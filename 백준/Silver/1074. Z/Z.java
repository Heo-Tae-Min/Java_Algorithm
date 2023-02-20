import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r, c, count = 0;
	
	static int visit(int row, int col, int size) {
		if(size == 1) {
			if(row == r && col == c) return count;
			else {
				count++;
				return -1;
			}
		}
		// 4분할
		int half = size/2;
		int mid_r = row+half, mid_c = col+half;
		
		if(mid_r > r && mid_c > c) {
			// 0번 칸 바로
			return visit(row, col, half);
		}
		else if(mid_r > r && mid_c <= c) {
			count += half * half;
			// 0번 칸만큼 더하고 1번 칸 탐색
			return visit(row, mid_c, half);
		}
		else if(mid_r <= r && mid_c > c ) {
			count += half * half * 2;
			// 0, 1번 칸만큼 더하고 2번 칸 탐색
			return visit(mid_r, col, half);
		}
		else {
			count += half * half * 3;
			// 0, 1, 2번 칸만큼 더하고 3번 칸 탐색
			return visit(mid_r, mid_c, half);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int size = (int)Math.pow(2, N);
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int result = visit(0, 0, size);
		System.out.println(result);
	}

}