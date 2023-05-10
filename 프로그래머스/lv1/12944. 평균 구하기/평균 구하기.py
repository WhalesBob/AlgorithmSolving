def solution(arr):
    sum = 0
    
    for e in arr:
        sum = sum + e
        
    return sum / len(arr)    