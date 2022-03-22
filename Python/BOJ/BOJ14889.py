from sys import stdin
from itertools import combinations

N = int(stdin.readline())

athlete = [i for i in range(N)]
stat = [list(map(int, (stdin.readline().split(' ')))) for _ in range(N)]

# 조합 전체를 구하는 방식
min_result = 100000000
combi = list(combinations(athlete, int(N/2)))

i = 0
j = len(combi)-1

for k in range(0, len(combi)//2):
    Ateam = 0
    Bteam = 0

    for m in range(len(combi[i])):
        for n in range(m, len(combi[i])):
            if m != n:
                si = combi[i][m]
                sj = combi[i][n]

                Ateam += stat[si][sj]
                Ateam += stat[sj][si]

    for m in range(len(combi[j])):
        for n in range(m, len(combi[j])):
            if m != n:
                si = combi[j][m]
                sj = combi[j][n]

                Bteam += stat[si][sj]
                Bteam += stat[sj][si]

    min_result = min(min_result, abs(Ateam - Bteam))
    i += 1
    j -= 1

    if i >= j:
        break

print(min_result)

# DFS, 백트랙킹으로 개선해야함
