def solution(a, b):
    sum = 0
    a,b = min(a,b), max(a,b)
    for i in range(a,b+1):
        sum = sum + i
        
    return sum    