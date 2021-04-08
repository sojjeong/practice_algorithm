"""
# 삼성 SW Expert Academy [파이썬 S/W 문제해결 기본] 1일차 - 전기버스
A도시는 전기버스를 운행하려고 한다. 전기버스는 한번 충전으로 이동할 수 있는 정류장 수가 정해져 있어서, 중간에 충전기가 설치된 정류장을 만들기로 했다.
버스는 0번에서 출발해 종점인 N번 정류장까지 이동하고, 한번 충전으로 최대한 이동할 수 있는 정류장 수 K가 정해져 있다.
충전기가 설치된 M개의 정류장 번호가 주어질 때, 최소한 몇 번의 충전을 해야 종점에 도착할 수 있는지 출력하는 프로그램을 만드시오.
만약 충전기 설치가 잘못되어 종점에 도착할 수 없는 경우는 0을 출력한다. 출발지에는 항상 충전기가 설치되어 있지만 충전횟수에는 포함하지 않는다.
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