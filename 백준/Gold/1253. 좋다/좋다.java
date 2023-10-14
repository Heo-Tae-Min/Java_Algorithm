/**
 *   투포인터 사용하면 될 듯
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static long[] num;
	static int N;
	
	static int cntNumber(long target) {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(num[i] > target) break;
			if(num[i] == target) cnt++;
		}
		
		return cnt;
	}
	
	static boolean check(int idx) {
		
		int head=0, tail= N-1;
		
		// 만약 head와 tail이 교차되었으면 모든 경우를 본 것을 의미하기 때문에 break하도록 설정
		while(head < tail) {
			if(head == idx) {
				head++;
				continue;
			}
			if(tail == idx) {
				tail--;
				continue;
			}
			
			long sum = num[head] + num[tail];
			
			// head + tail이 본인을 넘어간다면 합을 줄여야 하기 때문에 tail을 줄임
			if(sum > num[idx]) tail--;
			
			// 합이 본인보다 작다면 합을 늘려야 하기 때문에 head를 늘림
			else if(sum < num[idx]) head++;
			
			// 합이 본인이라면 true 반환
			else return true;
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = new long[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			num[i] = Long.parseLong(st.nextToken());
		}

		// 투포인터 사용을 위한 정렬
		Arrays.sort(num);
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(check(i)) cnt++;
		}
		
		System.out.println(cnt);
	}
}