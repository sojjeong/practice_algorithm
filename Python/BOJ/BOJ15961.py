from sys import stdin

# 회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
N, d, k, c = map(int, stdin.readline().split())

sushi = []

for _ in range(N):
    sushi.append(int(stdin.readline()))

dict_sushi = {}

for i in range(k):
    if sushi[i] not in dict_sushi:
        dict_sushi[sushi[i]] = 1
    else:
        dict_sushi[sushi[i]] += 1

max_length = 0

# dictionary 에 넣고 빼면서 key 갯수 체크 하는 방식으로 접근

for i in range(0, N):
    dict_sushi[sushi[i]] -= 1

    j = i + k

    if j >= N:
        j = (j % N)

    if sushi[j] not in dict_sushi:
        dict_sushi[sushi[j]] = 1
    else:
        dict_sushi[sushi[j]] += 1

    if dict_sushi[sushi[i]] == 0:
        dict_sushi.pop(sushi[i])

    sushi_count = 0
    if c not in dict_sushi:
        sushi_count = len(dict_sushi.keys()) + 1
    else:
        sushi_count = len(dict_sushi.keys())

    max_length = max(max_length, sushi_count)

print(max_length)
