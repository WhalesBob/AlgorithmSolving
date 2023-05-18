case = int(input())
direct = [[-1,0],[0,-1],[1,0],[0,1]]

s = set()

def can_go(x, y):
    return 0 <= x < 4 and 0 <= y < 4
def visit(append_string, count, x, y):
    append_string += str(matrix[y][x])
    if count == 6:
        s.add(append_string)
        return

    else:
        for i in range(4):
            next_x = x + direct[i][0]
            next_y = y + direct[i][1]
            if can_go(next_x, next_y):
                visit(append_string, count + 1, next_x, next_y)

for c in range(case):
    matrix = [list(map(int, input().split())) for a in range(4)]

    for y_axis in range(4):
        for x_axis in range(4):
            visit("", 0, x_axis, y_axis)

    print("#{0} {1}".format(c+1, len(s)))
    s = set()