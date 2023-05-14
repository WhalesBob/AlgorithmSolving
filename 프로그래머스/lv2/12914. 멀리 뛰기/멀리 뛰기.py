def solution(n):
    if n<2:
        return 1
    
    array = [0] * (n+1)
    array[0] = array[1] = 1
    for i in range(2,n+1):
        array[i] = array[i-1] + array[i-2]
    
    return array[n] % 1234567