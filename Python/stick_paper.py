# 삼성 SW Expert Academy 4869 - 종이 붙이기
# 현재의 단계의 종이 모양은
# 이전 단계의 종이 모양들에 10x20을 하나 붙히거나, 
# 두 단계 전의 종이 모양들에 20x20 하나 붙힌 것 과 10x20 두개를 가로로 쌓은 모양을 하나 붙힌 것

T = int(input())

# 3단계까지는 연산을 줄이기 위해 리턴 값 써줌
def stick_paper(step):
    if step == 0:
        return 0
    elif step == 1:
        return 1
    elif step == 2:
        return 3
    else:
        return stick_paper(step-1) + (2 * stick_paper(step-2))


for test_case in range(1, T+1):
    N = int(input())
    N = N // 10
    answer = stick_paper(N)
    print(f'#{test_case} {answer}') 