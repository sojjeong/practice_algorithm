package Java.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class swea5644 {
    static int M;           // 총 이동 시간
    static int A;           // BC 갯수

    // 이동 방향
    static String[] AMove;
    static String[] BMove;

    static BC[] BCList;     // 충전기 객체 담는 배열

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String str = br.readLine();
            st = new StringTokenizer(str);

            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            // 그 다음 2개의 줄에는 각각 사용자 A와 B의 이동 정보가 주어진다.
            // 한 사용자의 이동 정보는 M개의 숫자로 구성되며, 각각의 숫자는 다음과 같이 매초마다 이동 방향을 의미한다.

            AMove = br.readLine().split(" ");
            BMove = br.readLine().split(" ");

            // 그 다음 줄에는 A개의 줄에 걸쳐 BC의 정보가 주어진다.
            // 하나의 BC 정보는 좌표(X, Y), 충전 범위(C), 처리량(P)로 구성된다.
            // 주어지는 좌표가 1,1 기준이지 -1 하도록
            BCList = new BC[A];

            for (int i = 0; i < A; i++) {
                str = br.readLine();
                st = new StringTokenizer(str);

                BCList[i] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            // 총 이동 시간 동안 수행
            // 첫 위치 A(0,0), B(9,9)

            Info AInfo = new Info(0, 0, 0);
            Info BInfo = new Info(9, 9, 0);

            for(int i = 0; i < M+1; i++){
                // 이동하기 전에 현재 위치하고 있는 곳이 충전기의 범위 안인지 확인
                int AMax = 0;
                int BMax = 0;

                List<Integer> AIndex = new ArrayList<Integer>();
                List<Integer> BIndex = new ArrayList<Integer>();

                for(int j = 0; j < A; j++){
                    // 현재 위치와 충전소간의 거리 구함
                    int ADistance = Math.abs(AInfo.x - BCList[j].x) + Math.abs(AInfo.y - BCList[j].y);
                    int BDistance = Math.abs(BInfo.x - BCList[j].x) + Math.abs(BInfo.y - BCList[j].y);

                    // 충전 범위 안에 들어오면 체크
                    if(ADistance <= BCList[j].c){
                        AIndex.add(j);
                    }

                    if(BDistance <= BCList[j].c){
                        BIndex.add(j);
                    }
                }

                if(AIndex.isEmpty() && BIndex.isEmpty()) {
                    if(i < M){
                        AInfo.move(AMove[i]);
                        BInfo.move(BMove[i]);
                    }
                    continue; // 충전 할 필요가 없으니 아래 검사 X
                } else if(BIndex.isEmpty()){  // A만 확인
                    for(Integer a : AIndex){
                        AMax = Math.max(AMax, BCList[a].p);
                    }
                } else if(AIndex.isEmpty()){  // B만 확인
                    for(Integer b : BIndex){
                        BMax = Math.max(BMax, BCList[b].p);
                    }
                } else{ // 둘다 확인해야 하는 경우
                    int sumMax = 0;
                    
                    // 1 2
                    for(Integer a : AIndex){
                        // 2 3
                        for(Integer b : BIndex){
                            if(a == b){ // 같은 충전소를 쓴다면
                                int chargeA = BCList[a].p / 2;
                                int chargeB = BCList[b].p / 2;

                                if(sumMax < chargeA + chargeB){
                                    AMax = chargeA;
                                    BMax = chargeB;
                                    sumMax = chargeA + chargeB;
                                }
                            } else {
                                int sum = BCList[a].p + BCList[b].p;
                                if(sumMax < sum){
                                    AMax = BCList[a].p;
                                    BMax = BCList[b].p;
                                    sumMax = sum;
                                }
                            }
                        }
                    }
                }

                // 최댓값 충전
                AInfo.charge += AMax;
                BInfo.charge += BMax;

                // 이동! -> 마지막 루프에는 이동 안함
                if(i < M){
                    AInfo.move(AMove[i]);
                    BInfo.move(BMove[i]);
                }
            }

            sb.append("#").append(test_case).append(" ").append(AInfo.charge + BInfo.charge).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class Info {
    int x;
    int y;
    int charge;

    // 상 우 하 좌
    int[] dirX = {0, -1, 0, 1, 0};
    int[] dirY = {0, 0, 1, 0, -1};

    public Info(int x, int y, int charge) {
        this.x = x;
        this.y = y;
        this.charge = charge;
    }
    
    public void move(String s){
        switch (s){
            case "0":   // 아무것도 하지 않음
                break;
            case "1":   // 상
                this.x = this.x + dirX[1];
                this.y = this.y + dirY[1];
                break;
            case "2":   // 우
                this.x = this.x + dirX[2];
                this.y = this.y + dirY[2];
                break;
            case "3":   // 하
                this.x = this.x + dirX[3];
                this.y = this.y + dirY[3];
                break;
            case "4":   // 좌
                this.x = this.x + dirX[4];
                this.y = this.y + dirY[4];
                break;
        }
    }
}

class BC{
    int x;  // x
    int y;  // y
    int c;  // 충전 범위
    int p;  // 처리량

    public BC(int y, int x, int c, int p) {
        this.x = x-1;
        this.y = y-1;
        this.c = c;
        this.p = p;
    }
}