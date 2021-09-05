package Java.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 섬의 개수
public class BOJ4963 {
    static int W;   // 너비 Y
    static int H;   // 높이 X
    static int[][] island;
    static boolean[][] isVisited;
    static int count;

    // 좌상, 상, 우상, 우, 우하, 하, 좌하, 좌
    static int[] dirX = new int[]{-1, -1, -1, 0, 1, 1, 1, 0}; 
    static int[] dirY = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 입력은 무한대로 받되
        // 0 0 을 입력 받으면 프로그램 끝, 전체 섬 출력
        while(true){
            String temp = br.readLine();
            st = new StringTokenizer(temp);

            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            count = 0;
            
            if(W == 0 && H == 0) break;

            island = new int[H][W];
            isVisited = new boolean[H][W];

            for(int i = 0; i < H; i++){
                temp = br.readLine();
                st = new StringTokenizer(temp);
                for(int j = 0; j < W; j++){
                    island[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // DFS 를 호출 할 때, 탐색할 첫 인덱스를 바꿔주기 위해서 완탐이라 생각하고 전체 for 문 돌리기
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(!isVisited[i][j] && island[i][j] == 1)   // 가지치기
                    {
                        DFS(i, j);
                        count++;    // 탐색이 끝났으니 count 증가
                    }
                }
            }
            
            sb.append(count).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    
    private static void DFS(int startX, int startY){  
        // 검사하는 곳, 방문했으니 true
        isVisited[startX][startY] = true;

        for(int k = 0; k < 8; k++){
            int nextX = startX + dirX[k];
            int nextY = startY + dirY[k];

            // 유효 인덱스이고
            if(nextX >= 0 && nextX < H && nextY >= 0 && nextY < W){
                // 방문하지 않았고 
                if(!isVisited[nextX][nextY]){
                    if(island[nextX][nextY] == 1){
                        DFS(nextX, nextY);
                    } 
                } 
            } 
        }
    }
}
