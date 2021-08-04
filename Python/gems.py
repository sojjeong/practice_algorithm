def solution(gems):
    # gems : 보석 이름이 저장된 배열
    # answer : 가장 짧은 구간 [시작, 끝] 같은 범위가 있다면, 시작 번호가 더 작은 값 return
    # 특정 범위의 물건들을 모두 싹쓸이 구매 
    # ex : (1,2,3,4 o) (4,5,6,7 o) (3,4,6,7 5를 건너띄므로 x)
    # 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매
    answer = []
    
    # 보석이 몇 종류 있는지 먼저 알아내기
    kind_of_gems = set(gems)
    len_kind_of_gems = len(kind_of_gems)
    
    # 요소가 한개거나 모두 같은 요소거나
    if len_kind_of_gems == 1:
        answer = [1,1] 
        return answer
    
    # 모두 다른 요소일 때
    if len_kind_of_gems == len(gems):
        answer = [1, len(gems)]
        return answer
    
    # 그 외
    start = 0
    last = 0
    list_range = []
    
    for i in range (0, len(gems)): 
        # 검사해야할 남은 리스트가 종류보다 적으면 스탑
        if len(gems) - i < len_kind_of_gems:
            break
        else:
            temp_gems = set(gems)
            for j in range(i, len(gems)):
                if gems[j] in temp_gems:
                    if len_kind_of_gems == len(temp_gems):
                        start = j
                        temp_gems.remove(gems[j])
                    else:
                        temp_gems.remove(gems[j])
                        last = j
                else:
                    if len(temp_gems) != 0:
                        continue
                        
            if len(temp_gems) == 0:
                list_range.append([start, last])
            
    scope = 100000
    for li in list_range:
        if scope > li[1] - li[0]:
            scope = li[1] - li[0]
            start = li[0]
            last = li[1]

    answer  = [start+1, last+1]
    
    return answer


def solution2(gems):
    answer = []
    
    # 보석이 몇 종류 있는지 먼저 알아내기
    kind_of_gems = set(gems)
    len_kind_of_gems = len(kind_of_gems)
    
    # 요소가 한개거나 모두 같은 요소거나
    if len_kind_of_gems == 1:
        answer = [1,1] 
        return answer
    
    # 모두 다른 요소일 때
    if len_kind_of_gems == len(gems):
        answer = [1, len(gems)]
        return answer
    
    # 그 외
    start = 0
    last = 0
    list_range = []

    iter_start = 0

    while iter_start < len(gems) - len_kind_of_gems:
        check1 = False
        temp_gems = set(gems)
        for i in range (iter_start, len(gems)):
            if gems[i] in temp_gems:
                temp_gems.remove(gems[i])
                if len(temp_gems) == 0:
                    last = i
                    check1  = True
                    break
        if check1 == False:
            break
        check2 = False
        temp_gems = set(gems)
        for i in range (iter_start, last + 1):
            j = last + iter_start - i
            if gems[j] in temp_gems:
                temp_gems.remove(gems[j])
                if len(temp_gems) == 0:
                    start = j
                    check2  = True
                    break
        if (check1 == True) and (check2 == True):
            list_range.append([start, last])
        if (check2 == False):
            break
        iter_start = start + 1

    scope = 100000
    for li in list_range:
        if scope > li[1] - li[0]:
            scope = li[1] - li[0]
            start = li[0]
            last = li[1]

    answer  = [start+1, last+1]
    
    return answer


def solution3(gems):
    answer = []
    
    # 보석이 몇 종류 있는지 먼저 알아내기
    kind_of_gems = set(gems)
    len_kind_of_gems = len(kind_of_gems)
    len_gems = len(gems)
    
    # 요소가 한개거나 모두 같은 요소거나
    if len_kind_of_gems == 1:
        answer = [1,1] 
        return answer
    
    # 모두 다른 요소일 때
    if len_kind_of_gems == len_gems:
        answer = [1, len_kind_of_gems]
        return answer

    # 슬라이딩을 하는데 딕셔너리 이용함
    # 처음만 슬라이딩 하고, 그 이후 부터는 다음 슬라이딩 범위의 앞, 뒤 인덱스에 해당하는 요소가
    # 몇개가 들어오고 몇개가 빠지는지 체크해보기
    # 슬라이딩 범위는 가변적으로 하도록
    check_dict = {}

    start, last = 0, len_kind_of_gems
    restart = 0
    reslide = len_kind_of_gems

    isStartSlide = True

    while True:
        # 첫슬라이드 지정
        if isStartSlide:
            start_slide = gems[start:last]
            for key in start_slide:
                if key in check_dict:
                    check_dict[key] += 1
                else:
                    check_dict[key] = 1

            if len(check_dict) == len_kind_of_gems:
                answer = [start+1, last]
                return answer
            
            isStartSlide = False

        start_gem = gems[start]
        last_gem = gems[last]
        
        if start_gem in check_dict:
            check_dict[start_gem] -= 1

            if check_dict[start_gem] == 0:
                del check_dict[start_gem]

        if last_gem in check_dict:
            check_dict[last_gem] += 1
        else:
            check_dict[last_gem] = 1

        if len(check_dict) == len_kind_of_gems:
            answer = [start+2, last+1]
            return answer
        else:
            start += 1
            last += 1
        
        # 첫 슬라이드 범위 재 지정
        if last == len_gems:
            reslide += 1
            start = restart
            last = reslide
            isStartSlide = True
            check_dict = {}


    
def solution4(gems):
    answer = []
    
    # 보석이 몇 종류 있는지 먼저 알아내기
    kind_of_gems = set(gems)
    len_kind_of_gems = len(kind_of_gems)
    len_gems = len(gems)
    
    # 요소가 한개거나 모두 같은 요소거나
    if len_kind_of_gems == 1:
        answer = [1,1] 
        return answer
    
    # 모두 다른 요소일 때
    if len_kind_of_gems == len_gems:
        answer = [1, len_kind_of_gems]
        return answer

    # 처음만 슬라이딩 하고, 그 이후 부터는 다음 슬라이딩 범위의 앞, 뒤 인덱스에 해당하는 요소가 몇개가 들어오고 몇개가 빠지는지 체크해보기
    # 슬라이딩 범위는 가변적으로 하도록
    check_dict = {}

    start_slide = gems[:len_kind_of_gems]
    right, left = 0, len_kind_of_gems

    for key in start_slide:
        if key not in check_dict:
            check_dict[key] = 1
        else:
            check_dict[key] += 1

    if len(check_dict) == len_kind_of_gems:
        answer = [1, len_kind_of_gems]
        return answer

    while True:
        # 다음 요소 하나씩 넘어가면서 체크
        last_gem = gems[left]

        if last_gem in check_dict:
            check_dict[last_gem] += 1
        else:
            check_dict[last_gem] = 1

        # 모든 보석이 포함되어있는지 확인
        if len(check_dict) == len_kind_of_gems:
            break
        else:
            left += 1
            if left == len_gems:
                break

    
    # 앞 요소부터 체크
    while True:
        start_gem = gems[right]

        if check_dict[start_gem] == 1:
            answer = [right+1, left+1]
            return answer
        else:
            check_dict[start_gem] -= 1
            right += 1


def solution5(gems):
    answer = []
    
    # 보석이 몇 종류 있는지 먼저 알아내기
    kind_of_gems = set(gems)
    len_kind_of_gems = len(kind_of_gems)
    len_gems = len(gems)
    
    # 요소가 한개거나 모두 같은 요소거나
    if len_kind_of_gems == 1:
        answer = [1,1] 
        return answer
    
    # 모두 다른 요소일 때
    if len_kind_of_gems == len_gems:
        answer = [1, len_kind_of_gems]
        return answer

    # 처음만 슬라이딩 하고, 그 이후 부터는 다음 슬라이딩 범위의 앞, 뒤 인덱스에 해당하는 요소가 몇개가 들어오고 몇개가 빠지는지 체크해보기
    # 슬라이딩 범위는 가변적으로 하도록
    # 구간이 여러개 라면 가장 짧은 것, 만약 가장 짧은 구간이 여러 개라면 시작 진열대 번호가 가장 작은 구간을 return 합니다.
    check_dict = {}
    save_stack = []

    start_slide = gems[:len_kind_of_gems]
    right, left = 0, len_kind_of_gems

    for key in start_slide:
        if key not in check_dict:
            check_dict[key] = 1
        else:
            check_dict[key] += 1

    if len(check_dict) == len_kind_of_gems:
        answer = [1, len_kind_of_gems]
        return answer
    
    # 조건을 충족하는 범위가 완성이 되더라도, 그 다음 요소를 기준으로 다시 구간을 구해서 구간이 여러개가 나오는지에 대해서 구해줘야 함
    restart = False

    while True:
        # 구간 다시 구하기
        if restart:
            check_dict = {}
            last = right + len_kind_of_gems
            # 길이에 맞는 구간을 구할 수 있는지
            if last <= len_gems:
                start_slide = gems[right:last]
                restart = False
                left = last-1

                for key in start_slide:
                    if key not in check_dict:
                        check_dict[key] = 1
                    else:
                        check_dict[key] += 1
                
                if len(check_dict) == len_kind_of_gems:
                    save_stack.append((right,left))
                    right = left+1
                    restart = True
            else:
                break
        else:
            if left < len_gems:
                # 슬라이드의 다음 요소 하나씩 넘어가면서 체크
                last_gem = gems[left]

                if last_gem in check_dict:
                    check_dict[last_gem] += 1
                else:
                    check_dict[last_gem] = 1

                # 모든 보석이 포함되어있는지 확인하고, 구간을 저장해준 뒤, 그 다음 슬라이드 검사하도록 구현해야함
                if len(check_dict) == len_kind_of_gems:
                    save_stack.append((right, left))
                    right = left+1
                    restart = True
                else:
                    left += 1
            else:
                break

    resave_stack = []
    # 앞 요소부터 체크
    for a in save_stack:
        right = a[0]
        left = a[1]
        start_slide = gems[right:left+1]
        
        check_dict = {}

        for key in start_slide:
            if key not in check_dict:
                check_dict[key] = 1
            else:
                check_dict[key] += 1

        while True:
            start_gem = gems[right]

            if check_dict[start_gem] == 1:
                resave_stack.append((right, left))
                break
            else:
                check_dict[start_gem] -= 1
                right += 1

    abs_stack = []

    for i in resave_stack:
        range = abs(i[0] - i[1])
        abs_stack.append(range)

    min_index = abs_stack.index(min(abs_stack))
    answer = [resave_stack[min_index][0]+1, resave_stack[min_index][1]+1]
    return answer


def final_solution(gems):
    TYPE_NUM = len(set(gems)) # 종류개수
    GEM_NUM = len(gems) # 진열대 길이
    answer = []
    start, end = 0, 0
    DIST, INDEX = 0, 1 # 구간, index
    # 초기값
    cur_shop = {gems[0]: 1}
    while start < GEM_NUM and end < GEM_NUM:
        if len(cur_shop) < TYPE_NUM: # 아직 전체 모음 아님
            end += 1
            if end == GEM_NUM:
                break
            cur_shop[gems[end]] = cur_shop.get(gems[end], 0) + 1    
            # dict.get(key, default) key가 없으면 default 값 value 로 설정
        else:
            answer.append((end-start, [start+1, end+1]))    # 길이, [시작 인덱스, 끝 인덱스]
            cur_shop[gems[start]] -= 1
            if cur_shop[gems[start]] == 0:
                del cur_shop[gems[start]]
            start += 1
    answer = sorted(answer, key=lambda x: (x[DIST], x[INDEX]))
    return answer[0][INDEX]


if __name__ == '__main__':
    # gems = ["AA", "AB", "AC", "AA", "AC"]
    # gems = ["ZZZ", "YYY", "NNNN", "YYY", "BBB"]
    # gems = ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "DIA", "SAPPHIRE"]
    gems = ["A","C","A","A","B","A","B","C"] # 기댓값 [6,8]
    answer = final_solution(gems)
    print(answer)