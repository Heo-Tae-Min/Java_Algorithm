import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		Stack<Character> stack = new Stack<>();
		
		loop:
		for(int tc=1; tc<=T; tc++) {
			String input = br.readLine();
			for(int i=0; i<input.length(); i++) {
				if(input.charAt(i) == '(')
					stack.push('(');
				else if(input.charAt(i) == ')') {
					if(stack.isEmpty()) {
						sb.append("NO\n");
						continue loop;						
					}
					stack.pop();
				}
			}
			
			if(!stack.isEmpty()) {
				sb.append("NO\n");
				stack.clear();
			}
			else {
				sb.append("YES\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}