import math

def solution(n):
    sum = 0
    s = set()
    
    for i in range(1,math.ceil(math.sqrt(n) + 1)):
        if(n % i == 0):
            s.update([i,n/i])
            
    for i in s:
        sum = sum + i
        
    return sum    