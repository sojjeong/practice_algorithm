# 삼성 SW Expert Academy 4837 - 부분집합의 합

T = int(input())

for test_case in range(1, T+1):
    N, K = map(int, input().split())
    
    subset_A = []
    sum_count = 0
    subset_sum = 0

    # 1부터 12까지의 값을 N개 가진 부분집합
    # K > 79 : 0 출력 (1~12 까지의 합)
    if (K < 79):
        range_list = [ i for i in range(1,13)]
        n = len(range_list)
        temp_list = []

        # 부분집합 만들기
        for i in range(1 << n): 
            for j in range(n): 
                t_f = i & (1 << j)
                if t_f: 
                    temp_list.append(range_list[j])
            if len(temp_list) == N:
                subset_A.append(temp_list)  
            temp_list = []    

        for a in subset_A:
            subset_sum = sum(a)
            if subset_sum == K:
                sum_count += 1

    print(f'#{test_case} {sum_count}')