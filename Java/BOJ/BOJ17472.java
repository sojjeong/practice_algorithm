package Java.BOJ;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ17472 {
    // 부모 원소를 관리(트리처럼 사용
    static int[] parents;
    static int V,E;
    static Edge[] edgeList;
    
    static int N;
    static int M;

    static int[][] arr;
    static boolean[][] isVisited;

    // 상 우 하 좌
    static int[] dirX = {-1, 0, 1, 0};
    static int[] dirY = {0, 1, 0, -1};

    static PriorityQueue<Edge> pq = new PriorityQueue<>();  // 간선 순서, 가중치 작은 순으로

    // 가중치 순으로 sort 하기 위함
    static class Edge implements Comparable<Edge>{      
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            super();
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);      // 간선의 부호가 다를 때
        }
    }

     // a가 속한 집합의 대표자 찾기
    private static int find(int a){
        if(a == parents[a]) return a;   // 자신이 대표자면 자신 return
        return parents[a] = find(parents[a]);   // 자신이 속한 집합의 대표자를 자신의 부모로 : path compression
    }

    private static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        // 둘의 부모가 같다면 이미 같은 집합이니 합치지 않는다.
        if(aRoot == bRoot) return;
        else {
            parents[bRoot] = aRoot;
        }
    }

    private static void make(int n){
        parents = new int[n+1];
        // 모든 원소에서 자신을 대표자로 만듬
        // 1부터 시작
        for(int i = 1; i <= n; i++){
            parents[i] = i;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];
        isVisited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        // 섬을 노드라고 생각하고 하나로 묶어주자
        int count = 1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!isVisited[i][j] && arr[i][j] == 1){
                    BFS(i, j, count);
                    count++;
                }
            }
        }

        // 섬 갯수 만큼
        edgeList = new Edge[count];
        make(count-1);

        // 섬으로 묶고, 각 위치에서 다른 섬으로 가는데, 한 방향으로만 쭉 가보기, 다른 숫자 만날 때까지
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(arr[i][j] != 0){
                    // 다른 방향으로 뻗어나가기, 좌표와 현재 섬의 번호 넘기기
                    makeBridge(i, j, arr[i][j]);
                }
            }
        }

        int result = 0;
        int bridgeCount = 0;

        // foreach 구문 사용하면 우선순위가 깨짐!! 반드시 poll 로 진행
        /*
        for(Edge e: pq){
            // 각 간선의 union, find 진행
            // System.out.println(e.start + " " + e.end + " " + e.weight);
            
            int a = find(e.start);
            int b = find(e.end);

            if(a==b) continue;

            union(e.start, e.end);
            result += e.weight;
            bridgeCount++;
        }
        */

        while(!pq.isEmpty()){
            // 각 간선의 union, find 진행
            
            Edge e = pq.poll();

            int a = find(e.start);
            int b = find(e.end);

            if(a==b) continue;

            union(e.start, e.end);
            result += e.weight;
            bridgeCount++;
        }


        // 다리 갯수 = 섬 - 1 (count 가 한번 더 증가되어 있어서 - 2)
        if(result == 0 || bridgeCount != count-2){
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }


    public static void BFS(int i, int j, int count){
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{i,j});

        while(!q.isEmpty()){
            Integer[] p = q.poll();
            isVisited[p[0]][p[1]] = true;
            arr[p[0]][p[1]] = count;

            // 4방탐색
            for(int n = 0; n < 4; n++){
                int dx = p[0] + dirX[n];
                int dy = p[1] + dirY[n];

                if(dx >= 0 && dx < N && dy >= 0 && dy < M &&
                !isVisited[dx][dy] && arr[dx][dy] == 1){
                    arr[dx][dy] = count;
                    q.add(new Integer[]{dx, dy});
                }
            }
        }
    }


    public static void makeBridge(int i, int j, int num){
        // 한 방향으로만 나가기
        int x = i;
        int y = j;
        int bridgeLength = 0;

        for(int k = 0; k < 4; k++){
            while(true){
                x = x + dirX[k];
                y = y + dirY[k];

                if(x >= 0 && x < N && y >= 0 && y < M){
                    // 간 곳이 빈 곳이면
                    if(arr[x][y] == 0){
                        bridgeLength++;
                    } else if(arr[x][y] == num){
                        // 아직 현재 섬을 벗어나지 못한거면
                        // 다른 방향 탐색해야함, 이 방향으로 가면 안됨
                        bridgeLength = 0;
                        x = i;
                        y = j;
                        break;
                    } else if(arr[x][y] != num){
                        // 섬에 닿음, length 2이상의 경우, 간선으로 추가
                        if(bridgeLength >= 2){
                            pq.add(new Edge(num, arr[x][y], bridgeLength));
                            // pq.add(new Edge(arr[x][y], num, bridgeLength));
                        }

                        // 다음 방향 체크하기 위해 초기화
                        bridgeLength = 0;
                        x = i;
                        y = j;
                        break;
                    }
                } else {    
                    // 유효하지 않은 인덱스이면, 다음 방향 체크하도록 초기화 후 break
                    bridgeLength = 0;
                    x = i;
                    y = j;
                    break;
                }
            }
        }
    }
}
