def DFS(matrix, start):
    visited[start] = 1
    print(start, end= " ")
    for i in range(1,N+1):
        if matrix[start][i] == 1 and visited[i] == 0:
            DFS(matrix, i)


def BFS(matrix, start):
    queue = [start]
    visited = [0]*(N+1)
    visited[start] = 1

    while queue:
        node = queue.pop(0)
        print(node, end = " ")
        for i in range(N+1):
            if visited[i] == 0 and matrix[node][i] == 1:
                queue.append(i)
                visited[i] = 1
    

N, M, s = map(int, input().split())
matrix = [[0]*(N+1) for i in range(N+1)]

for i in range(M):
    start, end = map(int, input().split())
    matrix[start][end], matrix[end][start] = 1, 1

visited = [0]*(N+1)

DFS(matrix, s)
print()
BFS(matrix, s)