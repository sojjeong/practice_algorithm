tc = int(input())
global max_prob

def dfs(cur_e, labor, order_list, prob):
    global max_prob
    
    # 가지치기 중요
    if emp[cur_e][labor] == 0:
        return
    
    # 현재 값이 max 값보다 작거나 같다면 return
    # 소수가 곱해져서 숫자가 계속 작아지기 때문
    if prob <= max_prob:
        return

    prob *= float(emp[cur_e][labor] / 100)
    re_list = []

    for i in order_list:
        if i != labor:
            re_list.append(i)

    if len(re_list) == 0: 
        max_prob = max(max_prob, prob)
        return
    
    for i in order_list:
        if i != labor:
            dfs(cur_e + 1, i, re_list, prob)


for i in range (tc):
    n = int(input())
    max_prob = -1
    emp = []
    order = [i for i in range(n)]

    for j in range(n):
        emp.append(list(map(int, input().split())))

    for j in range(n):
        if emp[0][j] == 0: continue
        dfs(0, j, order, 1)

    max_prob *= 100
    
    print(f'#{tc} {"%0.6f" % max_prob}')