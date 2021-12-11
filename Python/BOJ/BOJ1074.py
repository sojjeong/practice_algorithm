N, r, c = map(int, input().split())

def divide(n, row, col, start_num):
    global finish
    global visit
    global total
    
    if finish: return

    if n == 2:
        for i in range(row, row + 2):
            for j in range(col, col + 2):
                if i == r and j == c:
                    finish = True
                    total = start_num + visit
                    return

                visit += 1
                
    else:
        middle = n // 2
        middle_row = row + middle
        middle_col = col + middle

        plus_num = (n * n) // 4 

        if r < middle_row and c < middle_col:
            divide(n // 2, row, col, start_num)
        elif r < middle_row and c >= middle_col:
            divide(n // 2, row, col + n // 2, start_num + plus_num)
        elif r >= middle_row and c < middle_col:
            divide(n // 2, row + n // 2, col, start_num + plus_num * 2)
        elif r >= middle_row and c >= middle_col:
            divide(n // 2, row + n // 2, col + n // 2, start_num + plus_num * 3)


global finish
finish = False

global visit
visit = 0

global total
total = 0

n = pow(2, N) // 2
divide(pow(2, N), 0, 0, 0)
print(total)