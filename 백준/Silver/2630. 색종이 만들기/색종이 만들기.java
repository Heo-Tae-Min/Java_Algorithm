import java.util.Scanner;
/**
 * @author THKim
 */
public class Main {
	static int white = 0;
	static int blue = 0;
	static int[][] spaces;
	
	// 주어진 영역의 공간의 셀 체크하여 모두 초록색이나 하얀색으로 이루어져있는지 확인 후
	// 4개로 쪼개기.
	// 하얀색 0 , 초록색 1
	static void cut(int r, int c, int size) {
		// 실제로 자를 필요는 없고, 좌상단 좌표와 한변의 크기만 준다면 해결할 수 있다.
		int sum = 0;
		for (int i = r, rEnd = r+size; i < rEnd; i++) {
			for (int j = c, cEnd = c+size; j < cEnd; j++) {
				sum += spaces[i][j];
			}
		}
		if(sum == size * size) { // 모두 초록색인 경우
			blue++;
		}else if(sum == 0) { // 모두 하얀색인 경우
			white++;
		}else { // 혼합된 상황
			// 4분할
			int half = size/2;
			cut(r, c, half);
			cut(r, c+half, half);
			cut(r+half, c, half);
			cut(r+half, c+half, half);
		}
		
		return; // 여기가 return 자리에요
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		spaces = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				spaces[i][j] = sc.nextInt();
			}
		}
		
		cut(0, 0, n);

		System.out.println(white);
		System.out.println(blue);
		sc.close();
	}
}