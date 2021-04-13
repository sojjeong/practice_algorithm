"""
4834. [파이썬 S/W 문제해결 기본] 1일차 - 숫자 카드
"""

T = int(input())

for test_case in range(1, T + 1):
    N = int(input())
    ai = input()

    arr_ai = []

    for i in ai:
        arr_ai.append(int(i))

    arr_count = []

    for i in range(0, 10):
        cnt = arr_ai.count(i)
        arr_count.append([i,cnt])

    max_cnt = arr_count[0][1]
    max_num = 0

    for i in range(1, 10):
        if max_cnt < arr_count[i][1]:
            max_cnt = arr_count[i][1]
            max_num = i
        if max_cnt == arr_count[i][1] and max_num < i:
            max_num = i

    print(f'#{test_case} {max_num} {max_cnt}')