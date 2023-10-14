import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long[] Arr, Tree;
	static int N;
	
	static long sum(int index) {
		long total = 0;
		while(index > 0) {
			total += Tree[index];
			index -= index & -index;
		}
		
		return total;
	}
	
	static void update(int index, long after) {
		long diff = after - Arr[index];
		Arr[index] = after;
		while(index <= N) {
			Tree[index] += diff;
			index += index & -index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Arr = new long[N+1];
		Tree = new long[N+1];
		
		for(int i=1; i<=N; i++) {
			long num = Long.parseLong(br.readLine());
			update(i, num);
			Arr[i] = num;
		}
		
		
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(a == 1) {
				update(b, c);
			}
			else if(a == 2) {
				sb.append(sum((int)c) - sum(b-1)).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
		
	}

}