package Java.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

// 사칙연산 유효성 검사 
public class swea1233 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Queue<String[]> expression = new LinkedList<String[]>();
        int correct = 0;

        for(int tc = 1; tc <= 10; tc++){
            int N = Integer.parseInt(br.readLine());

            for(int i = 0; i < N; i++){
                String[] tempString = br.readLine().split(" ");
                expression.offer(tempString);
            }

            while(!expression.isEmpty()){
                String[] temp = expression.poll();
                int size = temp.length;

                // 해당 정점의 알파벳, 해당 정점의 왼쪽 자식, 오른쪽 자식의 정점번호가 차례대로 주어진다.
                // 숫자 8이 4번정점에 해당하면 "4(노드번호) 8(갖고 있는 값)"
                // 연산자 '/' 가 2번 정점에 해당하면 두 자식이 각각 숫자인 정점의 노드 번호로 : 2 / 4 5 
                // 1 - 2 3 : 1번 노드에 는 '-' 가 들어있고, 2번 3번 자식을 갖고 있다.00

                // 부모는 무조건 연산자(1 - 2 3)
                // 리프는 무조건 값(86 3)

                if(size == 4){ // (1 - 2 3)
                    if(temp[1].equals("+") || temp[1].equals("-") || temp[1].equals("*") || temp[1].equals("/")){
                        correct = 1;
                    }else{
                        correct = 0;
                        break;
                    }
                } else if(size == 2){ //(86 3) 이런식의 구조
                    if(temp[1].equals("+") || temp[1].equals("-") || temp[1].equals("*") || temp[1].equals("/")){
                        correct = 0;
                        break;
                    } else{
                        correct = 1;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(correct).append("\n");
            expression.clear();
        }

        bw.write(sb.toString());
        bw.flush();
		bw.close();
		br.close();
		br = null;
		bw = null;
    }
}
