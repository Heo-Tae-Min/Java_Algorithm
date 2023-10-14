import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long[] tree;
	static long[] nums;
	static int N;
	
	static long sum(int index) {
		long total = 0;
		while(index > 0) {
			total += tree[index];
			index -= (index & -index);
		}
		
		return total;
	}
	
	static void update(int index, long after) {
		long diff = after - nums[index];
		nums[index] = after;
		while(index <= N) {
			tree[index] += diff;
			index += index & -index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		nums = new long[N+1];
		tree = new long[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			long num = Long.parseLong(st.nextToken());
			update(i, num);
			nums[i] = num;
		}
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());  
			int y = Integer.parseInt(st.nextToken());  
			int a = Integer.parseInt(st.nextToken());  
			int b = Integer.parseInt(st.nextToken());
			
			if(x > y) {
				x = x^y;
				y = x^y;
				x = x^y;
			}
			
			sb.append(sum(y) - sum(x-1)).append("\n");
			update(a,b);
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}