package Java.BOJ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3985 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cakeSize = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] cakeCheck = new int[cakeSize+1];  // 0번인덱스 안씀
        int[][] wantCake = new int[N][];        // 원하는 케이크
        int[][] realCake = new int[N][2];        // 실제 가져간 케이크 갯수
        int maxValue = 0;
        int maxNum = 0;

        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            st = new StringTokenizer(temp);
            int p = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            wantCake[i] = new int[]{p, k, k-p+1};   // 시작, 끝, 케익갯수
        }

        for(int i = 0; i < N; i++){
            if(maxValue < wantCake[i][2]){
                maxValue = wantCake[i][2];
                maxNum = i+1;
            }
        }
        
        // 가장 많이 받을거라 기대되는 사람  
        System.out.println(maxNum);

        //케이크에 방청객 종이 적기
        for(int i = 0; i < N; i++){
            int p = wantCake[i][0];
            int k = wantCake[i][1];

            for(int j = p; j <= k; j++){
                if(cakeCheck[j] == 0) cakeCheck[j] = i+1;
            }
        }

        // 가장 많은 케익을 가져가는 사람 구하기
        for(int i = 1; i < cakeCheck.length; i++){
            if(cakeCheck[i] == 0) continue;

            realCake[cakeCheck[i]-1][0] = cakeCheck[i];
            realCake[cakeCheck[i]-1][1] += 1;
        }

        int maxCake = 0;
        int maxCakeTaker = 0;

        for (int[] ints : realCake) {
            if (maxCake < ints[1]) {
                maxCake = ints[1];
                maxCakeTaker = ints[0];
            }
        }
        System.out.println(maxCakeTaker);
    }
}
