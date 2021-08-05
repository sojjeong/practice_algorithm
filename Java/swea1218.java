package com.ssafy.ws_0805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class swea1218 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("C:\\Users\\Sojjeong\\Downloads\\swea1218.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // switch, if-else -> map 구조로 간단하게 할 수 있을 것!
        Map<Character, Character> bracketMap = new HashMap<Character, Character>();
        
        bracketMap.put('}', '{');
        bracketMap.put('>', '<');
        bracketMap.put(']', '[');
        bracketMap.put(')', '(');

        for(int test_case = 1; test_case <= 10; test_case++)
		{
            int result = 1;
            int size = Integer.parseInt(br.readLine()); 
            String str = br.readLine();
            Stack<Character> stack = new Stack<Character>();

            for(int i = 0; i < size; i++){
                if(result == 0)
                    break;
                
                char temp = str.charAt(i);
                if(temp  == '{' || temp == '[' || temp == '(' || temp == '<')
                    stack.push(temp);
                else{
                    // 선생님 맵 이용해 구현
                    if(stack.isEmpty() || stack.pop().equals(bracketMap.get(temp))){
                        result = 0;
                        break;
                    }

                    if(!stack.isEmpty()){
                        char top = stack.peek();       
                        switch(top){
                            case '{':
                                if(temp == '}')
                                    stack.pop();                                       
                                else {
                                    result = 0;
                                }
                                break;
                            case '[':
                                if(temp == ']')
                                    stack.pop();
                                else {
                                    result = 0;
                                }
                                break;
                            case '(':
                                if(temp == ')')
                                    stack.pop();
                                else {
                                    result = 0;
                                }
                                break;
                            case '<':
                                if(temp == '>')
                                    stack.pop();
                                else {
                                    result = 0;
                                }
                                break;
                        }
                    }
                }

                // stack.clear();	재사용 할꺼면 클리어 해줘야함
            }

            sb.append("#").append(test_case).append(" ");
            sb.append(result).append("\n");

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
        br = null;
        bw = null;
    }
}
