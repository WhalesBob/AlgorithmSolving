def solution(sizes):
    big, small = 0,0
    for arr in sizes:
        b,s = max(arr[0],arr[1]), min(arr[0],arr[1])
        if(big < b):
            big = b
        if(small < s):
            small = s
    
    return big * small