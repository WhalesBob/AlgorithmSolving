case = int(input())

direct = [[-1, 0], [0, -1], [1, 0], [0, 1]]

def can_go(matrix, x, y):
    return 0 <= x < len(matrix[0]) and 0 <= y < len(matrix)

def travel(x, y, matrix):

    count = 0
    while True:
        count += 1
        is_traveled = False
        for i in range(4):
            next_x = x + direct[i][0]
            next_y = y + direct[i][1]
            if can_go(matrix, next_x, next_y) and matrix[next_y][next_x] == matrix[y][x] + 1:
                is_traveled = True
                x, y = next_x, next_y
                break

        if not is_traveled:
            break

    return count


for c in range(case):
    n = int(input())
    d = {}
    get_take = {}

    matrix = []
    for y in range(n):
        array = list(map(int, input().split()))
        matrix.append(array)
        for x in range(len(array)):
            d[array[x]] = [y, x]

    for i in range(1, n ** 2 + 1):
        axis_y, axis_x = d[i]
        get_take[i] = travel(axis_x, axis_y, matrix)

    max_take = sorted(get_take.items(), key=lambda x:(x[1],x[0]), reverse=True)
    real_max_take = [max_take[i] for i in range(len(max_take)) if max_take[i][1] == max_take[0][1]]

    print("#{0} {1} {2}".format(c + 1, max_take[len(real_max_take)-1][0], max_take[len(real_max_take)-1][1]))