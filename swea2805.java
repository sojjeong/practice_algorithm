import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 2021.08.05 농작물 수확하기
public class swea2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

		int T;
		T= Integer.parseInt(bf.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int result = 0;
            int N = Integer.parseInt(bf.readLine());
            int[][] arr = new int[N][N];

            // 값 넣기
            for(int i = 0; i < N; i++){
                // String[] st = bf.readLine().split("");
                String st = bf.readLine();
                
                for(int j = 0; j < N; j++)
                    // arr[i][j] = Integer.parseInt(st[j]);
                    arr[i][j] = st.charAt(j) - '0';         // 이 방법이 메모리 더 적게 씀!
            }
            
            int center = N / 2;
            int left = center;
            int right = center;
            
            for(int i = 0; i < N; i++){
                for(int j = left; j <= right; j++){
                    result += arr[i][j];
                }

                if(i < center){
                    left--;
                    right++;
                } else {
                    left++;
                    right--;
                }
            }

            sb.append("#").append(test_case).append(" ");
            sb.append(result).append("\n");
		}
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        bf.close();
        bf = null;
        bw = null;
 
    }    
}
