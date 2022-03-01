# 자주 등장하는 빈도순대로 정렬
# 만약, 수열의 두 수 X와 Y가 있을 때, 
# X가 Y보다 수열에서 많이 등장하는 경우에는 X가 Y보다 앞에 있어야 한다. 
# 만약, 등장하는 횟수가 같다면, 먼저 나온 것이 앞에 있어야 한다.

from sys import stdin
from collections import Counter

N, C = map(int, (stdin.readline().split()))
message = list(map(int, stdin.readline().split()))
message_dict = {}

# 들어온 순서대로 갯수를 체크, 정렬하면 안됨! 먼저 나온 것이 앞에 있어야 한다는 조건을 해침
# 일일히 돌면서 갯수 체크
for i in message:
    if not message_dict.get(i):
        message_dict[i] = 1
    else:
        message_dict[i] += 1

# Counter 사용하는 방법 - 이 문제에서는 Counter 를 사용한 방법이 더 느렸음
message_count = Counter(message)

# 많이 나온 빈도(딕셔너리의 value) 기준으로 sort
sorted_dict = sorted(message_dict.items(), key=lambda x : x[1], reverse=True)

answer = ""
for i in sorted_dict:
    for _ in range(i[1]):
        answer += str(i[0]) + " "

print(answer)