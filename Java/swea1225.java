package com.ssafy.ws_0805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea1225 {
    // switch, if-else -> map 구조로 간단하게 할 수 있을 것!
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("C:\\Users\\Sojjeong\\Downloads\\swea1225.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer token;
        Queue<Integer> q = new LinkedList<Integer>();

        while(true){
            String Temp;
            if((Temp = br.readLine()) == null)
                break;

            int T = Integer.parseInt(Temp);
            
            String str = br.readLine();
            token = new StringTokenizer(str);
            
            for(int i = 0; i < 8; i++){
                q.offer(Integer.parseInt(token.nextToken()));
            }

            int cycle = 1;

            while(true){
                int num = q.peek() - cycle;
                if(num <= 0){
                    q.poll();
                    q.offer(0);
                    break;
                } else {
                    q.poll();
                    q.offer(num);
                }
                ++cycle;
                if(cycle > 5) cycle = 1;
            }

            sb.append("#").append(T).append(" ");
            for(int num: q){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            q.clear();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
        br = null;
        bw = null;
    }
}
