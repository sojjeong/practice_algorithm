from sys import stdin

T = int(stdin.readline())

for _ in range(T):
    post = [[], []]
    n = int(stdin.readline())
    
    for i in range(2):
        post[i] = list(map(int, (stdin.readline()).strip().split()))
       
    dp = [[0]*n, [0]*n, [0]*n]

    dp[0][0] = post[0][0]
    dp[1][0] = post[1][0]
    
    for i in range(1, n):
        # 자신 + 이전의 다른 행 값 / 자신 + 이전 행 선택하지 않고, 전전행의 max 값
        dp[0][i] = max(post[0][i] + dp[1][i-1], post[0][i] + dp[2][i-1])
        dp[1][i] = max(post[1][i] + dp[0][i-1], post[1][i] + dp[2][i-1])
        dp[2][i] = max(dp[0][i-1], dp[1][i-1])
    
    print(max(dp[0][n-1], dp[1][n-1]))