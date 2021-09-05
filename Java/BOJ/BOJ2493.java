package Java.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stoken;
        Stack<Integer[]> stack = new Stack<Integer[]>();
        Stack<Integer[]> tempStack = new Stack<Integer[]>();
        Map<Integer, Integer> matchingIndex = new HashMap<Integer, Integer>();
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String str = br.readLine();
        stoken = new StringTokenizer(str);
    
        for(int i = 0; i < N; i++){
            int temp = Integer.parseInt(stoken.nextToken());
            arr[i] = temp;
            stack.push(new Integer[]{temp, i+1});
        }

        while(true){
            if(stack.isEmpty()) break;
            Integer[] out;
            Integer[] top;

            if(tempStack.isEmpty()){
                out = stack.pop();
                if(stack.isEmpty()) break;
                top = stack.peek();

                if(out[0] < top[0]){
                    matchingIndex.put(out[0], top[1]);
                } else {
                    tempStack.push(out);
                }
            } else {
                // temp 스택에 값이 들어가 있을 때
                out = tempStack.pop();
                if(stack.isEmpty()) break;
                top = stack.peek();

                if(out[0] < top[0]){
                    matchingIndex.put(out[0], top[1]);
                } else{
                    tempStack.push(out);
                    tempStack.push(top);
                    stack.pop();
                }
            }   
        }

        for(int i : arr){
            if(matchingIndex.get(i) == null){
                sb.append("0 ");
            } else{
                sb.append(matchingIndex.get(i)).append(" ");
            }
        }
  
        bw.write(sb.toString());
        bw.flush();
        bw.close();;
        br.close();
        br = null;
        bw = null;
    }
}
