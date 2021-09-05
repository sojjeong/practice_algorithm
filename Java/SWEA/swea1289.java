package Java.SWEA;
import java.util.Scanner;

// 2021.08.02 원재의 메모리 복구하기
class swea1289
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			String target = sc.next();
			int count = 0;
			
			int[] list = new int[target.length()];
			
			for (int i = 0; i < target.length(); i++) {
				list[i] = target.charAt(i) - '0';
			}
			
			while (true) {
				boolean flag = false;
				int index = 0;
				
				for(int i = 0; i < target.length(); i++) {
					if(list[i] == 1) {
						flag = true;
						index = i;
						break;
					}
				}
				
				if(flag == true)
				{
					for (int i = index; i < target.length(); i++) {
						switch (list[i]) {
						case 0:
							list[i] = 1;
							break;
						case 1:
							list[i] = 0;
							break;
						}
					}
					count++;
				}else break;
			}
			
			System.out.printf("#%d %d%n", test_case, count);
		}
	}
}