package Java.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BOJ11399 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N];
        int[] useTime = new int[N];

        String temp = br.readLine();
        st = new StringTokenizer(temp);
        for(int i = 0; i < N; i++){
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);

        for(int i = 0; i < N; i++){
            if(i == 0) useTime[i] = time[i];
            else useTime[i] = useTime[i-1] + time[i];
        }

        int sum = IntStream.of(useTime).sum();
        System.out.println(sum);
        br.close();
    }
}