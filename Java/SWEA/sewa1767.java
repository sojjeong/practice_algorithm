import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class sewa1767 {
    
    static int[][] arr;
    static int N;
    static List<Point> coreList;
    static int min_value;
    static int max_count;
    static Point[] dirP = {new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            min_value = Integer.MAX_VALUE;
            max_count = 0;
            
            arr = new int[N][N];
            int[][] copyArr = new int[N][N];
            coreList = new ArrayList<Point>();

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int temp = sc.nextInt();
                    arr[i][j] = temp;
                    copyArr[i][j] = temp;

                    if(i == N-1 || i == 0 || j == N-1 || j == 0) continue;
                    if(temp == 1){
                        coreList.add(new Point(i,j));
                    }
                }
            }

            DFS(copyArr, 0, 0, 0);
            System.out.println("#" + test_case + " " + min_value);
            
		}

        sc.close();
        
    }

    public static void DFS(int[][] board, int index, int sum, int count){
        if(index == coreList.size()){
            if(count == 0) return;

            if(max_count < count){
                max_count = count;
                min_value = sum;
            }
            else if(max_count == count)
                min_value = Math.min(min_value, sum);
            
            return;
        }

        Point p = coreList.get(index);
        
        for(int i = 0; i < 4; i++){
            int x = p.x;
            int y = p.y;
            
            boolean check = false;

            while(true){
                x = x + dirP[i].x;
                y = y + dirP[i].y;

                // 유효한 인덱스이면 
                if (x >= 0 && x < N && y >= 0 && y < N){
                    if(x == 0 || x == N-1 || y == N-1 || y == 0){
                        if(board[x][y] == 0){
                            check = true;
                            break;
                        } else {
                            check = false;
                            break;
                        }
                    } else if (board[x][y] == 0){
                        check = true;
                    } else {
                        check = false;
                        break;
                    }
                } else {
                    check = false;
                    break;
                }
            }

            if(check){
                x = p.x;
                y = p.y;
                int tempSum = sum;

                while(true){
                    x = x + dirP[i].x;
                    y = y + dirP[i].y;

                    if (x >= 0 && x < N && y >= 0 && y < N){ 
                        board[x][y] = 2;
                        tempSum++;
                    }
                    else break;
                }
                DFS(board, index+1, tempSum, count+1);

                x = p.x;
                y = p.y;

                while(true){
                    x = x + dirP[i].x;
                    y = y + dirP[i].y;

                    if (x >= 0 && x < N && y >= 0 && y < N){ 
                        board[x][y] = 0;
                    }
                    else break;
                }
            }
            else{
                DFS(board, index+1, sum, count);
            }
        }
    }

}
