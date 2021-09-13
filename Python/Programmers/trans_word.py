from collections import deque

def solution(begin, target, words):
    answer = 0
    
    # begin -> target 으로 변환하는 가장 짧은 변환과정
    # 한 번에 한 개의 알파벳만 바꿀 수 있음, 변경 된 단어는 words 안에 있어야 함 
    # words 리스트 안에는 무조건 target 단어가 있어야 함
    if target not in words:
        return 0
    
    """
    문자 단위로 단어를 비교해서 변환해야 하는 문자만 먼저 체크 해보기

    각 단어의 위치 별로 
    1. 한 개 알파벳 변환
    2.
        2-1. 변환한 단어가 target 이면 return 
        2-2. 있으면 후보군에 변환한 단어 추가, 원상 복구
        2-3. 없으면 원상복구 후, 다음 for 문 진행
    3. 후보군을 바탕으로 [변환단어, 변환완료된 단어가 제외된 words 리스트] 를 큐에 다시 담음
    4. 큐가 빌 때까지 반복
    """

    if target not in words:
        return 0
    
    key = 0
    split_word = {}
    i = 0
    
    # 문자 단위로 단어를 비교해서 변환해야 하는 문자만 먼저 체크 해보기
    while True:
        if i == len(begin):
            break
            
        split_set = set()    
        for word in words:
            split_set.add(word[i])
            
        split_word[key] = list(split_set)
        key += 1
        i += 1

    split_begin = list(begin)
    split_target = list(target)

    # 초기 큐
    deq = deque()
    deq.append([begin, words])
    INIT_LENGTH = len(words)
    answer = 1

    # BFS
    while deq:
        element = deq.popleft()
        split_begin = list(element[0])    # 한 자리씩 변환하기 위해 쪼갬
        modify_words = element[1]         # 수정하기 전 words 

        # 탐색 단계 증가! 남은 words 수를 단계라고 생각한다
        if len(modify_words) < INIT_LENGTH:
            answer += 1
            INIT_LENGTH = len(modify_words)

        # 위치별로 알파벳 한개씩 바꾸기
        for key, items in split_word.items():
            # 위치 값이 같은 값이면 검사하지 않도록
            if split_begin[key] == split_target[key]:
                continue
            
            candidate = []

            for item in items:
                # 같은 글씨면 검사 x
                if item == split_begin[key]:
                    continue
                
                origin = split_begin[key]
                split_begin[key] = item
                str_begin = ''.join([str(element) for element in split_begin])
                
                if str_begin == target:
                    return answer
                else:    
                    # 변환한 단어가 words 리스트 안에 있으면, 후보군에 추가하고 다시 단어 원상복구
                    if str_begin in modify_words:
                        candidate.append(str_begin)        
                    split_begin[key] = origin
            
            # 후보군을 바탕으로 [변환단어, 변환완료된 단어가 제외된 words 리스트] 를 큐에 다시 담음
            for cd in candidate:
                if cd == target:
                    return answer
                else:
                    modify_words.remove(cd)
                    deq.append([cd, list(modify_words)])
                    modify_words.append(cd) # 복구
    
    return answer


begin = "hit"
target = "cog"
words = ["hot", "dot", "dog", "lot", "log", "cog"]
answer = solution(begin, target, words)
print(answer)