import java.util.Scanner;

// 2021.08.03 달팽이 숫자
public class swea1954 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			arr[0][0] = 1;

			int cnt = 2;
			int row = 0;
			int col = 0;
			int dir = 1;

			// 방향 : 오른쪽1 - 아래2 - 왼쪽3 - 위4
			while(true){
				if(cnt > N*N) break;
				
				switch(dir){
					case 1 : 
						// 다음 칸이 비었는지 검사
						if(col + 1 < N){
							if(arr[row][col+1] == 0){
								++col;
								arr[row][col] = cnt;
								dir = 1;
							} else {
								cnt--;
								dir = 2;
							} 
						} else {
							cnt--;
							dir = 2;
						}
						break;
					case 2 :
						if(row + 1 < N){
							if(arr[row+1][col] == 0) {
								++row;	
								arr[row][col] = cnt;
								dir = 2;
							} else {
								cnt--;
								dir = 3;
							}
						} else {
							cnt--;
							dir = 3;
						}
						break;
					case 3:
						if(col-1 >= 0){
							if(arr[row][col-1] == 0){
								--col;
								arr[row][col] = cnt;
								dir = 3; 
							} else {
								dir = 4;
								cnt--;
							}	
						} else {
							dir = 4;
							cnt--;
						} 
						break;

					case 4:
						if(row-1 >= 0){
							if(arr[row-1][col] == 0) {
								--row;
								arr[row][col] = cnt;
							} else {
								dir = 1;
								cnt--;
							}
						} else {
							dir = 1;
							cnt--;
						} 
						break;
				}
				cnt++;
			}
			System.out.printf("#%d%n", test_case);
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					System.out.printf("%d ", arr[i][j]);
				}
				System.out.println();
			}
		}
		sc.close();;
		sc = null;
    }
}
