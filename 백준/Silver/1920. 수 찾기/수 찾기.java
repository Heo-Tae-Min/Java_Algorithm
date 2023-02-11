import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		TreeSet<Integer> nums = new TreeSet<>();
		
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; i++) {
			if(nums.contains(Integer.parseInt(st.nextToken())))
				sb.append(1).append("\n");
			else
				sb.append(0).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

}