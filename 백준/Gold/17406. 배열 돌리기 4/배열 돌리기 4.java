import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] nums, order;
    static int[] picked;
    static boolean[] isSelected;
    static int N, M, K, Min = Integer.MAX_VALUE;
    
    // 회전 함수
    static int[][] rotate(int[][] arr, int r, int c, int s) {
    	int[][] result = new int[N][M];
    	
    	for(int i=0; i<N; i++)
    		for(int j=0; j<M; j++)
    			result[i][j] = arr[i][j];
    	
        for(int i=1; i<=s; i++) {
            int rs = r-i, re = r+i;
            int cs = c-i, ce = c+i;
            
            for(int j=rs; j<re; j++) result[j][cs] = arr[j+1][cs]; 
            for(int j=cs; j<ce; j++) result[re][j] = arr[re][j+1];
            for(int j=re; j>rs; j--) result[j][ce] = arr[j-1][ce];
            for(int j=ce; j>cs; j--) result[rs][j] = arr[rs][j-1];
        }
        return result;
    }
    
    static int arrCost(int[][] arr) {
    	int min = Integer.MAX_VALUE;
    	for(int i=0; i<N; i++) {
    		int sum = 0;
    		for(int j=0; j<M; j++)
    			sum += arr[i][j];
    		min = Math.min(min, sum);
    	}
    	return min;
    }
    
    // 연산 순서 순열
    static void pick(int cnt) {
    	if(cnt == K) {
    		int[][] result = nums;
    		for(int idx : picked) {
    			result = rotate(result, order[idx][0], order[idx][1], order[idx][2]);
    		}
    		Min = Math.min(Min, arrCost(result));
    		return;
    	}
    	
    	for(int i=0; i<K; i++) {
    		if(isSelected[i]) continue;
    		
    		picked[cnt] = i;
    		isSelected[i] = true;
    		
    		pick(cnt+1);
    		
    		isSelected[i] = false;
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        nums = new int[N][M];
        order = new int[K][3];
        picked = new int[K];
        
        isSelected = new boolean[K];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            
            int[] temp = new int[] {r, c, s};
            order[i] = temp;
        }
        
        pick(0);
        
        System.out.println(Min);
    }
}