package Java.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

// 배열돌리기4
public class BOJ17406 {
    static int[][] board;
    static int N;
    static int M;
    static int A = 10000;
    static int K;
    static Integer[] KNum;
    static boolean[] isSelected;
    static Queue<Integer[]> KPermu = new LinkedList<Integer[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String temp = br.readLine();
        st = new StringTokenizer(temp);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine();
            st = new StringTokenizer(temp);
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * (r-s , c-s) / (r+s, c+s) 크기를 시계 방향으로 한칸씩 돌림
         */

        // 회전 연산이 두 개 이상이면, 연산을 수행한 순서에 따라 최종 배열이 다르다.
        // 각 회전 연산에 대한 순열 필요

        int[] r = new int[K];
        int[] c = new int[K];
        int[] s = new int[K];
        KNum = new Integer[K];
        isSelected = new boolean[K + 1];

        // 1~K 까지 숫자로 순열 만들기
        permutation(0);

        for (int i = 0; i < K; i++) {
            temp = br.readLine();
            st = new StringTokenizer(temp);
            r[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }

        // int[] minA = new int[K]; // 경우의 수가 K 만큼 나오는게 아니니깐..!
        List<Integer> minA = new ArrayList<Integer>();

        // 원본 배열 있어야함! 각 순열마다 원본에서부터 바꿔야 하니까
        int[][] origin = deepCopy(board);

        while (!KPermu.isEmpty()) {
            Integer[] permu = KPermu.poll();
            board = deepCopy(origin);

            A = 10000;

            for (int i = 0; i < permu.length; i++) {

                int rs = r[permu[i] - 1] - s[permu[i] - 1] - 1;
                int re = r[permu[i] - 1] + s[permu[i] - 1] - 1;
                int cs = c[permu[i] - 1] - s[permu[i] - 1] - 1;
                int ce = c[permu[i] - 1] + s[permu[i] - 1] - 1;

                RightRotation(rs, re, cs, ce);
            }

            for (int j = 0; j < N; j++) {
                int result = IntStream.of(board[j]).sum();
                A = Math.min(A, result);
                System.out.println(Arrays.toString(board[j]));
            }
            System.out.println();

            minA.add(A);
        }

        sb.append(Collections.min(minA));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        bw = null;
        br.close();
        br = null;
    }

    // temp 값에 하나 저장해서 그거 밀어주는 형식으로, 인덱스 범위줘서 재귀로도 가능
    private static void RightRotation(int rs, int re, int cs, int ce) {
        if (rs == re || cs == ce)
            return;

        int temp = board[rs][cs]; // rs, cs 에 해당하는 곳의 값을 빼놨으니 아래 순서대로 값 밀어야 함

        // ↑ col start 기준으로 row 를 위로 움직이기
        for (int i = rs; i < re; i++) {
            board[i][cs] = board[i + 1][cs];
        }

        // ← row end 기준으로 col 왼쪽으로 움직이기
        for (int i = cs; i < ce; i++) {
            board[re][i] = board[re][i + 1];
        }

        // ↓ col end 기준으로 row 아래로 움직이기
        for (int i = re; i > rs; i--) {
            board[i][ce] = board[i - 1][ce];
        }

        // → row start 기준으로 col 오른쪽으로 움직이기
        for (int i = ce; i > cs; i--) {
            board[rs][i] = board[rs][i - 1];
        }

        board[rs][cs + 1] = temp;

        RightRotation(rs + 1, re - 1, cs + 1, ce - 1);
    }

    public static void permutation(int cnt) {
        if (cnt == K) {
            Integer[] temp = KNum.clone();
            KPermu.offer(temp);

            // Integer[] temp2 = new Integer[K];
            // for(int i = 0; i < K; i++){
            // if(isSelected[i])
            //
            // }
            return;
        }

        // 가능한 모든 수 시도
        for (int i = 1; i <= K; i++) {
            if (isSelected[i])
                continue; // 사용중인 수면 다음 수로

            KNum[cnt] = i;
            isSelected[i] = true;

            // 다음 자리 순열 뽑기
            permutation(cnt + 1);
            isSelected[i] = false;
        }
    }

    private static int[][] deepCopy(int[][] original) {
        if (original == null)
            return null;
        int[][] result = new int[original.length][original[0].length];

        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, result[i], 0, original[0].length);
        }

        return result;
    }
}
