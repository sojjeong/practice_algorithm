"""
https://www.acmicpc.net/problem/1181
알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.

길이가 짧은 것부터
길이가 같으면 사전 순으로
입력
첫째 줄에 단어의 개수 N이 주어진다. (1≤N≤20,000) 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다. 주어지는 문자열의 길이는 50을 넘지 않는다.

출력
조건에 따라 정렬하여 단어들을 출력한다. 단, 같은 단어가 여러 번 입력된 경우에는 한 번씩만 출력한다.

입력
13
but
i
wont
hesitate
no
more
no
more
it
cannot
wait
im
yours

출력
i
im
it
no
but
more
wait
wont
yours
cannot
hesitate
"""

# 길이가 짧은 것부터 정렬
# 길이가 같으면 사전 순으로

# 1. 각 단어의 길이를 먼저 체크 -> 딕셔너리로 묶어주자
# 2. 그 다음 같은 단어끼리 sort

check_cnt = 0
word_cnt = int(input())
word_list = []

while True:
    word = input()
    word_list.append(word)
    check_cnt += 1

    if check_cnt == word_cnt:
        break

dict_by_len = {}

# 단어 길이 별로 묶음
for i in word_list:
    word_len = len(i)
    
    if not word_len in dict_by_len:
        dict_by_len[word_len] = []
    
    dict_by_len[word_len].append(i)

# dictonary 로 만들어 중복 제거
dict_except_durable = {}
for key, value in dict_by_len.items():
    tempdict = dict.fromkeys(value, 0)
    key_list = []
    
    # 중복이 제외된 word dictonary 의 key 값을 list로 만듬
    for temp_key, temp_value in tempdict.items():
        key_list.append(temp_key)    

    # 정렬 뒤 글자 길이 key 에 맞추어 다시 dictonary 로 바꿔줌
    key_list = sorted(key_list)
    dict_except_durable[key] = key_list

# dictionary 의 key 정렬
sorted_key_list = sorted(list(dict_except_durable.keys()))

for key in sorted_key_list:
    for word in dict_except_durable[key]:
        print(word)