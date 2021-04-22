# 삼성 SW Expert Academy 4843 - 특별한 정렬

T = int(input())

for test_case in range(1, T + 1):
    N = int(input())
    ai = list(map(int,input().split()))
    sort_ai = sorted(ai)

    answer = []
    index_flag = True
    i = 0
    j = 1

    # 가장 큰 수, 가장 작은 수, 2번째 큰 수, 2번째 작은 수...
    while True:
        if index_flag:
            answer.append(sort_ai[N-j])
            index_flag = False
            j += 1
        else:
            answer.append(sort_ai[i])
            index_flag = True
            i += 1

        if len(answer) == 10:
            break

    print(f'#{test_case}', end=" ")
    for number in answer:
        print(number, end=" ")
    print()