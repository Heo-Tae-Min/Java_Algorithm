import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static Map<String, String> parent;
	static Map<String, Integer> nums;
	
	static void init() {
		parent = new HashMap<>();
		nums = new HashMap<>();
	}
	
	static String find(String a) {
		if(a.equals(parent.get(a))) return a;
		
		parent.put(a, find(parent.get(a)));
		return parent.get(a);
	}
	
	static int union(String a, String b) {
		
		// Map에 없는 경우 자기 자신을 부모로 초기화
		if(!parent.containsKey(a)) {
			parent.put(a, a);
			nums.put(a, 1);
		}
		if(!parent.containsKey(b)) {
			parent.put(b, b);
			nums.put(b, 1);
		}
		
		String aRoot = find(a);
		String bRoot = find(b);
		
		// 최고 부모가 같은 경우 부모의 nums를 리턴
		if(aRoot.equals(bRoot))
			return nums.get(aRoot);
		
		// 최고 부모가 다른 경우
		int sum = nums.get(aRoot) + nums.get(bRoot);
		nums.put(aRoot, sum);
		parent.put(bRoot, aRoot);
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트 케이스 입력
		int T = Integer.parseInt(br.readLine());
		
		// 테케만큼 루프
		for(int tc=1; tc<=T; tc++) {
			
			init();
			
			// F 입력
			int F = Integer.parseInt(br.readLine());
			
			for(int i=0; i<F; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String a = st.nextToken();
				String b = st.nextToken();
				
				sb.append(union(a, b)).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}