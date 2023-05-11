def solution(name, yearning, photo):
    d = dict()
    for i in range(0, len(name)):
        d[name[i]] = yearning[i]
    
    result = []
    for pArray in photo:
        sum = 0
        for p in pArray :
            if(p in d):
                sum += d[p]
        result.append(sum)
        
    return result 
