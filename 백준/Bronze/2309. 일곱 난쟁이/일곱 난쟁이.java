import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int over;
	static int[] dwarf;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		dwarf = new int[9];
		int sum = 0;
		for(int i=0; i<9; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
			sum += dwarf[i];
		}
		Arrays.sort(dwarf);
		
		over = sum - 100;
		
		int out_i=0, out_j=0;
		loop:
		for(out_i=0; out_i<9; out_i++) {
			for(out_j=0; out_j<9; out_j++) {
				if(dwarf[out_i] + dwarf[out_j] == over)
					break loop;
			}
		}
		
		for(int i=0; i<9; i++) {
			if(i == out_i || i == out_j) continue;
			sb.append(dwarf[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

}