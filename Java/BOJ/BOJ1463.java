package Java.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 1로 만들기 - dp
public class BOJ1463 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());   //1로 만들어야 하는 수
        int[] dp = new int[N+1];
        
        dp[0] = 0;
        dp[1] = 0;    

        if(N >= 2){
            for(int i = 2; i <= N; i++){
                int divThree = Integer.MAX_VALUE;
                int divTwo = Integer.MAX_VALUE;
                int subOne = Integer.MAX_VALUE;
                
                // 3으로 나눠지면
                if(i % 3 == 0) divThree = i / 3;
                if(i % 2 == 0) divTwo = i / 2;
                subOne = i-1;

                // 유효한 값들 중에 + 1 한 곳의 값이 min 인 값을 넣음
                if(divThree != Integer.MAX_VALUE) divThree = dp[divThree] + 1; 
                if(divTwo != Integer.MAX_VALUE) divTwo = dp[divTwo] + 1; 
                if(subOne != Integer.MAX_VALUE) subOne = dp[subOne] + 1; 

                int min_num = Math.min(divThree,divTwo);
                min_num = Math.min(min_num, subOne);
                dp[i] = min_num;
            }
        }
        
        System.out.println(dp[N]);
    }
}
