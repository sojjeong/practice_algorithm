import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 2021.08.04 파리 퇴치
public class swea2001 {
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);	// 빈칸을 받으려면 StringTokenizer 사용해야함
        	int N = Integer.parseInt(st.nextToken()); //첫번째 호출
        	int M = Integer.parseInt(st.nextToken()); //두번째 호출
			
			int[][] arr = new int[N][N];
			int result = 0;

			for (int i = 0; i < N; i++) {
				str = br.readLine();
				st = new StringTokenizer(str);

				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
	
			for(int i = 0; i <= N-M; i++){
				for(int j = 0; j <= N-M; j++){
					int temp = 0;
					for(int n = i; n < i+M; n++){
						for(int m = j; m < j+M; m++){
							temp += arr[n][m];
						}
					}

					result = Math.max(result, temp);
				}
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
			
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		bw = null;
		br.close();
		br = null;
	}
}
