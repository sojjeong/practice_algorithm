from sys import stdin

M, N = map(int, (stdin.readline()).split())

board = [1 for _ in range(2 * M - 1)]

for _ in range(N):
    a,b,c = map(int, (stdin.readline()).split())

    for i in range(a, a+b):
        board[i] += 1

    for i in range(a+b, 2 * M - 1):
        board[i] += 2

result = ""
for j in range(M, 2*M-1):
    result += str(board[j]) + " "

for i in range(M - 1, -1, -1):
    print(board[i], end=" ")
    print(result)