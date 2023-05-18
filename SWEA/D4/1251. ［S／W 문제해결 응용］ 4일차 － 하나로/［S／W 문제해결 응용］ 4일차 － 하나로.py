import math
case = int(input())

def get_length(x1, y1, x2, y2):
    return (x1-x2)**2 + (y1-y2)**2

def tunneling(x_axis, y_axis, e):
    length = len(x_axis)
    visited = [False] * length
    visited[0] = True

    dist = [((x_axis[i] - x_axis[0])**2 + (y_axis[i] - y_axis[0])**2) for i in range(1, length)]
    dist.insert(0, 0)
    for i in range(1, length-1):
        min_value = 10**100
        min_idx = -1
        for idx in range(1, length):
            if visited[idx] is False and dist[idx] < min_value:
                min_idx = idx
                min_value = dist[idx]

        visited[min_idx] = True
        for j in range(1, length):
            new_length = get_length(x_axis[j], y_axis[j], x_axis[min_idx], y_axis[min_idx])
            if visited[j] is False and dist[j] > new_length:
                dist[j] = new_length

    return round(e * sum(dist))

for c in range(case):
    node_count = int(input())
    x_axis = list(map(int, input().split(" ")))
    y_axis = list(map(int, input().split(" ")))
    e = float(input())
    print("#{0} {1}".format(c+1, tunneling(x_axis, y_axis, e)))