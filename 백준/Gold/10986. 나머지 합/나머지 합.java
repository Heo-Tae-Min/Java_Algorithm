import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] mod = new long[N+1];
		long[] num = new long[N+1];
		long[] cnt = new long[M];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			num[i] = Long.parseLong(st.nextToken());
			mod[i] = (mod[i-1] + num[i] % M) % M;
			cnt[(int)mod[i]]++;
		}
		
		long total = 0;

		// 구간의 길이가 2이상인 경우
		// 나머지 같은 거 끼리 빼먼 나머지는 0
		for(int i=0; i<M; i++) {
			if(cnt[i] <= 1) continue;
			total += cnt[i] * (cnt[i]-1) / 2;
		}
		
		// 구간의 길이가 1인 경우
		total += cnt[0];
		
		System.out.println(total);
	}
}