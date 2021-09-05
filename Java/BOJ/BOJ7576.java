package Java.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 토마토
public class BOJ7576 {
    static int[][] tomato;
    static int N;
    static int M;
    static int[] dirX = {-1, 1, 0, 0}; //상 하 좌 우
    static int[] dirY = {0, 0, -1, 1};
    static boolean[][] isVisited;
    static Queue<int[]> willVisit = new LinkedList<int[]>();
    static int day = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 
        // 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 
        // 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 
        // 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.
        // 1은 익은 토마토, 0은 익지 않은 토마토, -1은 토마토가 없는 칸

        // 입력
        String temp = br.readLine();
        st = new StringTokenizer(temp);
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        tomato = new int[N][M];
        isVisited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            temp = br.readLine();
            st = new StringTokenizer(temp);

            for(int j = 0; j < M; j++){
                int num = Integer.parseInt(st.nextToken());
                tomato[i][j] = num;

                // 1일때 방문을 위해 큐에 먼저 담는다
                if(num == 1){
                    willVisit.offer(new int[]{i, j});
                }
            }
        }

        // 탐색! -> 인접한 곳에 토마토가 있는지 봐야 하니까 너비우선탐색으로 접근
        // 큐를 사용! -> 방문 할 예정인 인덱스를 큐에 담는다. 
        // 방문했던 곳은 재방문하지 않도록 방문 표시 저장용도 만든다(boolean 배열로 해서 [i][j] 가 true,false 인지 바로 접근하도록).

        BFS();
        System.out.println(Arrays.deepToString(tomato));

        sb.append(day);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void BFS(){     
        Queue<int[]> tempQueue = new LinkedList<>();

        while(true){
            while(!willVisit.isEmpty()){
                // 4방향 탐색
                int[] curIndex = willVisit.poll();
    
                for(int i = 0; i < 4; i++){
                    int nextX = curIndex[0] + dirX[i];
                    int nextY = curIndex[1] + dirY[i];
                    
                    // 유효하지 않은 인덱스이거나
                    if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                    
                    // 이미 방문한 곳이면
                    if(isVisited[nextX][nextY]) continue;
    
                    // 토마토가 없으면
                    if(tomato[nextX][nextY] == -1) {
                        // 방문하지 않도록 방문처리 하고 continue
                        isVisited[nextX][nextY] = true;
                        continue;
                    }
    
                    // 다음 인덱스의 토마토가 익지 않고 방문한적 없을 때
                    if(tomato[nextX][nextY] == 0 && !isVisited[nextX][nextY]){
                        tomato[nextX][nextY] = 1;
                        tempQueue.offer(new int[]{nextX, nextY});
                    } 
                }
            }

            // 현재 날짜의 큐를 다 방문해서 비우고 날짜 증가시켜야함
            // 즉 다음날 방문할 큐는 현재 큐를 다 비운다음에 넣어야 한다는 것   
            if(tempQueue.isEmpty()) break;  // 더 방문할 곳이 없다면 break
            
            day++;
            willVisit.addAll(tempQueue);
            tempQueue.clear();
        }

        // 큐가 다 비어서 방문할 수 없는데 익지 않은 토마토가 하나라도 있으면 -1 반환
        if(willVisit.isEmpty()){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(tomato[i][j] == 0){
                        day = -1;
                        return;
                    }
                }
            }
        }
    }
}
