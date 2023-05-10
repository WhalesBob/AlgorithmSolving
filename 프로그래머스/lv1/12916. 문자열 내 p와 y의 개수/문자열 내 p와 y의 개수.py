def solution(s):
    p,y = 0,0
    
    for t in s:
        if(t.lower() == 'p'):
            p = p+1
    
        if(t.lower() == 'y'):
            y = y+1

    return (p == y)