package Java.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 배열 돌리기1
public class BOJ16926 {
    static int[][] board;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        String temp = br.readLine();
        st = new StringTokenizer(temp);

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for(int i = 0; i < N; i++){
            temp = br.readLine();
            st = new StringTokenizer(temp);
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }       

        for(int i = 0; i < R; i++){
            int rs = 0, re = N-1, cs = 0, ce = M-1;     // 초기값
            int inner = (Math.min(N, M)) / 2;           // 내부 순환 값
            
            while(true){
                if(inner == 0) break;
                leftRotation(rs, re, cs, ce);
                inner--;
                rs += 1;
                re -= 1;
                cs += 1;
                ce -= 1;
            }   
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }   

        bw.write(sb.toString());
        bw.flush();
		bw.close();
		br.close();
		br = null;
		bw = null;
    }

    // temp 값에 하나 저장해서 그거 밀어주는 형식으로, 인덱스 범위줘서 재귀로도 가능
    private static void leftRotation(int rs, int re, int cs, int ce){
        int temp = board[rs][cs];   // rs, cs 에 해당하는 곳의 값을 빼놨으니 아래 순서대로 값 밀어야 함

        // ← row start 기준으로 col 움직이기
        for(int i = cs; i < ce; i++){
            board[rs][i] = board[rs][i+1];
        }
        
        // ↑ col end 기준으로 row 움직이기
        for(int i = rs; i < re; i++){
            board[i][ce] = board[i+1][ce];
        }

        // → row end 기준으로 col 움직이기
        for(int i = ce; i > cs; i--){
            board[re][i] = board[re][i-1];
        }

        // ↓ col start 기준으로 row 움직이기
        for(int i = re; i > rs; i--){
            board[i][cs] = board[i-1][cs];
        }

        board[rs+1][cs] = temp;
    }
}
