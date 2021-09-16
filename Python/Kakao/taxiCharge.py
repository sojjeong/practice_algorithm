"""
다익스트라 
플로이드 와샬

차이점 알고 있기
이 문제는 플로이드 와샬로 풀 수 있음 ( 다이나믹 프로그래밍 )
모든 노드에서 다른 모든 노드까지의 최단 경로를 모두 계산

각 단계마다 특정한 노드 k를 거쳐 가는 경우를 확인
- a 에서 b로 가는 최단 거리보다, a에서 k를 거쳐 b로 가는 거리가 더 짧은지 검사
- Dab = min(Dab, Dab + Dkb)
"""

# 실패한 다익스트라
def Dijkstra(n, s, a, b, fares):
    answer = 0
    
    """
    n : 지점 갯수
    s : 출발지
    a : a 의 목적지
    b : b 의 목적지
    fares : [c,d,f] : c와 d를 있는 요금은 f
    """

    # c : list([d, f])
    fares_dict = {}

    # 0번 안쓰기
    fares_list = [[0 for _ in range(n+1)] for _ in range(n+1)]

    print(fares_list)
    
    # 양방향이니까 반대의 경우에도 담아줘야함
    for i in fares:
        fares_list[i[0]][i[1]] = i[2]

    print(fares_list)

    """
    # 다른 정점이랑 연결 되어 있는지 
    for i in range(0, len(fares)):
        if not fares_dict.get(i[0]):
            fares_dict[i[0]] = []
            
        fares_dict[i[0]].append([i[1], i[2]])
    """

    aDistance = [-1]    # 0 번 인덱스 안쓰기
    bDistance = [-1]

    aVisited = [ False ]
    bVisited = [ False ]
    
    # 일직선으로 연결된 경우 최대 200*100,000 의 비용이 발생
    INFINITY = 20000001
    
    for i in range (0, n):
        aDistance.append(INFINITY)
        bDistance.append(INFINITY)
        aVisited.append(False)
        bVisited.append(False)
    
    
    # 시작점과의 거리는 0
    aDistance[s] = 0
    bDistance[s] = 0

    aPath = []
    bPath = []

    minValue = 0
    current = 0

    # a의 목적지까지의 최단 거리
    for i in range(1, a+1):
        # 1단계, 방문하지 않은 정점 중 최소 가중치의 정점 선택
        minValue = INFINITY     
        for j in range(1, a+1):
            if not aVisited[j] and aDistance[j] < minValue:
                minValue = aDistance[j]
                current = j

        aVisited[current] = True
        aPath.append(current)
        
        if current == a:
            break

        # 2단계, current 정점을 경유지로 하여 갈 수 있는 다른 방문하지 않은 정점들에 대한 처리
        for j in range(1, a+1):
            if not aVisited[j] and fares_list[current][j] != 0 and aDistance[j] > minValue+fares_list[current][j]:
                aDistance[j] = minValue+fares_list[current][j]
        
        

        print(aDistance)
    print(aPath)

    
    # b의 목적지까지의 최단 거리
    minValue = 0
    current = 0

    for i in range(1, n+1):
        # 1단계, 방문하지 않은 정점 중 최소 가중치의 정점 선택
        minValue = INFINITY
        for j in range(1, n+1):
            if not bVisited[j] and bDistance[j] < minValue:
                minValue = bDistance[j]
                current = j
                break

        bVisited[current] = True
        bPath.append(current)
        
        # 도착지에 다다르면 break
        if current == b:
            break

        # 2단계, current 정점을 경유지로 해서 갈 수 있는 다른 방문하지 않은 정점들에 대한 distance 갱신
        for c in range(1, n+1):
            if not bVisited[c] and fares_list[current][c] != 0 and bDistance[c] > minValue+fares_list[current][c]:
                bDistance[c] = minValue+fares_list[current][c]

        print(bDistance)
    print(bPath)


    return answer


def Floyd_warshall(n,s,a,b,fares):
    # 가중치를 저장할 2차원 배열
    INFINITY = 20000001
    arr = [[INFINITY for _ in range(n)] for _ in range(n)]


    for i in range(n):
        for j in range(n):
            if i == j:
                arr[i][j] = 0

    # 정점 위치 0부터 시작하기
    for f in fares:
        start = f[0] - 1
        end = f[1] - 1
        weight = f[2]

        # 양방향이니까 두 쪽 다 넣어주기
        arr[start][end] = weight
        arr[end][start] = weight


    for k in range(n):
        for i in range(n):
            for j in range(n):
                arr[i][j] = min(arr[i][k] + arr[k][j], arr[i][j])
                
    
    # k 까지 같이 가는 위치 최솟값 찾기
    min_k = INFINITY
    end = 0

    # 시작점을 제외한 다른 정점들
    v = [i for i in range(n)]
    v.remove(s-1)

    # 각자 타는 케이스도 구해줘야 함
    seperate = arr[s-1][a-1]+ arr[s-1][b-1]

    for k in v:
        if min_k > arr[s-1][k] + arr[k][a-1] + arr[k][b-1]:
            min_k = arr[s-1][k] + arr[k][a-1] + arr[k][b-1]
            end = k

    print(min(min_k, seperate))




# n,s,a,b : 정점갯수, 시작점, a의 목적지, b의 목적지, 간선 가중치
fares = [[2,6,6], [6,3,7], [4,6,7], [6,5,11], [2,5,12], [5,3,20], [2,4,8], [4,3,9]]
answer = Floyd_warshall(6, 4, 5, 6, fares)