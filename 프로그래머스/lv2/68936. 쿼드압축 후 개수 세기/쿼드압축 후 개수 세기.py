result = []

def solution(arr):
    get_append(arr, 0, len(arr[0]), 0, len(arr))

    ans = [0,0]
    for i in range(0, len(result)):
        ans[result[i]]+=1
        
    return ans

def zipping(matrix, start_x, end_x, start_y, end_y):
    half_x = int((end_x + start_x) / 2)
    half_y = int((end_y + start_y) / 2)

    get_append(matrix, start_x, half_x, start_y, half_y)
    get_append(matrix, half_x, end_x, start_y, half_y)
    get_append(matrix, start_x, half_x, half_y, end_y)
    get_append(matrix, half_x, end_x, half_y, end_y)

def get_append(matrix, start_x, end_x, start_y, end_y):
    if is_all_same(matrix, start_x, end_x, start_y, end_y):
        result.append(matrix[start_y][start_x])
    else:
        zipping(matrix, start_x, end_x, start_y, end_y)

def is_all_same(matrix, start_x, end_x, start_y, end_y):
    value = matrix[start_y][start_x]
    for y in range(start_y, end_y):
        for x in range(start_x, end_x):
            if value != matrix[y][x]:
                return False
    return True