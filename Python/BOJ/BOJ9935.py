from sys import stdin

line = stdin.readline().strip()
boom = stdin.readline().strip()

stack = []

if len(boom) == 1:
    for i in line:
        if i != boom:
            stack.append(i)
    
    if stack:
        print(''.join(stack))
    else:
        print("FRULA")
else:
    # 시간 초과
    """
    while True:
        if boom in line:
            line = line.split(boom)
            line = ''.join(line)
        else:
            break

    if len(line) == 0:
        print("FRULA")
    else:
        print(line)
    """

    stack = []

    for i in line:
        stack.append(i)
        
        if len(stack) >= len(boom) and ''.join(stack[len(stack) - len(boom) : len(stack)]) == boom:
            for _ in range(len(boom)):
                stack.pop()
    
    if stack:
        print(''.join(stack))
    else:
        print("FRULA")
