import sys

S, E, Q = input().split()

S = float(S.replace(":", "."))
E = float(E.replace(":", "."))
Q = float(Q.replace(":", "."))

user_dict = {}

for line in sys.stdin:
    if len(line) <= 2:
        break
    time, user = line.strip().split()

    if user_dict.get(user):
        user_dict[user].append(float(time.replace(":", ".")))
    else:
        user_dict[user] = []
        user_dict[user].append(float(time.replace(":", ".")))

# ~ S , E ~ Q 기록
count = 0

for key, value in user_dict.items():
    enter_check = False
    out_check = False

    for i in value:
        if 0.0 <= i <= S:
            enter_check = True
            continue
        if E <= i <= Q:
            out_check = True
            break
    
    if enter_check and out_check:
        count += 1

print(count)