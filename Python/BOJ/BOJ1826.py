import sys
from collections import deque
import heapq

n = int(input())
oil_station = []
sort_oil = []

for _ in range(n):
    line = (sys.stdin.readline()).strip()
    a, b = map(int, line.split())    
    oil_station.append((a,b))


L, P = map(int, input().split())

sort_dist = deque(sorted(oil_station, key=lambda x:x[0]))
count = 0

while P < L:
    while sort_dist and sort_dist[0][0] <= P:
        cur = sort_dist.popleft()
        heapq.heappush(sort_oil, -cur[1])
    
    if not sort_oil:
        break    

    P += -(heapq.heappop(sort_oil))
    count += 1

    if P >= L:
        break
   

if P >= L:
    print(count)
else:
    print(-1)