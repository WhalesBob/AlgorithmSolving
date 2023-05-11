def solution(t, p):
    count = 0
    length = len(p)
    for i in range(0,len(t)-length+1):
        compare = int(t[i:i+length])
        if(compare <= int(p)):
            count+=1
    return count
