import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 *  유클리드 호제법
	 *  a와 b의 최대공약수는  => b와 a%b의 최대공약수와 같음
	 *  이 두 수를 나눠지는 수, 나누는 수라 부르면
	 *  나누는 수가 0이 되는 경우 나눠지는 수가 처음 a, b의 최소 공배수
	 */
	static int most(int a, int b) {
		return b != 0 ? most(b, a%b) : a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int gcd = most(Math.max(N, M), Math.min(N, M));
		sb.append(gcd).append("\n");
		sb.append(N*M/gcd).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
	}

}