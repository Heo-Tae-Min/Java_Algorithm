import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int year = -1;
			
			int index = 0;
			while(true) {
				int years = M * (index++ + 1);
				if(years % N == 0) break;
			}
			
			for(int i=0; i<=index; i++) {
				int years = M*i + x;
				if((years - y) % N == 0) {
					year = years;
					break;
				}
			}
			
			sb.append(year).append("\n");
		}
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}