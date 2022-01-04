from sys import stdin
n = int(stdin.readline())

dir_list = []
max_x = 0
max_y = 0

for _ in range(6):
    dir, cm = map(int, stdin.readline().split())
    if dir == 1 or dir == 2:
        max_x = max(cm, max_x)
    if dir == 3 or dir == 4:
        max_y = max(cm, max_y)
    dir_list.append(cm)

# 0, 1 인덱스를 곱한 값 + 3,4 인덱스를 곱한 값 = 면적 이란 공식을 찾음
# 만약 두 인덱스의 값이, 각 변의 max 값이면 두 사각형 면적 뺄셈을 해야함

# 0,1
if dir_list[0] in [max_x, max_y] and dir_list[1] in [max_x, max_y]:
    result = (dir_list[0] * dir_list[1]) - (dir_list[3] * dir_list[4])
    result *= n
# 3,4
elif dir_list[3] in [max_x, max_y] and dir_list[4] in [max_x, max_y]:
    result = (dir_list[3] * dir_list[4]) - (dir_list[0] * dir_list[1])
    result *= n
else:
    result = (dir_list[0] * dir_list[1]) + (dir_list[3] * dir_list[4])
    result *= n

print(result)