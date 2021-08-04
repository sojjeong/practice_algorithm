import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// 2021.08.04 상호의 배틀필드
public class swea1873 {
     
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

        HashMap<Integer, Character> dirKey = new HashMap<Integer, Character>();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int x = 0;
            int y = 0;
            int dir = 0;

            int H = sc.nextInt();
            int W = sc.nextInt();
            sc.nextLine();          // 반드시 해줘야 다음 라인으로 넘어감

            char[][] field = new char[H][W];

            for(int i = 0; i < H; i++){
                field[i] = sc.nextLine().toCharArray();
            }

            int N = sc.nextInt();
            sc.nextLine();
            char[] command = new char[N];
            command = sc.nextLine().toCharArray();

            dirKey.put(1, '^');
            dirKey.put(2, 'v');
            dirKey.put(3, '<');
            dirKey.put(4, '>');
            
            // 전차 시작 위치 찾기
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(field[i][j] == '^' || field[i][j] == '<' || field[i][j] == '>' || field[i][j] == 'v'){
                        x = i;
                        y = j;
                        break;
                    }
                }
            }

            char temp = field[x][y];

            switch(temp){
                case '^':
                    dir = 1;
                    break;
                case 'v':
                    dir = 2;
                    break;
                case '<':
                    dir = 3;
                    break;
                case '>':
                    dir = 4;
                    break;
            }

            for(char cmm :command){
                if(cmm == 'S'){
                    if(dir == 1){
                        // 위
                        int tempX = x;
                        while(true){
                            --tempX;
                            if(tempX < 0 || field[tempX][y] == '#') break;
                            if(field[tempX][y] == '*'){
                                field[tempX][y] = '.';
                                break;
                            }
                        }
                    }else if(dir == 2){
                        // 아래
                        int tempX = x;
                        while(true){
                            ++tempX;
                            if(tempX >= H || field[tempX][y] == '#') break;
                            if(field[tempX][y] == '*'){
                                field[tempX][y] = '.';
                                break;
                            }
                        }
                        
                    }else if(dir == 3){
                        // 왼쪽
                        int tempY = y;
                        while(true){
                            --tempY;
                            if(tempY < 0 || field[x][tempY] == '#') break;
                            if(field[x][tempY] == '*'){
                                field[x][tempY] = '.';
                                break;
                            }
                        }
                    }else if(dir == 4){
                        // 오른쪽
                        int tempY = y;
                        while(true){
                            ++tempY;
                            if(tempY >= W || field[x][tempY] == '#') break;
                            if(field[x][tempY] == '*'){
                                field[x][tempY] = '.';
                                break;
                            }
                        }
                    }
                    continue;
                }
                
                switch(cmm){
                    case 'U':
                        dir = 1;
                        field[x][y] = dirKey.get(dir);    
                        if(x-1 >= 0){
                            if(field[x-1][y] == '.')
                            {
                                field[x][y] = '.';
                                x--;
                                field[x][y] = dirKey.get(dir);
                            }
                        }
                        break;
                    case 'D':
                        dir = 2;
                        field[x][y] = dirKey.get(dir);    
                        if(x+1 < H){
                            if(field[x+1][y] == '.')
                            {
                                field[x][y] = '.';
                                x++;
                                field[x][y] = dirKey.get(dir);    
                            }
                        }
                        break;
                    case 'L':
                        dir = 3;
                        field[x][y] = dirKey.get(dir);    
                        if(y-1 >= 0){
                            if(field[x][y-1] == '.')
                            {
                                field[x][y] = '.';
                                y--;
                                field[x][y] = dirKey.get(dir);
                            }
                        }
                        
                        break;
                    case 'R':
                        dir = 4;
                        field[x][y] = dirKey.get(dir);    
                        if(y+1 < W){
                            if(field[x][y+1] == '.'){
                                field[x][y] = '.';
                                y++;
                                field[x][y] = dirKey.get(dir);
                            }
                        }
                        break;
                }
            }

            field[x][y] = dirKey.get(dir);

            System.out.printf("#%d ", test_case);
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    System.out.printf("%c", field[i][j]);
                }
                System.out.println();
            }
		}

        sc.close();
        sc = null;
    }
}
