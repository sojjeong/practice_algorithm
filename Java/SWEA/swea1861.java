package Java.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

// 2021.08.06 정사각형 방
public class swea1861 {
    static int[] dirX = {0, 1, 0, -1};
	static int[] dirY = {-1, 0, 1, 0};
	static int[][] arr;
	static int result = 0;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer token;
		Stack<Integer[]> stack = new Stack<Integer[]>();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			// 입력
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				token = new StringTokenizer(str);
				
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(token.nextToken());
				}
			}

			int i = 0;
			int j = 0;

			while(true){
				if(j == N){
					++i;
					j = 0;
				}

				if (i == N) break;

				searchRoom(i, j, 1);
				stack.push(new Integer[]{arr[i][j], result});
				j++;
			}

			int minRoomNum = 10000;
			int maxCnt = 0;

			while(!stack.empty()){
				Integer[] element = stack.pop();
				if(maxCnt < element[1]){
					minRoomNum = element[0];
					maxCnt = element[1];
				}
				else if(maxCnt == element[1]){
					if(minRoomNum > element[0]){
						minRoomNum = element[0];
					}
				}	
			}
			sb.append("#").append(test_case).append(" ").append(minRoomNum).append(" ").append(maxCnt).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		br = null;
		bw = null;
	}
	
	private static void searchRoom(int x, int y, int cnt) {
		boolean[] isTrue = new boolean[4];
		
		for(int i = 0; i < 4; i++) {
			int tempX = dirX[i] + x;
			int tempY = dirY[i] + y;
			
			if((tempX < 0 || tempX >= N || tempY < 0 || tempY >= N)) {
				isTrue[i] = false;
				continue;
			}
			
			if(arr[tempX][tempY] == (arr[x][y] + 1)) { 
				isTrue[i] = true;
				searchRoom(tempX, tempY, cnt+1);
				break;
			} else {	// 1보다 큰 게 없으면, 다음 for index 검사
				isTrue[i] = false;
			}
		}
		
		int boolCnt = 0;
		for(int i = 0; i < 4; i++){
			if(!isTrue[i]){
				boolCnt++;
			}
		}

		if (boolCnt == 4){
			result = cnt;
		}
	}
}
