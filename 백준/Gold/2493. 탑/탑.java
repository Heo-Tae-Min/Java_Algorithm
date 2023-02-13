import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class Pair {
	int height;
	int index;
	
	public Pair(int height, int index) {
		super();
		this.height = height;
		this.index = index;
	}
}

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		Stack<Pair> stack = new Stack<>();
		
		
		int N = Integer.parseInt(br.readLine());
		int[] tower = new int[N];
		int[] receive = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
		stack.add(new Pair(tower[N-1], N-1));
		for(int i=N-2; i>=0; i--) {			
			while(stack.isEmpty() == false && tower[i] > stack.peek().height) {				
				receive[stack.pop().index] = i+1;
			}
			stack.add(new Pair(tower[i], i));
		}
		while(stack.isEmpty() == false) {
			receive[stack.pop().index] = 0;
		}
		
		for(int data : receive)
			sb.append(data).append(" ");
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
	}

}