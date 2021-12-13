import sys

n = int((sys.stdin.readline()).strip())
signal = (sys.stdin.readline()).strip()

divide_n = n // 5
signal_list = []

num_dict = {
    "####.##.##.####" : 0,
    ".#..#..#..#..#." : 1,
    "###..#####..###" : 2,
    "###..####..####" : 3,
    "#.##.####..#..#" : 4,
    "####..###..####" : 5,
    "####..####.####" : 6,
    "###..#..#..#..#" : 7,
    "####.#####.####" : 8,
    "####.####..####" : 9,
}

for i in range(0, 5):
    signal_list.append(list("." + signal[i * divide_n : i * divide_n + divide_n] + "."))

result = ""

for i in range(divide_n):
    check = ""
    for j in range(5):
        check += ''.join(signal_list[j][i:i+3])
    
    if check in num_dict:
        result += str(num_dict[check])

print(result)