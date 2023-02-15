import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<List<Integer>> nums;
	
	static void one() {
		Collections.reverse(nums);
	}
	
	static void two() {
		for(int i=0; i<nums.size(); i++) {
			Collections.reverse(nums.get(i));
		}
	}
	
	static void three() {
		List<List<Integer>> result = new ArrayList<>();
		int nn = nums.size();
		int nm = nums.get(0).size();
		
		for(int i=0; i<nm; i++) {
			List<Integer> temp = new ArrayList<>();
			for(int j=nn-1; j>=0; j--) {
				temp.add(nums.get(j).get(i));
			}
			result.add(temp);
		}
		nums = result;
	}
	
	static void four() {		
		List<List<Integer>> result = new ArrayList<>();
		int nn = nums.size();
		int nm = nums.get(0).size();
		
		for(int i=nm-1; i>=0; i--) {
			List<Integer> temp = new ArrayList<>();
			for(int j=0; j<nn; j++) {
				temp.add(nums.get(j).get(i));
			}
			result.add(temp);
		}
		nums = result;
	}
	
	static void five() {
		List<List<Integer>> result = new ArrayList<>();
		int nn = nums.size();
		int nm = nums.get(0).size();
		
		for(int i=0; i<nm; i += nm/2) {
			for(int j=0; j<nn/2; j++) {
				List<Integer> temp = new ArrayList<>();
				temp.addAll(nums.get(j+nn/2).subList(i, i+nm/2));
				temp.addAll(nums.get(j).subList(i, i+nm/2));
				
				result.add(temp);
			}
		}
		nums = result;
	}

	static void six() {
		List<List<Integer>> result = new ArrayList<>();
		int nn = nums.size();
		int nm = nums.get(0).size();
		
		for(int i=nm/2; i>=0; i -= nm/2) {
			for(int j=0; j<nn/2; j++) {
				List<Integer> temp = new ArrayList<>();
				temp.addAll(nums.get(j).subList(i, i + nm/2));
				temp.addAll(nums.get(j+nn/2).subList(i, i + nm/2));
				
				result.add(temp);
			}
		}
		nums = result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		nums = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			List<Integer> temp = new ArrayList<>();
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			nums.add(temp);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int input = Integer.parseInt(st.nextToken());
			switch(input) {
			case 1: 
				one();
				break;
			case 2:
				two();
				break;
			case 3:
				three();
				break;
			case 4:
				four();
				break;
			case 5:
				five();
				break;
			case 6:
				six();
			}
		}
		for(List<Integer> data : nums) {
			for(Integer num : data)
				sb.append(num).append(" ");
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

}