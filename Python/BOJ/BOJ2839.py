n = int(input())

a = 3
b = 5

if n % b == 0:
    print(n // b)
else:
    sum_a = a
    while True:
        if sum_a > n:
            print(-1)
            break

        if (n - sum_a) % b == 0:
            print(sum_a // a + (n - sum_a) // b)
            break
        else:
            sum_a += a
            continue