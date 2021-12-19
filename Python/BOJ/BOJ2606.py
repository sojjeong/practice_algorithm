from sys import stdin
from collections import deque

n = int(stdin.readline())
m = int(stdin.readline())

visited = [False for _ in range(n)]
network = {}

for i in range(m):
    a, b = map(int, stdin.readline().split())

    if network.get(a):
        network[a].append(b)
    else:
        network[a] = []
        network[a].append(b)
    
    if network.get(b):
        network[b].append(a)
    else:
        network[b] = []
        network[b].append(a)

q = deque()

if network.get(1):
    q.append(1)
    
    while q:
        src = q.popleft()

        if visited[src-1]:
            continue
        
        for value in network[src]:
            q.appendleft(value)
        
        visited[src-1] = True

    print(visited.count(True) - 1)
else:
    print(0)
