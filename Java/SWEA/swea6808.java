package Java.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea6808 {
    static boolean[] isSelected;
    static int[] totalCard;
    static int[] Kcard;
    static int[] Icard;
    static Integer[] numbers;
    static int KWin;
    static int KLose;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); 

        for(int test_case = 1; test_case <= T; test_case++){
            KWin = 0;
            KLose = 0;

            totalCard = new int[18];
            Icard = new int[9];
            Kcard = new int[9];
            numbers = new Integer[9];
            isSelected = new boolean[10];

            String temp = br.readLine();
            st = new StringTokenizer(temp);

            // 규영이의 카드
            for(int i = 0; i < 9; i++){
                int num = Integer.parseInt(st.nextToken());
                Kcard[i] = num;
                totalCard[num-1] = num;
            }

            // 인영이는 규영이가 가져간 카드를 제외하고 가져가야한다.
            int index = 0;
            for(int i = 0; i < 18; i++){
                if(totalCard[i] == 0){
                    Icard[index] = i+1;
                    index++;
                }
            }

            // 인영이가 가져간 카드로 순열을 만들어 모든 경우의 수 체크
            permutation(0);
            sb.append("#").append(test_case).append(" ").append(KWin).append(" ").append(KLose).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw = null;
        br.close();
        br = null;
    }

    public static void permutation(int cnt){
        if(cnt == 9){
            // 이렇게 하면 배열을 9! 만큼 만드니까 터짐!! 여기서 계산하게 만들자!
            // Integer[] temp = numbers.clone();
            // permu.offer(temp);

            int KSum = 0;
            int ISum = 0;

            for (int i = 0; i < 9; i++) {
                int k = Kcard[i];
                int ii = numbers[i];

                // 높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고,
                if (k > ii)
                    KSum += (k + ii);
                else
                    ISum += (k + ii);
            }

            // 두 사람의 총점이 같으면 무승부이다.
            if (KSum > ISum)
                KWin++;
            else
                KLose++;

            return;
        }

        // 가능한 모든 수들이 들어있는 배열 모든 원소에 대해 시도
        for(int i = 0; i < 9; i++){         // i : 인덱스!
            if(isSelected[i]) continue;     // 인덱스에 해당하는 수가 사용중인 수면 다음 수로

            numbers[cnt] = Icard[i];
            isSelected[i] = true;

            // 다음 자리 순열 뽑기
            permutation(cnt+1);
            isSelected[i] = false;      // 다음번에서도 이 수를 써야하니까 원래도 되돌리기
        }
    }

    public static void permutation(int cnt, int flag){
        if(cnt == 9){
            int KSum = 0;
            int ISum = 0;

            for (int i = 0; i < 9; i++) {
                int k = Kcard[i];
                int ii = numbers[i];

                // 높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고,
                if (k > ii)
                    KSum += (k + ii);
                else
                    ISum += (k + ii);
            }

            // 두 사람의 총점이 같으면 무승부이다.
            if (KSum > ISum)
                KWin++;
            else
                KLose++;

            return;
        }

        // 가능한 모든 수들이 들어있는 배열 모든 원소에 대해 시도
        for(int i = 0; i < 9; i++){         // i : 인덱스!
            if((flag & 1 << i) != 0) continue; 

            numbers[cnt] = Icard[i];
            permutation(cnt+1, flag | 1 << i);
        }
    }
}
