def solution(expression):
    answer = 0
    
    number = []
    symbol = []
    total = []
    origin_total = []
    num_str = ''
    
    for ex in expression:
        if ex not in ['+', '-', '*']:
            num_str += ex
        else:
            number.append(int(num_str))
            symbol.append(ex)
            total.append(int(num_str))
            total.append(ex)
            num_str = ''
    
    # 마지막 숫자
    total.append(int(num_str))
    origin_total = list(total)

    # 가장 큰 수를 만들기 위한 연산자 우선순위 사용자 정의
    symbol_priority = []
    SET_SYMBOLE = list(set(symbol))
    
    if len(SET_SYMBOLE) == 1:
        symbol_priority = [{SET_SYMBOLE[0]:1}]
    elif len(SET_SYMBOLE) == 2:
        symbol_priority = [{SET_SYMBOLE[0]:1, SET_SYMBOLE[1]: 2} , {SET_SYMBOLE[0]:2, SET_SYMBOLE[1]:1}]
    else:
        symbol_priority = [{'+':1, '*':2, '-':3}, {'-':1, '+':2, '*':3},  {'*':1, '+':2, '-':3}, 
          {'+':1, '-':2, '*':3},  {'*':1, '-':2, '+':3},  {'-':1, '*':2, '+':3}]
    
    total_postfix = []

    # 후위표기법으로 변경한 후 계산! 우선순위 고려해서
    # 연산자이면 스택에 push (스택 안에 있는 것 보다 우선순위가 높으면 push 하고 top 변경, 안 높으면 토큰보다 작을 때까지 pop)
    for sp in symbol_priority:
        total = list(origin_total)    # 다시 원상 복구
        top = -1
        symbol_stack = []
        postfix_expression = []

        while len(total) > 0:
            token = total.pop(0)    
            
            # 숫자는 저장한다
            if type(token) == int:
                postfix_expression.append(token)
            else:
                if not symbol_stack: # 비어있다면 일단 넣고
                    symbol_stack.append(token)
                    top += 1
                else: # 비어있지 않다면 우선 순위 비교
                    in_symbol = symbol_stack[top]
                    
                    # 스택 안보다 우선순위가 높으면 push
                    if sp[in_symbol] < sp[token]:
                        symbol_stack.append(token)
                        top += 1
                    else:   # 스택 안 보다 낮으면 pop, 높은 우선순위가 나오거나 빌 때까지 pop
                        while sp[in_symbol] >= sp[token]:
                            if symbol_stack:
                                symbol = symbol_stack.pop()
                                postfix_expression.append(symbol)
                                top -=1
                                if top < 0:
                                    break
                                in_symbol = symbol_stack[top]
                            else:
                                break
                        
                        symbol_stack.append(token)
                        top += 1
        
        # 남은 연산자 pop
        while len(symbol_stack) > 0:
            symbol = symbol_stack.pop()
            postfix_expression.append(symbol)

        print(postfix_expression)
        total_postfix.append(postfix_expression)
    
    print(total_postfix)
    total_result = []
    
    for tp in total_postfix:
        # 후위표기법으로 변경된 식의 값을 스택에 넣다가 연산자를 만나면 앞의 두 값을 pop 해서 계산
        result = []
        while len(tp) > 0:
            # 앞 부분 부터
            token = tp.pop(0)
            
            # 숫자면 넣고
            if type(token) == int:
                result.append(token)
            #연산자를 만나면
            else:   
                b = result.pop()
                a = result.pop()
                c = 0

                if token == '*':
                    c = a*b
                elif token == '-':
                    c = a-b
                elif token == '+':
                    c = a+b
                
                result.append(c)
        
        total_result.append(abs(result[0]))

    print(total_result)
    answer = max(total_result)
    return answer