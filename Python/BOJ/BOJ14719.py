# 각 기둥 꼭대기 점에서 x 축으로 갔을 때, 
# 장애물을 만나면 물을 받을 수 있는 공간이 생긴다는 것
# 같은 y 축 기둥을 만나면 뻗어나간 x범위 y축 아래에서 0인 공간을 물로채움(뭔가 표시를 함)

# x, y
H, W = map(int, input().split())
block = list(map(int, input().split()))
arr = [[0 for _ in range(W)] for _ in range(H)]

for i in range(W):
    for j in range(block[i]):
        arr[j][i] = 1
    
# print(arr)

# 왼쪽에서 오른쪽으로 가보기
for i in range(W):
    if block[i] == 0: continue

    r = block[i] - 1      # x 
    c = i                 # y

    flag = False

    while True:
        if c + 1 < W:
            if arr[r][c + 1] == 1:  # 기둥이 있다면
                c = c + 1
                flag = True
                break
            else: 
                c = c + 1
        else: break

    # 찾았으면
    if flag:
        for j in range(0, block[i]):
            for h in range(i, c+1):
                if arr[j][h] == 0 :
                    arr[j][h] = -1

# print(arr)   

# 오른쪽에서 왼쪽으로 가보기
for i in range(W-1, -1, -1):
    if block[i] == 0: continue

    r = block[i] - 1      # x 
    c = i                 # y
    flag = False

    while True:
        if c - 1 >= 0:
            if arr[r][c - 1] == 1:  
                c = c - 1
                flag = True
                break
            else: 
                c = c - 1
        else: break

    # 찾았으면
    if flag:
        for j in range(0, block[i]):
            for h in range(i, c-1, -1):
                if arr[j][h] == 0:
                    arr[j][h] = -1

count = 0
for i in range(H):
    count += arr[i].count(-1)

print(count)