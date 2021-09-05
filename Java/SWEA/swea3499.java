package Java.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2021.08.06 swea3499 퍼펙트 셔플
public class swea3499 {
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer token;
		Queue<String> queue1 = new LinkedList<String>();
        Queue<String> queue2 = new LinkedList<String>();
		
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <=T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            token = new StringTokenizer(str);
            
            for(int i = 1; i <= N; i++) {
                if(N % 2 == 1)  // 홀수 일때
                {
                    if( i <= (N / 2 + 1)) 
                        queue1.add(token.nextToken());
                    else{
                        queue2.add(token.nextToken());
                    }
                } else {    // 짝수 일때
                    if(i <= (N / 2))
                        queue1.add(token.nextToken());
                    else{
                        queue2.add(token.nextToken());
                    }
                }
            }
            sb.append("#").append(test_case).append(" ");

            while(!queue1.isEmpty()){
                if(queue2.isEmpty()){
                    sb.append(queue1.poll());
                }
                else{
                    sb.append(queue1.poll()).append(" ").append(queue2.poll()).append(" ");
                }
            }

            sb.append("\n");
        }
        queue1.clear();
        queue2.clear();

        bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		br = null;
		bw = null;
    }
}
