from sys import stdin

global count

x = [-1, 0, 1, 0]
y = [0, 1, 0, -1]


def checked(value):
    if 0 <= value < N:
        return True
    else:
        return False


def dfs(board, curX, curY, visited):
    global count

    if visited[curX][curY]:
        return

    visited[curX][curY] = True
    count += 1

    for i in range(4):
        nextX = curX + x[i]
        nextY = curY + y[i]

        if checked(nextX) and checked(nextY) and not visited[nextX][nextY]:
            if board[nextX][nextY] == 1:
                dfs(board, nextX, nextY, visited)


N = int(stdin.readline())

house_map = []

for _ in range(N):
    house_map.append(list(map(int, list(stdin.readline().rstrip()))))


group = []
count = 0
visited = [[False for _ in range(N)] for _ in range(N)]

for i in range(N):
    for j in range(N):
        if not visited[i][j] and house_map[i][j] == 1:
            dfs(house_map, i, j, visited)
            group.append(count)
            count = 0

group.sort()

print(len(group))

for i in group:
    print(i)
