# 4835. [파이썬 S/W 문제해결 기본] 1일차 - 구간합

T = int(input())

for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    ai = list(map(int, input().split()))
    
    min_value = 1000000
    max_value = -1000000
    start_value = 0

    while True:
        sum_value = 0
        last = start_value + M 
        
        if last > N:
            break
       
        for i in range(start_value, last):
            sum_value += ai[i]

        if sum_value > max_value:
            max_value = sum_value
        
        if sum_value < min_value:
            min_value = sum_value

        start_value += 1

    print(f'#{test_case} {max_value-min_value}')
