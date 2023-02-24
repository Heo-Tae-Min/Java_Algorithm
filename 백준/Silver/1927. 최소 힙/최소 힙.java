import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {return a - b;}); 
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(pq.isEmpty()) sb.append(0).append("\n");
				else sb.append(pq.poll()).append("\n");
			}
			else
				pq.offer(num);
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}
}