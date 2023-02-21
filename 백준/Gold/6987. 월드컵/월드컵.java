import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] scores;
	static int[][] simulation;
	
	// 0번 ~ 5번 팀이 경기를 가질 수 있는 모든 경우의 수
	static int[] team1 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] team2 = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	
	// 가상 경기 진행 함수
	static boolean match(int cnt) {
		boolean result = false;
		
		// 모든 경기가 완료된 경우에는 가상 경기와 실제 스코어가 같은지 확인
		if(cnt == 15) {
			for (int i = 0; i < 6; i++)
				for (int j = 0; j < 3; j++)
					if(scores[i][j] != simulation[i][j]) return false;
			
			return true;
		}
		
		// team1 이김, team2 짐
		simulation[team1[cnt]][0]++; 
		simulation[team2[cnt]][2]++;
		result |= match(cnt+1);
		simulation[team1[cnt]][0]--; 
		simulation[team2[cnt]][2]--;
		
		// 무승부
		simulation[team1[cnt]][1]++; 
		simulation[team2[cnt]][1]++;
		result |= match(cnt+1);
		simulation[team1[cnt]][1]--; 
		simulation[team2[cnt]][1]--;
		
		// team1 짐, team2 이김
		simulation[team1[cnt]][2]++; 
		simulation[team2[cnt]][0]++;
		result |= match(cnt+1);
		simulation[team1[cnt]][2]--; 
		simulation[team2[cnt]][0]--;
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		scores = new int[6][3];
		simulation = new int[6][3];
		
		for (int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<6; i++) {
				scores[i][0] = Integer.parseInt(st.nextToken());
				scores[i][1] = Integer.parseInt(st.nextToken());
				scores[i][2] = Integer.parseInt(st.nextToken());
			}
			
			if(match(0)) sb.append("1 ");
			else sb.append("0 ");
		}
		sb.append("\n");
		bw.write(sb.toString());
		bw.flush();
	}

}