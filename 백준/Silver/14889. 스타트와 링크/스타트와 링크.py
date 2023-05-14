from sys import stdin
global diff_min
n = 0
global visited
def DFS(index, size):
    global diff_min
    if size > n / 2:
        start,link = 0,0
        for pick_idx in range(0,n):
            for player_idx in range(0,n):
                if visited[pick_idx] == True and visited[player_idx] == True:
                    start += int(matrix[pick_idx][player_idx])
                if visited[pick_idx] == False and visited[player_idx] == False:
                    link += int(matrix[pick_idx][player_idx])

        if diff_min > abs(start-link):
            diff_min = abs(start-link)
        return

    for node_index in range(index, n):
        visited[node_index] = True
        DFS(node_index + 1, size + 1)
        visited[node_index] = False

if __name__ == '__main__':
    n = int(stdin.readline())

    matrix = []
    for i in range(0, n):
        input_array = stdin.readline().split(" ")
        matrix.append(input_array)

    diff_min = 10 ** 100
    visited = [False] * (n + 1)
    DFS(1, 1)
    print(diff_min)