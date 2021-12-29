from sys import stdin

N = int(stdin.readline())
A = list(map(int, stdin.readline().split()))


## 이진탐색 : O(NlogN)
DP = [0]
DP2 = [0]

for i in range(len(A)):
    if A[i] > DP2[len(DP2)-1]:
        DP2.append(A[i])
        DP.append(DP[len(DP)-1] + 1)
    else:
        for j in range(len(DP2)):
            if A[i] <= DP2[j]:
                DP2[j] = A[i]
                break

print(DP.pop())


"""
## O(N^2)
DP = [1] * N

for i in range(N):
    for j in range(i):
        if A[i] > A[j]:
            DP[i] = max(DP[i], DP[j] + 1)

print(max(DP))
"""