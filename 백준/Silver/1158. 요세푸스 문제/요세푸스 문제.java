import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		sb.append("<");
		while(queue.isEmpty() == false) {
			// K-1번 순환 시키고
			for(int i=0; i<K-1; i++)
				queue.add(queue.poll());
			// 하나 뽑기
			sb.append(queue.poll()).append(", ");
		}
		sb.setLength(sb.length()-2);
		sb.append(">\n");
		
		bw.write(sb.toString());
		bw.flush();
	}

}