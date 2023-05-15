def solution(arr1, arr2):
    len_arr1_x = len(arr1[0])
    len_arr1_y = len(arr1)
    len_arr2_x = len(arr2[0])

    return [ [ sum([arr1[y][b] * arr2[b][x] for b in range(0,len_arr1_x)]) for x in range(0, len_arr2_x) ] for y in range(0, len_arr1_y) ]
    
    
