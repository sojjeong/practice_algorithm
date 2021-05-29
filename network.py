def DFS(graph, root, visited):       
    # DFS 함수가 실행될 때, 방문 노드 추가
    visited.append(root)
    
    for i in graph[root]:
        if i in visited:
            continue
        else:
            DFS(graph, i, visited)
    

def solution(n, computers):
    answer = 0
    graph = {}
    i = 1
    
    for cp in computers:
        if i not in graph:
            graph[i] = []
            
        for j in range(len(cp)):
            if i == j+1:
                continue
            else:
                if cp[j] == 1:
                    graph[i].append(j+1)
        i += 1
    
    visited = []

    for key in graph.keys():
        if key not in visited:
            DFS(graph, key, visited)
            # 한 네트워크 방문이 모두 끝나서 DFS가 함수가 종료되면
            answer += 1
        else:
            continue

    return answer


n = 3
computers = [[1, 1, 0], [1, 1, 0], [0, 0, 1]]
# computers = [[1, 1, 0], [1, 1, 1], [0, 1, 1]]
result = solution(n, computers)
print(result)