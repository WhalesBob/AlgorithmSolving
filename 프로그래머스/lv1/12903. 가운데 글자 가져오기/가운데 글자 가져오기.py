import math

def solution(s):
    m,n = math.floor(len(s)/2), math.ceil(len(s)/2)
    return s[m:n] if n != m else s[m-1:m+1]
    

