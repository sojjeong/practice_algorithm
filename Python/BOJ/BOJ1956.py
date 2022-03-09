import sys
v, e = map(int, input().split())

INF = 400 * 10000 + 1

board = [[INF for _ in range(v)] for _ in range(v)]

for i in range(e):
    a, b, weight = map(int, input().split())
    board[a-1][b-1] = weight

for i in range(v):
    board[i][i] = 0

min_result = sys.maxsize

for k in range(v):
    for i in range(v):
        for j in range(v):
            if board[i][j] > board[i][k] + board[k][j]:
                board[i][j] = board[i][k] + board[k][j]

            if i != j != k and board[i][j] != INF and board[j][i] != INF and board[i][k] != INF and board[k][j] != INF and board[j][i] != INF:
                min_result = min(
                    min_result, board[i][j] + board[j][i], board[i][k] + board[k][j] + board[j][i])

if min_result == sys.maxsize:
    print(-1)
else:
    print(min_result)
