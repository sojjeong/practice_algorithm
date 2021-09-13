# 삼성 SW Expert Academy 4866 - 괄호 검사

T = int(input())

for test_case in range(1, T+1):
    sentence = list(input())
    left_bracket = ['{', '(']
    right_bracket = ['}', ')']
    stack = []

    # 일단 왼쪽 괄호는 무조건 push
    # 왼쪽 괄호를 만나면 현재 top이 그것과 짝인지 검사.
    # 안맞는다면 바로 return false
    # 맞는다면 스택에서 pop 하고 다음 괄호 검사

    # 마지막 검사를 위한 플래그
    stop = False
    
    for ch in sentence:
        if ch in left_bracket:
            stack.append(ch)
            continue
        elif ch in right_bracket:
            if len(stack) == 0:
                anser = 0
                stop = True
                break
            else:
                top = stack.pop()
                if ch == ')' and top == '(':
                    continue
                elif ch == '}' and top == '{':
                    continue
                else:
                    answer = 0
                    stop = True
                    break
    
    # 위 for 문에서 예외되는 값을 만나 return 되지 않는 경우
    # 마지막 스택이 다 비었는지 검사 
    if stop == False:
        if len(stack) == 0:
            answer = 1
        else:
            answer = 0

    print(f'#{test_case} {answer}')