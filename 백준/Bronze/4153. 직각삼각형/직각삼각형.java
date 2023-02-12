import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			int[] lines = new int[3];
			for(int i=0; i<3; i++) lines[i] = Integer.parseInt(st.nextToken());
			
			if(lines[0] == 0 && lines[1] == 0 && lines[2] == 0)
				break;
			
			Arrays.sort(lines);
			
			if(Math.pow(lines[0], 2) + Math.pow(lines[1], 2) == Math.pow(lines[2], 2))
				sb.append("right\n");
			else
				sb.append("wrong\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
}