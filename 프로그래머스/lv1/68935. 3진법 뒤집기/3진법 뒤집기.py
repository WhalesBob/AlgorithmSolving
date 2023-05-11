def solution(n):
    numStr = convert(n,3)
    reverse = numStr[::-1]
    return int(reverse,3)

def convert(n, q):
    base = ''
    while(n > 0):
        n,mod = divmod(n,q)
        base += str(mod)
    return base[::-1]
    
    
    