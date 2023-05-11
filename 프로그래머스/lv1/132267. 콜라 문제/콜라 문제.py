def solution(a, b, n):
    count = 0
    while(n >= a):
        q,r = divmod(n,a)
        count += q*b
        n = q*b+r
    
    return count
