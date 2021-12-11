import sys

he_is_wrong = "Goldbach's conjecture is wrong."

# "에라토스테네스의 체"를 이용해 소수만 걸러냄
n = 1000000
all_number = [i for i in range(0, n+1)]
prime_number = []

for i in range(2, n+1):
    if all_number[i] != 0:
        prime_number.append(i)

        for j in range(i * 2, n+1, i):
            if all_number[j] % i == 0:
                all_number[j] = 0
    else:
        continue

prime_set = set(prime_number)

while True:
    line = sys.stdin.readline()
    line = int(line.strip())
    if line == 0:
        break
    
    index = 0

    for i in range(len(prime_number)):
        if prime_number[i] > line:
            index = i-1
            break
    
    flag = False

    for i in range(index, index // 2 - 1, -1):
        b = prime_number[i]
        a = line - b

        if a in prime_set:
            print(f'{line} = {a} + {b}')
            flag = True
            break
        else:
            continue
    
    if not flag:
        print(he_is_wrong)
