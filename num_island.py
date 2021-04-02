"""
백준 4963번 섬의 갯수
https://www.acmicpc.net/problem/4963 
정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.
한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 
두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.
둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
입력의 마지막 줄에는 0이 두 개 주어진다.

입력
1 1
0

2 2
0 1
1 0

3 2
1 1 1
1 1 1

5 4
1 0 1 0 0
1 0 0 0 0
1 0 1 0 1
1 0 0 1 0

5 4
1 1 1 0 1
1 0 1 0 1
1 0 1 0 1
1 0 1 1 1

5 5
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1

0 0

출력
0
1
1
3
1
9
"""
from collections import deque

 # 오른쪽 위부터 시계 방향
check_x = [-1, 0, 1, 1, 1, 0, -1, -1]
check_y = [-1, -1, -1, 0, 1, 1, 1, 0]

while True:
    w, h = map(int, input().split())  
    if h == 0 and w == 0 : 
        break

    total_island = []
    for i in range(0, h):
        island = input()
        island = list(map(int,island.split()))
        total_island.append(island)
    
    q = deque()
    count = 0

    # 탐색 시작
    for i in range(h):
        for j in range(w):
            if total_island[i][j] == 1:
                q.append((i,j))     # 첫 시작점. 검사하려는 곳의 인덱스
                total_island[i][j] = 0  # 다시 방문하지 않도록 0으로 

                while(q):
                    current = q.popleft()
                    x = current[0]
                    y = current[1]

                    # 검사하려는 인덱스
                    for dir in range(0, 8):
                        dir_x = x + check_x[dir]    
                        dir_y = y + check_y[dir]

                        # 인덱스 넘어가지 않도록 예외 처리
                        if 0 <= dir_x < h and 0 <= dir_y < w:
                            if total_island[dir_x][dir_y] == 1:
                                q.append((dir_x, dir_y))    # 방문해야 할 곳의 인덱스를 q에 삽입
                                total_island[dir_x][dir_y] = 0  # 다시 방문하지 않도록 0으로

                # 큐 방문이 모두 끝나면
                count += 1

    print(count)               