from collections import deque
import heapq
import sys

N,E = map(int, input().split())
INF = 800001

node = {}
for i in range(N):
    node[i+1] = {}

for _ in range(E):
    line = sys.stdin.readline()
    a,b,c = map(int, line.strip().split())
    node[a][b] = c
    node[b][a] = c

v1, v2 = map(int, input().split())


def dijkstra(src, dest):
    q = []
    weight = [INF for _ in range(N)]
    weight[src-1] = 0

    heapq.heappush(q, [weight[src-1], src])
    
    while q:
        current_distance, current_destination = heapq.heappop(q)

        if weight[current_destination-1] < current_distance:
            continue
        
        for target, value in node[current_destination].items():
            if value + weight[current_destination-1] < weight[target-1]:
                weight[target-1] = value + weight[current_destination-1]
                heapq.heappush(q, [weight[target-1], target])

    return weight[dest-1]


if v1 == 1 and v2 == N:
    result = dijkstra(1, N)
    if result >= INF:
        print(-1)
    else:
        print(result)
elif v1 == 1:
    a = dijkstra(1, v2)
    b = dijkstra(v2, N)

    if a >= INF or b >= INF:
        print(-1)
    else:
        print(a+b)
elif v2 == N:
    a = dijkstra(1, v1)
    b = dijkstra(v1, N)

    if a >= INF or b >= INF:
        print(-1)
    else:
        print(a+b)
else:
    a = dijkstra(1, v1)
    b = dijkstra(v1, v2)
    c = dijkstra(v2, N)
    d = dijkstra(1, v2)
    e = dijkstra(v2, v1)
    f = dijkstra(v1, N)

    if (a >= INF or b >= INF or c >= INF) and (d >= INF or e >= INF or f >= INF):
        print(-1)
    else:
        
        result1 = a + b + c
        result2 = d + e + f
        print(f"result1 : {result1}")
        print(f"result2 : {result2}")

        result = min(result1, result2)
        print(result)