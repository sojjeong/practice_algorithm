from sys import stdin
from collections import deque
import re

# 최솟값을 만들기 위해선, + 되는 영역에만 괄호를 치면 됨
# 풀이 : -로만 split 한 후, 첫 수에서 모든 수를 빼면 됨..

l = stdin.readline().rstrip()
num_list = deque(map(int, (re.findall(r'\d+', l))))
symbol_list = deque(re.findall(r'\D+', l))

stack = []
flag = False

while num_list:
    a = num_list.popleft()
    s = ''

    if symbol_list:
        s = symbol_list.popleft()

    if flag:
        stack[-1] += a
    else:
        stack.append(a)

    if s == '-':
        stack.append('-')
        flag = False
    elif s == '+':
        flag = True


result = stack[0]

for i in range(1, len(stack)):
    if stack[i] != '-':
        result -= stack[i]

print(result)
