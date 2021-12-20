from sys import stdin

n, m = map(int, stdin.readline().split())
board = []

for _ in range(n):
    board.append(list(map(int, stdin.readline().split())))

# 테트로미노 하나를 회전, 대칭 한 모양으로 슬라이딩 윈도우
tetromino = {
    # 일자
    'hori' : [[True] * 4],
    'verti' : [[True]] * 4,
    
    # 네모
    'square' : [[True, True]] * 2,

    # L
    'L1' : [[True, False]] * 2 + [[True, True]],
    'L2' : [[True]*3, [True, False, False]],
    'L3' : [[True, True]] + [[False, True]] * 2,
    'L4' : [[False, False, True], [True]*3],
    'L5' : [[False, True]] * 2 + [[True, True]],
    'L6' : [[True, False, False], [True]*3],
    'L7' : [[True, True]] + [[True, False]] * 2,
    'L8' : [[True]*3, [False, False, True],], 

    # ㄹ:
    'Z1' : [[True, False], [True, True], [False, True]],
    'Z2' : [[False, True, True], [True, True, False]],
    'Z3' : [[False, True], [True, True], [True, False]],
    'Z4' : [[True, True, False], [False, True, True]],

    # ㅗ
    'O1' : [[False, True, False], [True]*3],
    'O2' : [[True, False], [True, True], [True, False]],
    'O3' : [[True]*3, [False, True, False]],
    'O4' : [[False, True], [True, True], [False, True]],
}

sum = 0

for key, value in tetromino.items():
    row = len(value)
    col = len(value[0])

    for i in range(n - (row - 1)):
        for j in range(m - (col - 1)):
            temp = []
            temp_sum = 0

            for r in range(i, i + row):
                temp.append(board[r][j:j+col])

            for a in range(len(temp)):
                for b in range(len(temp[0])):
                    if value[a][b]:
                        temp_sum += temp[a][b]
            
            sum = max(temp_sum, sum)

print(sum)