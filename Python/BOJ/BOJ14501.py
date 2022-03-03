from sys import stdin

N = int(stdin.readline())
meeting = []
day = []
dp = []

for i in range(N):
    time, value = map(int, (stdin.readline().split()))
    day.append(time)
    meeting.append(value)

    # dp 배열 값 초기화
    dp.append(value)


# for 문을 돌며 기준 날짜 이전의 dp 값을 모두 비교해봄, 단 일정이 가능한 것만!
# dp[i] = max(meeting[i] + dp[j](i 날짜 이전의 dp값들), dp[i])

for i in range(N):
    for j in range(i):
        if day[j]-1 < i - j:
            dp[i] = max(meeting[i] + dp[j], dp[i])

max_sum = 0

for i in range(N):
    if i + day[i] - 1 < N:
        max_sum = max(max_sum, dp[i])

print(max_sum)
