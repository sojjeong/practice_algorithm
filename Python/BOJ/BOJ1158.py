from sys import stdin
from collections import deque

# 1번째 풀이 => 5752ms
n, k = map(int, (stdin.readline().split()))
q = deque()

for i in range(1, n+1):
    q.append(str(i))

order = []
index = 0

while q:
    temp = q.popleft()
    index += 1

    if index == k:
        order.append(temp)
        index = 0
    else:
        q.append(temp)

order_string = '<' + ', '.join(order) + '>'
print(order_string)

##############################################################

# 2번째 풀이 => 76ms
n, k = map(int, (stdin.readline().split()))
q = [i for i in range(1, n+1)]

order = []
index = 0

while q:
    index = (index + k - 1) % len(q)
    order.append(q.pop(index))

order_string = '<' + ', '.join(str(i) for i in order) + '>'
print(order_string)
