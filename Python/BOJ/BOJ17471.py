# 게리맨더링
from collections import deque
import itertools
import sys


def bfs(combi):
    q = deque([combi[0]])
    sum = 0
    visited = set()
    flag = False

    while q:
        node = q.popleft()

        # 방문하지 않은 노드이면
        if node not in visited:
            sum += population[node]
            visited.add(node)

            # 그 노드에 연결된 다른 정점 확인
            for u in board[node]:
                # 조합 안에 있는지, 이미 방문한 노드가 아닌지 확인
                if u in combi and u not in visited:
                    q.append(u)

    # 조합 수 만큼 while문이 돌았다는 건, 다 방문이 가능한 점이라는 것
    if len(combi) == len(visited): flag = True
    return sum, flag


N = int(input())
population = list(map(int, input().split()))
board = [[] for _ in range(N)]

# 인접 행렬 말고, 인접 리스트로 표현해보기
for i in range(N):
    input_list = list(map(int, input().split()))

    for j in range(1, len(input_list)):
        board[i].append(input_list[j]-1) # 0부터 시작하게..


# 될 수 있는 최고 수 + 1
global minResult
minResult = sys.maxsize

# 두개로 선거구를 나누는 방법 -> 조합으로 두개로 나눈 후, bfs 이용해 각 조합이 연결되어 있는지 확인
# itertools 이용해 조합 구하기 
# 조합이 1 2 3 이라고 생각 했을 때, (1 / 2 3) 과 (2 3 / 1) 이 같으므로 반절만 구함!
for i in range(1, N // 2 + 1 ):
    # range(N) 까지의 수를 i개로 나눔
    combis = list(itertools.combinations(range(N), i))

    for combi in combis:
        # 두 선거구가 연결되어 있는지 확인
        # 조합에서 선택한 값
        sum1, flag1 = bfs(combi)
        
        # 조합에서 선택하지 않은 나머지 값
        sum2, flag2 = bfs([i for i in range(N) if i not in combi])

        # 둘 다 연결이 되어 있다면 true 반환
        if flag1 and flag2:
            if abs(sum1 - sum2) < minResult:
                minResult = abs(sum1 - sum2)
        
if minResult == sys.maxsize:
    print(-1)
else:
    print(minResult)