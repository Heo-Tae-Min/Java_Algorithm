import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] numbers;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();
	
	static void perm(int cnt, int N, int M) {
		if(cnt == M) {
			for(int data : numbers) {
				sb.append(data).append(" ");
			}
			sb.append("\n");
			return;
		}
		
	
		for(int i=1; i<=N; i++) {
			if(isSelected[i])
				continue;
			
			numbers[cnt] = i;
			isSelected[i] = true;
			perm(cnt+1, N, M);
			isSelected[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		isSelected = new boolean[N+1];
		
		perm(0, N, M);
		System.out.println(sb.toString());
	}

}