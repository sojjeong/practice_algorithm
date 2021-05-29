from itertools import permutations


def check(users, banned_id):
    # 튜플의 조합이 banned_id 와 하나라도 맞지 않으면 return false
    for i in range(len(banned_id)):
        # 밴 아이디 길이와 맞지 않으면 return false
        if len(users[i]) != len(banned_id[i]):
            return False
        
        # 글자 돌아가면서 일치하는지 비교
        for j in range(len(users[i])):
            if banned_id[i][j] == '*':
                continue
            elif banned_id[i][j] != users[i][j]:
                return False
    
    return True


def solution(user_id, banned_id):
    # itertools 를 이용한 순열만들기
    # permutations 함수
    answer = 0

    user_permutation = list(permutations(user_id, len(banned_id)))  # 금지시킬 아이디 수 만큼의 순열 생성
    print(user_permutation)

    banned_set = []

    # 문제에서 순서는 상관이 없기 때문에 중복 값 제거해줘야함    
    # check 함수 만들어서 해결함
    for users in user_permutation:
        # 튜플 하나씩 비교
        if not check(users, banned_id):
            continue
        else:
            users = set(users)  # 중복 값 튜플은 추가하지 않기 위해 set으로 만들어 줌
            if users not in banned_set:
                banned_set.append(users)
    
    answer = len(banned_set)
    return answer


user_id = ["frodo", "fradi", "crodo", "abc123", "frodoc"]
banned_id = ["fr*d*", "*rodo", "******", "******"]
answer = solution(user_id, banned_id)
print(answer)