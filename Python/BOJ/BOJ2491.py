from sys import stdin

N = int(stdin.readline())
array = list(map(int, stdin.readline().split()))

max_len = 1
bigger_count = 1
smaller_count = 1

for i in range(1, len(array)):
    # 증가하면
    if array[i] > array[i-1]:
        bigger_count += 1
        smaller_count = 1
    # 감소하면
    elif array[i] < array[i-1]:
        smaller_count += 1
        bigger_count = 1
    # 같으면
    else:
        bigger_count += 1
        smaller_count += 1

    max_len = max(bigger_count, smaller_count, max_len)

print(max_len)
