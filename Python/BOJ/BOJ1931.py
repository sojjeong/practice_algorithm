from sys import stdin

N = int(stdin.readline())

meeting_room = []
max_room = 0

for _ in range(N):
    start, end = map(int, (stdin.readline().split()))
    meeting_room.append((start, end))

# end가 짧은 순으로 정렬 후, start 순으로 다시 정렬
meeting_room.sort(key=lambda x: (x[1], x[0]))

select_meeting = [meeting_room[0]]
index = 0

for i in range(1, len(meeting_room)):
    # 검사하려는 회의실의 start 값이 이미 선택한 회의의 end 값보다 작으면 안됨
    if meeting_room[i][0] < select_meeting[index][1]:
        continue
    else:
        select_meeting.append(meeting_room[i])
        index += 1

print(len(select_meeting))
