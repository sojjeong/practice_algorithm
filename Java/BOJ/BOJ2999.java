package Java.BOJ;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2999 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정인이가 보내는 메시지는 총 N글자
        // R<=C이고, R*C=N인 R과 C를 고른다. 만약, 그러한 경우가 여러 개일 경우, R이 큰 값을 선택한다.
        // 그 다음, 행이 R개고, 열이 C개인 행렬을 만든다.

        /**
         * 그 다음, 행이 R개고, 열이 C개인 행렬을 만든다.
         *
         * 이제 메시지를 행렬에 옮긴다. 첫 번째 행의 첫 번째 열부터 C번째 열까지 메시지를 순서대로 옮긴 뒤,
         * 남은 메시지는 두 번째 행, 세 번째 행,... R번째 행에 첫 번째 행을 채운 방법과 동일한 순서대로 옮긴다.
         *
         * 행렬에 모두 메시지를 옮겼다면, 이 것을 첫 번째 열의 첫 번째 행부터 R번째 행까지 차례대로 읽으면서 다시 받아 적는다.
         * 그 다음에, 두 번째 열, 세 번째 열,..., C번째 열에 쓰여 있는 문자를 첫 번째 열을 읽은 방법과 동일하게 받아적는다.
         */
        String password = br.readLine();
        int R = 1; // 일단 1부터 시작하면서 점차 올려보자
        int N = password.length();
        int maxR = 1;
        int minC = N;

        while(R <= N / R){
            if(N % R == 0){
                maxR = R;
                minC = N / R;
            }
            R++;
        }

        String[] passwordSplit = password.split("");
        String[][] passwordArr = new String[maxR][minC];
        int index = 0;

        for(int j = 0; j < minC; j++){
            for(int i = 0; i < maxR; i++){
                passwordArr[i][j] = passwordSplit[index];
                index++;
            }
        }

        for(int i = 0; i < maxR; i++){
            for(int j = 0; j < minC; j++){
                sb.append(passwordArr[i][j]);
            }
        }

        System.out.println(sb.toString());
        br.close();
    }
}
