# 탐색할 필요가 없었는데....탐색하려고 짜본 dfs..
def dfs(crt_num, finger_num, length, move_stack):
    # dx,dy 상하좌우 검사해서 가장 가까운 손가락으로 그 위치로 이동하도록
    dx = [0, 1, 0, -1]
    dy = [-1, 0, 1, 0]
    
    # 탐색용 배열 4x3
    search = [[1,2,3], [4,5,6], [7,8,9], ['*',0,'#']]
    
    crt_index = [(i,j) for i in range(4) for j in range(3) if search[i][j]==crt_num]
    
    # 현재 탐색을 시작하는 위치의 인덱스
    x,y = crt_index[0][0], crt_index[0][1]
    
    for i in range(0,3):
        search_x = x+dx[i]
        search_y = y+dy[i]
        
        # 인덱스 유효성 검사
        if 0 <= search_x <= 3 and 0 <= search_y <= 2:
            # 방문하지 않은 곳이면
            if search[search_x][search_y] not in move_stack:
                move = search[search_x][search_y]
                # 방문 했다고 표시
                move_stack.append(move)
                # 거리 증가
                length += 1
                # 이동한 곳이 손가락의 현재 숫자와 같은가
                if move == finger_num:
                    return length
                # 아니면 다시 탐색
                else:
                    dfs(move, finger_num, length, move_stack)
            # 방문한 곳이면
            else:
                continue
        # 인덱스를 벗어난 곳이면
        else:
            continue
    
    return length
    

def solution(numbers, hand):
    crt_r = '*'
    crt_l = '#'     
    answer = ''
    
    # 탐색용 배열 4x3
    search = [[1,2,3], [4,5,6], [7,8,9], ['*',0,'#']]
    
    for crt_num in numbers:
        if crt_num in [1,4,7]:
            answer += 'L'
            crt_l = crt_num
        elif crt_num in [3,6,9]:
            answer += 'R'
            crt_r = crt_num
        elif crt_num in [2,5,8,0]:
            crt_index = [(i,j) for i in range(4) for j in range(3) if search[i][j]==crt_num]
            l_index = [(i,j) for i in range(4) for j in range(3) if search[i][j]==crt_l]
            r_index = [(i,j) for i in range(4) for j in range(3) if search[i][j]==crt_r]
            
            # 현재 탐색을 시작하는 위치의 인덱스
            x,y = crt_index[0][0], crt_index[0][1]
            lx, ly = l_index[0][0], l_index[0][1]
            rx, ry = r_index[0][0], r_index[0][1]
    
            dir_l = abs(x-lx) + abs(y-ly)
            dir_r = abs(x-rx) + abs(y-ry)
            
            if dir_l == dir_r:
                if hand == 'right':
                    crt_r = crt_num
                    answer += 'R'
                elif hand == 'left':
                    crt_l = crt_num
                    answer += 'L'
            elif dir_l < dir_r:
                crt_l = crt_num
                answer += 'L'
            elif dir_r < dir_l:
                crt_r = crt_num
                answer += 'R'
            
    return answer