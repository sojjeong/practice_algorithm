package Java.SWEA;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 2021.08.03 Flatten
class swea1208
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int dump = sc.nextInt();
            List<Integer> arr = new ArrayList<>();
            
            for(int i = 0; i < 100; i++){
                arr.add(sc.nextInt());
            }       

            for(int i = 0; i < dump; i++){
                Collections.sort(arr);    
                arr.set(0, arr.get(0) + 1);
                arr.set(99, arr.get(99) - 1);
            }
            
            Collections.sort(arr);  
            int result = arr.get(99) - arr.get(0);
            System.out.printf("#%d %d%n", test_case, result);

		}

        sc.close();
        sc = null;
        
	}
}