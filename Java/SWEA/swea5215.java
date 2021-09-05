package Java.SWEA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 2021.08.09 햄버거 다이어트
public class swea5215 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static StringTokenizer st;
    static int limitKcal;
    static int N;
    static int[] score;
    static int[] kcal;
    static int maxScore;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i = 1; i <= T; i++){
            // 재료의 수, 제한 칼로리
            String str = br.readLine();
            st = new StringTokenizer(str);
            N = Integer.parseInt(st.nextToken());
            limitKcal = Integer.parseInt(st.nextToken());
            
            score = new int[N];
            kcal = new int[N];
            maxScore = 0;

            // 맛에 대한 점수와 칼로리
            for (int j = 0; j < N; j++){
                str = br.readLine();
                st = new StringTokenizer(str);
                score[j] = Integer.parseInt(st.nextToken());
                kcal[j] = Integer.parseInt(st.nextToken());
            }

            // 주어진 제한 칼로리 이하의 조합중에서 가장 맛에 대한 점수가 높은 햄버거의 점수
            subset(0, 0, 0);
            sb.append("#").append(i).append(" ").append(maxScore).append("\n");
        }

        bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		br = null;
		bw = null;
    }
    
    // 부분집합 구하기 재귀함수
    static void subset(int index, int scoreSum, int kcalSum){       
        if(kcalSum > limitKcal) return;
        
        if(index == N) {
			maxScore = Math.max(scoreSum, maxScore);
            return;
        }

        // 선택 했을 때
        subset(index+1, scoreSum+score[index], kcalSum+kcal[index]);

        // 선택 하지 않았을 때
        subset(index+1, scoreSum, kcalSum);
    }
}

