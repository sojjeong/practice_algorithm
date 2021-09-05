package Java.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 2021.08.09 암호문1
public class swea1228 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        List<String> list = new LinkedList<String>();
        
        for(int tc = 1; tc <= 10; tc++){
            // 첫 번째 줄 : 원본 암호문의 길이 N ( 10 ≤ N ≤ 20 의 정수)
            int N = Integer.parseInt(br.readLine());
            // 두 번째 줄 : 원본 암호문
            String str = br.readLine();
            st = new StringTokenizer(str);
            for(int i = 0; i < N; i++){
                list.add(st.nextToken());
            }

             // 세 번째 줄 : 명령어의 개수 ( 5 ≤ N ≤ 10 의 정수)
            int M = Integer.parseInt(br.readLine());
           
            // 네 번째 줄 : 명령어 I(삽입) x, y, s
            String[] command = br.readLine().split("I ");
           
            for(int i = 1; i <= M; i++){
                st = new StringTokenizer(command[i]);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                ArrayList<String> subList = new ArrayList<>(y); //initial capability

                for(int j = 0; j < y; j++){
                    subList.add(st.nextToken());
                }   
                list.addAll(x, subList); 
            }
            
            sb.append("#").append(tc).append(" ");
            for(int i = 0; i < 10; i++){
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
            list.clear();
        }

        bw.write(sb.toString());
        bw.flush();
		bw.close();
		br.close();
		br = null;
		bw = null;
    }
}
