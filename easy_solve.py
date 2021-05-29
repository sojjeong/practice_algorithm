A, B = map(int, input().split())

i = 0
j = 1
array = []
stop = False
while stop == False:
    for i in range(0, j):
        array.append(j)
        
        if len(array) == 1000:
            stop = True
            break
    j += 1

re_array = array[A-1:B]

total = 0

for i in re_array:
    total += i

print(total)