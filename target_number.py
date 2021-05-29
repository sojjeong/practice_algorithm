def DFS(stack, i, numbers, target, answer):
    if i == len(numbers):
        total = 0
        for i in stack:
            total += i
        if total == target:
            answer[0] = answer[0] + 1
        return 
    
    elif i < len(numbers):
        a = numbers[i]
        for sb in symbole:
            stack.append(sb*a)
            i += 1
            DFS(stack, i, numbers, target, answer)
            stack.pop()
            i -= 1


def solution(numbers, target):
    answer = [0]    # global 하게 넘겨주려면 객체 형태여야 함
    
    global symbole
    symbole = [-1, 1]
    stack = []
    DFS(stack, 0, numbers, target, answer)
    
    return answer[0]


numbers = [1, 1, 1, 1, 1]
target = 3
result = solution(numbers, target)
print(result)