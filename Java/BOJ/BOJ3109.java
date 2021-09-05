package Java.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3109 {
    static int R, C;

    // 우상, 우, 우하
    static int[] dirX = {-1, 0, 1};
    static int[] dirY = {1, 1, 1};
    static String[][] board;
    static boolean[][] isVisited;
    static int gasCnt = 0;

    public static void main(String[] args) throws Exception{
        /**
         * 원웅이는 가스를 되도록 많이 훔치려고 한다. 따라서, 가스관과 빵집을 연결하는 파이프라인을 여러 개 설치할 것이다.
         * 이 경로는 겹칠 수 없고, 서로 접할 수도 없다. 즉, 각 칸을 지나는 파이프는 하나이어야 한다.
         * 지나간 경로는 가지 않도록 isVisited boolean 배열 
         * 전으로 되돌아가서 다음 방향 탐색하지 않게 파이프가 연결이 되면 재귀를 완전히 끊어야 함
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String temp = br.readLine();
        st = new StringTokenizer(temp);

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new String[R][C];
        isVisited = new boolean[R][C];

        for(int i = 0; i < R; i++){
            String[] tempArr = br.readLine().split("");
            board[i] = tempArr;
        }

        for(int i = 0 ; i < R; i++){
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();

        for(int i = 0; i < R; i++){
            isVisited[i][0] = true;
            DFS(i, 0);  // 무조건 x는 0부터 시작

            for(int j = 0 ; j < R; j++){
                System.out.println(Arrays.toString(board[j]));
            }
            System.out.println();
        }

        sb.append(gasCnt);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean DFS(int x, int y){
        if(y == C-1){
            gasCnt++;
            board[x][y] = "O";
            return true;
        }

        for(int i = 0; i < 3; i++){
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            // 유효한 인덱스 일 때
            if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C){
                if(!isVisited[nextX][nextY] && board[nextX][nextY].equals(".")){
                    board[x][y] = "O";
                    isVisited[nextX][nextY] = true;

                    if(DFS(nextX, nextY)) return true;  // 타고타고 가던게 성공했다면 return true
                }
            }
        }
        // 이게 끝났는데도 true 가 return 이 안되면 false return
        return false;
    }
}
