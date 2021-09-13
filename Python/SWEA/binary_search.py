# 삼성 SW Expert Academy 4839 - 이진 탐색

def binary_search(left, right, person, search_count):
    center = int((left+right)/2)
    count = search_count + 1

    if center == person:
        return count

    if person > center:
        count = binary_search(center, right, person, count)
    elif person < center:
        count = binary_search(left, center, person, count)

    return count


T = int(input())

for test_case in range(1, T+1):
    P, Pa, Pb = map(int, input().split())
    winner = 0

    # 처음 검색 구간
    left = 1
    right = P
    center = int((left+right)/2)

    countA = binary_search(left, right, Pa, 0)
    countB = binary_search(left, right, Pb, 0)

    if countA > countB:
        winner = 'B'
    elif countA < countB:
        winner = 'A'
    elif countA == countB:
        winner = 0

    print(f'#{test_case} {winner}')