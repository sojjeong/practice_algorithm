from sys import stdin

while True:
    line = stdin.readline().rstrip()

    if line == '0':
        break

    reverse_line = list(line)
    reverse_line.reverse()
    reverse_line = ''.join(reverse_line)

    if line == reverse_line:
        print("yes")
    else:
        print("no")