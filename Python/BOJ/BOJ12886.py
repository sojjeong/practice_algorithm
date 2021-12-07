import sys
from collections import deque
sys.setrecursionlimit(10000000)

# 시간 초과, 재귀 호출이 너무 많음
# 동시에 같은 깊이를 진행하도록 BFS로 하는게 좋음
def dfs(cb, li, check_list):
    if backtracking:
        return

    copy_li = []

    for i in li:
        copy_li.append(i)

    a = max(copy_li[cb[0]], copy_li[cb[1]])
    b = min(copy_li[cb[0]], copy_li[cb[1]])

    a -= b
    b *= 2

    copy_li[cb[0]] = a
    copy_li[cb[1]] = b

    if set(copy_li) in check_list:
        return
    
    count = 0
    for i in copy_li:
        if i == mean:
            count += 1
    
    if count == 3:
        flag = True
        return

    check_list.append(set(copy_li))

    for i in combi:
        if not backtracking:
            dfs(i, copy_li, check_list)


def solve_dfs():
    init_li = [A, B, C]
    
    if ((A+B+C) % 3) == 0:
        check_list = []
        
        for i in combi:
            dfs(i, init_li, check_list)

            if backtracking:
                print(1)
                sys.exit()    
        print(0)
    else:
        print(0)



def bfs():
    q = deque()
    q.append((A, B))
    check[A][B] = True

    while q:
        x, y = q.popleft()
        z = TOTAL - x - y

        if x == y == z:
            print(1)
            return
        
        # 조합
        for a, b in (x, y), (x, z), (y, z):
            if a < b:
                b -= a
                a += a
            elif a > b:
                a -=b
                b += b
            else:
                continue    # 같은 수일 경우
            
            c = TOTAL - a - b
            X = min(a, b, c)
            Y = max(a, b, c)

            # 두 숫자만 체크해도, 어차피 나머지는 TOTAL - 두 수니깐
            if not check[X][Y]:
                q.append((X,Y))
                check[X][Y] = True

    # 큐가 빌 때까지 못 끝냈다는 것은 똑같은 수로 만들 수 없다는 것
    print(0)


def solve_bfs():
    if ((A+B+C) % 3) == 0:
        bfs()
    else:
        print(0)
        return


line = sys.stdin.readline()
A, B, C = map(int, line.strip('\n').split())

# DFS 시도용
mean = (A+B+C) / 3
combi = [[0,1], [0,2], [1,2]]
backtracking = False

# BFS 시도용
TOTAL = A + B + C
check = [[False]*TOTAL for _ in range(TOTAL)]

solve_bfs()