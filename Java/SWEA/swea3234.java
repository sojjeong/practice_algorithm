package Java.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea3234 {
    static int N;
    static int[] weight;
    static int[] permuArr;
    static boolean[] isSelected;
    static boolean[] isSubsetSelected;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());

            weight = new int[N];
            permuArr = new int[N];
            isSelected = new boolean[N];
            isSubsetSelected = new boolean[N];
            count = 0;

            String temp = br.readLine();
            st = new StringTokenizer(temp);

            for(int i = 0; i < N; i++){
                weight[i] = Integer.parseInt(st.nextToken());
            }
            permutation(0);
            sb.append("#").append(test_case).append(" ").append(count).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 변수를 매개변수로 넘겨주는게 속도면에서 더 낫다고 한다.
    private static void permutation(int permuArrIdx){
        if(permuArrIdx == N){
            SumFunction(0,0,0);
            return;
        }

        for(int i = 0; i < N; i++){
            if(isSelected[i]) continue;
            
            // 현재 수 선택 되었다 표시
            isSelected[i] = true;
            // 다음 수 선택
            permuArr[permuArrIdx] = weight[i];
            permutation(permuArrIdx+1);

            // 다음 포문에서 선택할 수 있도록
            isSelected[i] = false;
        }
    }

    private static void SumFunction(int cnt, int sumL, int sumR){
        if(cnt == N){
            count++;
            return;
        }
        // 왼쪽 저울에 올리기
        SumFunction(cnt+1, sumL + permuArr[cnt], sumR);
        
        // 오른쪽 저울에 올려진 추 무게가 왼쪽 거보다 작다면
        // 오른쪽에 올리기
        if( sumR + permuArr[cnt] <= sumL){
            SumFunction(cnt+1, sumL,  sumR + permuArr[cnt]);
        }
    }
}
