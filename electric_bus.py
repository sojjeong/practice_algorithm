"""
# 삼성 SW Expert Academy 4831 - 전기버스
"""

T = int(input())

for test_case in range(1, T + 1):
    K, N, M = map(int, input().split())
    location = list(map(int, input().split()))
    move = K
    check = True
    answer = 0

    """
    1. 현재 위치에 충전기가 있는지 체크한다.
    2. 이동 가능한 거리 내에 충전기가 존재하는 지 확인한다.
        2-1. 존재한다면 충전하지 않고 진행
        2-2. 존재하지 않는다면 충전하고 이동 가능한 거리를 초기화
    """
    
    # 충전소가 잘못 설립 된 경우
    for i in range(1, len(location)):
        if location[i] - location[i-1] > K:
            answer = 0
            check = False
    
    if check == True:
        for i in range(1, N+1) : 
            if i == N:
                break

            move -= 1

            # 1. 현재 위치에 충전기가 있는지 체크
            if i in location:
                # 2. 이동 가능한 거리 내에 충전기가 존재하는지 확인
                avable_move = i + move

                if len(location) != 1:
                    del location[0]

                    if avable_move >= location[0]:
                        # 2-1. 존재한다면 계속 진행
                        continue
                    else:
                        # 2-2. 존재하지 않는다면 충전하고 이동 가능 거리를 초기화
                        answer += 1
                        move = K
                else :
                    # 마지막 충전소일 때, 가능한 이동 거리 확인 후 충전소 들릴지 결정
                    if avable_move < N:
                        answer += 1
                        move = K
                    else:
                        continue
                    

    print(f'#{test_case} {answer}')