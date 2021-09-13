# 정규식을 사용하면 쉽게 풀리는 문제

def solution(new_id):
    answer = ''
    
    # 1. 모든 대문자를 대응되는 소문자로 치환
    new_id = new_id.lower() 

    # 2. 유효한 문자를 제외한 모든 문자 제거
    # 특수문자 : -_.~!@#$%^&*()=+[{]}:?,<>/
    new_id_list = list(new_id)
    symbol = set("~!@#$%^&*()=+[{]}:?,<>/")
    temp = []

    for i in range(0, len(new_id_list)):
        if new_id_list[i] not in symbol:
            temp.append(new_id_list[i])
    
    new_id = ''.join(temp)

    # 3. ... 와 .. 를 . 으로 바꿈, 전부 . 이 될 때까지 반복!
    dots = ["...", ".."]
    
    flag = True
    while flag:
        for dot in dots:
            if dot in new_id:
                new_id = new_id.replace(dot, ".")   # string 이 불변성객체라서 replace 후 다시 받아줘야 함
                flag=True
            else: flag=False

    answer = new_id        
    
    # 4. 아이디의 처음, 마지막에 위치한 . 제거 / 길이가 0이거나 1,2일때 예외처리
    if len(new_id) != 0:
        new_id_list = list(new_id)

        # 처음
        if new_id_list[0] == '.':
            new_id_list.pop(0)

        # 마지막
        if len(new_id_list) != 0:
            if new_id_list[len(new_id_list)-1] == '.':
                new_id_list.pop(len(new_id_list)-1)
    
        new_id = ''.join(new_id_list)
        answer = new_id

            
    # 5. new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
    if len(new_id) == 0:
        new_id = new_id + "a"
        answer = new_id
    
    # 6. new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
    # 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
    if len(new_id) >= 16:
        new_id = new_id[0:15]      
        if new_id[14] == '.':
            new_id = new_id[0:14]
        answer = new_id
    
    # 7. new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
    if len(new_id) <= 2:
        new_id_list = list(new_id)
        repeat = new_id_list[len(new_id_list)-1]
        
        while len(new_id_list) < 3:
            new_id_list.append(repeat)
    
        new_id = ''.join(new_id_list)  
        answer = new_id
    
    return answer


new_id = "................b"
answer = solution(new_id)
print(answer)