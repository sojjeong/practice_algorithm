from sys import stdin
from collections import deque

global total_candy
total_candy = 0

N = int(stdin.readline())
candy = []

for _ in range(N):
    candy.append(list((stdin.readline()).strip()))


dir_top_down = [(-1, 0), (1, 0),]
dir_left_right = [(0, 1), (0, -1)]
direction = [dir_top_down, dir_left_right]


def BFS(i, j, direciton):
    global total_candy
    q = deque()
    q.append((i, j))
    num = 1

    visited = [[False for _ in range(N)] for _ in range(N)]
    
    while q:
        cur = q.popleft()
        
        if not visited[cur[0]][cur[1]]:
            visited[cur[0]][cur[1]] = True

            for d in direciton:
                x = cur[0] + d[0]
                y = cur[1] + d[1]

                if x >= 0 and x < N and y >= 0 and y < N:
                    if not visited[x][y] and candy[cur[0]][cur[1]] == candy[x][y]:
                        num += 1
                        q.append((x,y))
    
    total_candy = max(total_candy, num)


for i in range(N):
    for j in range(N-1):
        temp = candy[i][j]
        candy[i][j] = candy[i][j+1]
        candy[i][j+1] = temp
        
        for d in direction:
            BFS(i, j, d)
            BFS(i, j+1, d)
        
        # BFS 실행 후 다시 원상복구 해야함
        temp = candy[i][j]
        candy[i][j] = candy[i][j+1]
        candy[i][j+1] = temp


if total_candy == N:
    print(N)
else:
    for i in range(N-1):
        for j in range(N):
            temp = candy[i][j]
            candy[i][j] = candy[i+1][j]
            candy[i+1][j] = temp

            for d in direction:
                BFS(i, j, d)
                BFS(i+1, j, d)
            
            # BFS 실행 후 다시 원상복구 해야함
            temp = candy[i][j]
            candy[i][j] = candy[i+1][j]
            candy[i+1][j] = temp

    print(total_candy)