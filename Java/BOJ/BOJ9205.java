package Java.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ9205 {
    static int conveniCnt;
    static Coordinate sangeun;
    static Queue<Coordinate> locationQueue;
    static Coordinate[] conveniList;
    static Coordinate festival;
    static int BeerCnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){

            conveniCnt = Integer.parseInt(br.readLine());
            BeerCnt = 20;

            // 상근이네
            String temp = br.readLine();
            st = new StringTokenizer(temp);
            sangeun = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            // 편의점
            locationQueue = new LinkedList<Coordinate>();
            conveniList = new Coordinate[conveniCnt];

            for(int i = 0; i < conveniCnt; i++){
                temp = br.readLine();
                st = new StringTokenizer(temp);
                conveniList[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            
            // 첫 시작점
            locationQueue.offer(new Coordinate(sangeun.x, sangeun.y));

            // 락 페스티벌 좌표
            temp = br.readLine();
            st = new StringTokenizer(temp);
            festival = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            boolean happy = false;

            // 처음 맨해튼거리 20*50 이내에 락페스티벌장소가 있는지 확인하기
            if(Math.abs(sangeun.x - festival.x) + Math.abs(sangeun.y - festival.y) <= 1000)
                happy = true;
            else{
                // 그렇지 않다면 편의점 들려야 함, 위치를 큐에 담음
                while(!locationQueue.isEmpty()){
                    Coordinate current = locationQueue.poll();

                    if(Math.abs(current.x - festival.x) + Math.abs(current.y - festival.y) <= 1000){
                        happy = true;
                        break;
                    }

                    // 갈 수 있는 편의점 있나 검사
                    for(int i = 0; i < conveniCnt; ++i){
                        if(!conveniList[i].isVisited){
                            int distance = Math.abs(current.x - conveniList[i].x) + Math.abs(current.y - conveniList[i].y);
                            // 방문가능하다면!
                            if(distance <= 1000){
                                conveniList[i].isVisited = true;
                                // 다음 방문할 곳 위치로 넣어줌
                                locationQueue.offer(new Coordinate(conveniList[i].x, conveniList[i].y));
                            }
                        }
                    }
                }
            }

            if(happy) sb.append("happy").append("\n");
            else sb.append("sad").append("\n");
            locationQueue.clear();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class Coordinate{
    int x;
    int y;
    boolean isVisited;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.isVisited = false;
    }
}