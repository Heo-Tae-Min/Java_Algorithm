import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int five = N/5, three = 0;
		int min = N;
		while(three <= N/3) {
			int sum = five*5 + three*3;
			if(sum > N) five--;
			else if(sum < N) three++;
			else {
				min = five + three;
				break;
			}
		}
		if(min == N) min = -1;
		
		System.out.println(min);
	}

}