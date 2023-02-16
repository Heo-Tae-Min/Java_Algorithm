import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		int length = input.length();
		int N = Integer.parseInt(input);
		
		int count = 0;
		int tens = 1;
		for(int i=0; i<length-1; i++) {
			count += 9 * tens * (i+1);
			tens *= 10;
		}
		count += (N-tens+1) * length;
		System.out.println(count);
	}

}