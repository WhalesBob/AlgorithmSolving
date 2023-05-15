from sys import stdout

n = int(input())
result = ['' for i in range(0, n)]

def print_recursive(size, start_y, start_x, is_blank):
    if is_blank:
        for i in range(0, size):
            result[start_y + i] += (' ' * size)
    else:
        if size == 1:
            result[start_y] += '*'
        else:
            for i in range(0, 3):
                for j in range(0, 3):
                    if i == 1 and j == 1:
                        print_recursive(int(size / 3), int(start_y + (size * i / 3)), int(start_x + (size * j / 3)), True)
                    else:
                        print_recursive(int(size / 3), int(start_y + (size * i / 3)), int(start_x + (size * j / 3)), False)

print_recursive(n, 0, 0,False)
for i in range(0, len(result)):
    stdout.write(result[i] + '\n')