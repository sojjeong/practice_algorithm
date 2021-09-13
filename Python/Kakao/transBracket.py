from collections import deque

def solution(p):
    answer = ''
    
    # 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
    if len(p) == 0: return answer
    
    # 이미 올바른 괄호 문자열인지 확인
    if isCorrectString(p): return p
    
    total = ""
    total = stepTwo(p, total)
    answer = total

    return answer

    
def isCorrectString(p):
    queue = deque(p)
    stack = []
    
    while queue:
        bracket = queue.popleft()
        
        if bracket == '(':
            stack.append(bracket)
        elif bracket == ')':
            # 비어있지 않고
            if stack:
                # top 이
                if stack[len(stack)-1] == '(': 
                    stack.pop()
                else:
                    return False

    if stack: return False
    return True
    
    
def stepTwo(p, total):
    queue = deque(p)   
    
    if len(p) == 0:     
        return ""
    
    if isCorrectString(p) : return p

    u = ""
    v = ""
    
    leftCount = 0
    rightCount = 0
    
    while True:
        bracket = queue.popleft()
        
        if bracket == '(':
            u += bracket
            leftCount += 1
        else:
            u += bracket
            rightCount += 1
        
        if leftCount == rightCount:
            # 나머지 뒷부분 v
            v = ''.join(list(queue))
            break
        
    if isCorrectString(u):
        # stepThree
        # v에 대해 1단계부터 다시 시작 -> 함수로 분리
        total = u + stepTwo(v, total)
    else:
        # u 에 대해서 새로운 문자열 만들기
        total += stepFour(u, v, total)

    return total


def stepFour(u, v, total):
    # 4.1 빈 문자열에 첫 번째 문자로 '(' 를 붙힘
    paragraph = '('
        
    # 4-2. 문자열 v에 대해 1단계부터 "재귀적으로 수행한 결과" 문자열을 이어 붙입니다.
    # 4-3. ')'를 다시 붙입니다. 
    paragraph += stepTwo(v, total)
    paragraph += ')'
    
    # 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
    reverse = ""
    
    if len(u) != 0:
        u = u[1:len(u)-1]
        for i in u:
            if i == "(":
                reverse += ')'
            else:
                reverse += '('
    
    reverse = paragraph + reverse
    
    # 4-5. 생성된 문자열을 반환합니다.
    return reverse


p = "()))((()"
print(solution(p))