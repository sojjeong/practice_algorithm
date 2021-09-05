package Java.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea1247 {
    static int x, y;    // 회사 위치, 시작 위치
    static int hx, hy;  // 집 위치
    static int N;
    static int[][] resultArr;
    static int[][] location;    // 고객 위치
    static boolean[] isSelected;
    static int totalSum;
    
    public static void main(String[] args) throws Exception{
        // 순열문제! 재귀함수 잘 가지치기
        // 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것을 찾으려 한다.
        // 두 위치 (x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|으로 계산된다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine());
            
            // 회사의 좌표, 집의 좌표, N명의 고객의 좌표
            String temp = br.readLine();
            st = new StringTokenizer(temp);

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            hx = Integer.parseInt(st.nextToken());
            hy = Integer.parseInt(st.nextToken());

            totalSum = 10000;
            location = new int[N][];
            resultArr = new int[N][];
            isSelected = new boolean[N];

            for(int i = 0; i < N; i++){
                location[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            }

            generatePremutaion(0, 0);
            sb.append("#").append(test_case).append(" ").append(totalSum).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    private static void generatePremutaion(int resultArrIndex, int sum) { // cnt : 부분집합을 처리하기 위해 고려된 원소 수
        if(sum > totalSum)  return;     // 기존의 min 보다 크면 계산 x

        if(resultArrIndex == N){
            // 집까지 거리 계산
            int x2 = resultArr[N-1][0];
            int y2 = resultArr[N-1][1];
            sum += Math.abs(hx - x2) + Math.abs(hy - y2);
            totalSum = Math.min(sum, totalSum);
            return;
        }

        for(int i = 0; i < N; i++){
            if(isSelected[i]) continue;

            resultArr[resultArrIndex] = location[i];

            int x1 = location[i][0];
            int y1 = location[i][1];

            int distance = Math.abs(x-x1) + Math.abs(y-y1);

            int tempx = x;
            int tempy = y;
            
            // 이동한 곳으로 갱신
            x = x1;
            y = y1;

            isSelected[i] = true;
            generatePremutaion(resultArrIndex+1, sum+distance);
            isSelected[i] = false;

            // 원래 위치로 되돌리기
            x = tempx;
            y = tempy;
        }
    }
}
