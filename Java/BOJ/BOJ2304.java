package Java.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Pair{
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class BOJ2304 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Pair[] list = new Pair[N];

        int maxY = 0;
        int maxX = 0;

        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            st = new StringTokenizer(temp);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[i] = new Pair(x, y);

            if(maxY < y){
                maxY = y;
                maxX = x;
            }
        }

        // 높이 순으로 정렬
        int finalMaxX = maxX;

        Arrays.sort(list, ((o1, o2) -> {
            // 같은 높이의 값이 있다면, x 기준으로 내림차순 정렬
            if(o2.y == o1.y) return Math.abs(finalMaxX - o2.x);
            return o2.y - o1.y;
        }));

        /// 첫 시작 인덱스
        int lIndex = maxX;
        int rIndex = maxX;

        int sum = list[0].y;

        for(int i = 1; i < list.length; i++){
            if( list[i].x > rIndex ){   // maxY의 오른쪽 탐색
                sum += list[i].y * (list[i].x - rIndex);
                rIndex = list[i].x;
            } else if (list[i].x < lIndex){ // maxY의 왼쪽 탐색
                sum += list[i].y * (lIndex - list[i].x);
                lIndex = list[i].x;
            }
        }
        System.out.println(sum);
    }
}
