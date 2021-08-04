def solution(s):
    answer = []

    # 문자열을 정렬하기 편한 형태로 바꿔줘야 할 듯! 정규표현식을 이용해도 가능
    s = s[2:-2]

    list_s = s.split('},{')    
    number_list = []

    for ls in list_s:
        number_list.append(list(map(int, ls.split(','))))

    # 각 요소의 갯수로 sort
    sort_list = sorted(number_list, key=lambda x : (len(x)))

    for sl in sort_list:
        for num in sl:
            if num not in answer:
                answer.append(num)

    return answer