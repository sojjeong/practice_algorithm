package Java.BOJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class BOJ14502{

    static boolean[] isSelected;
    static List<Integer> index; // 조합 만들 숫자 목록
    static int[][] arr;
    static int[][] copyArr;
    static Map<Integer, Integer[]> indexMap;
    static Queue<Integer[]> virusQ;
    static Queue<Integer[]> copyVirusQ;

    static int N;
    static int M;
    static int maxSafeZone = 0;

    static int[] dirX = {-1, 0, 1, 0};
    static int[] dirY = {0, 1, 0, -1};


    public static void main(String[] args) {
        // 바이러스가 있는 위치부터 4방탐색
        // BFS, 바이러스 있는 위치 큐에 담기
        // 벽을 어떻게 설치하냐, 완전 탐색 -> 벽 세울 위치 조합 이용

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();   // 가로
        M = sc.nextInt();   // 세로

        isSelected = new boolean[N*M];
        arr = new int[N][M];
        copyArr = new int[N][M];
        
        int count = 0;
        index = new ArrayList<>();
        indexMap = new HashMap<>();
        virusQ = new LinkedList<>();
        copyVirusQ = new LinkedList<>();

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                int temp = sc.nextInt();
                arr[i][j] = temp;
                copyArr[i][j] = temp;
                
                if(arr[i][j] == 0) {
                    indexMap.put(count, new Integer[]{i, j});
                    index.add(count);
                }
                else if(arr[i][j] == 2) {
                    virusQ.add(new Integer[]{i, j});
                    copyVirusQ.add(new Integer[]{i, j});
                }

                count++;
            }
        }

        combi(0, 0);
        System.out.println(maxSafeZone);
    }
    
    public static void combi(int n, int count){
        if(n > index.size()) return;

        if(count == 3){
            List<Integer> wall = new ArrayList<>();

            for(int i = 0; i < index.size(); i++){
                if(isSelected[i] == true){
                    wall.add(index.get(i));
                }
            }

            // 벽 세울 위치 3개 찾은 후 BFS 호출
            int safe_zone = BFS(wall);
            maxSafeZone = Math.max(safe_zone, maxSafeZone);

            // BFS 끝나면 원상 복구
            copyVirusQ.clear();
            copyVirusQ.addAll(virusQ);
            copyArr = deepCopy(arr);

            return;
        }

        isSelected[n] = true;
        combi(n+1, count+1);

        isSelected[n] = false;
        combi(n+1, count);

    }

    public static int BFS(List<Integer> wall){
        int safe_zone = 0;
        boolean[][] isVisited = new boolean[N][M];
        
        // 벽을 세우고 나서 바이러스 기준으로 BFS

        for(Integer i : wall){
            Integer[] point = indexMap.get(i);
            
            // 벽 세움
            copyArr[point[0]][point[1]] = 1;
        }

        // BFS
        while(!copyVirusQ.isEmpty()){
            Integer[] pos = copyVirusQ.poll();
            isVisited[pos[0]][pos[1]] = true;

            for (int i = 0; i < 4; i++){
                int x = pos[0] + dirX[i];
                int y = pos[1] + dirY[i];
    
                if(x >= 0 && x < N && y >= 0 && y < M && isVisited[x][y] == false && copyArr[x][y] == 0){
                    copyArr[x][y] = 2;
                    copyVirusQ.add(new Integer[]{x, y});
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(copyArr[i][j] == 0){
                    safe_zone++;
                }
            }
        }    

        return safe_zone;
    }

    // deep copy 함수 - 원본 배열로 원상복구 하기 위해 사용
    static int[][] deepCopy(int[][] p){
        int[][] arr = new int[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++)
            arr[i][j] = p[i][j];
        }

        return arr;
    }
}