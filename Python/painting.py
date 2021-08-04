# 삼성 SW Expert Academy 4836 - 색칠하기

T = int(input())

for test_case in range(1, T+1):
    N = int(input())
    
    """
    0 : x1
    1 : y1
    2 : x2
    3 : y2
    4 : color
    """
    color_red = []
    color_blue = []

    # set은 튜플도 가능
    set_red = set()
    set_blue = set()

    for i in range(0, N):
        temp_list = list(map(int, input().split()))
        
        if temp_list[4] == 1:
            color_red.append(temp_list)
        else:
            color_blue.append(temp_list)

    for red in color_red:
        x1, x2 = red[0], red[2]
        y1, y2 = red[1], red[3]

        for x in range(x1, x2+1):
            for y in range(y1, y2+1):
                set_red.add((x,y))

    for blue in color_blue:
        x1, x2 = blue[0], blue[2]
        y1, y2 = blue[1], blue[3]

        for x in range(x1, x2+1):
            for y in range(y1, y2+1):
                set_blue.add((x,y))
    
    # set에 & , | 연산으로 비교 가능
    same = set_red & set_blue
    answer = len(same)
    print(f'#{test_case} {answer}')