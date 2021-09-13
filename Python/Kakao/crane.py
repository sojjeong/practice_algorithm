# 2019 카카오 개발자 겨울 인턴십 - 크레인 인형뽑기 게임
# https://programmers.co.kr/learn/courses/30/lessons/64061

def solution(board, moves):
    moving_stack = []
    board_dict = {}         # 행 - key, 열 - value 로 담기 위한 딕셔너리
    len_board = len(board[0])
    answer = 0

    for i in range(0, len_board):
        board_dict[i+1] = []

    for i in range(0, len_board):
        for j in range(0, len_board):
            board_dict[i+1].append(board[j][i])

    for mv in moves:
        move_list = board_dict[mv]
        moving_doll = 0

        # 0이 아닌 값 찾기
        for inx in range(0, len_board):
            if move_list[inx] == 0:
                continue
            else:
                # 옮길 인형 담고
                # 빠진 곳은 0으로 채우기
                moving_doll = move_list[inx]
                board_dict[mv][inx] = 0
                break

        if moving_doll != 0:
            moving_stack.append(moving_doll)

        len_stack = len(moving_stack)
        if len_stack >= 2 and moving_stack[-2] == moving_stack[-1]:
                moving_stack.pop()
                moving_stack.pop()
                answer += 2

    return answer
