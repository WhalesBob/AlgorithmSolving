def solution(s):
    array = []
    for i in range(0, len(s)):
        isSame = False
        compare = s[i]
        for j in range(i-1,-1,-1):
            if(s[j] == s[i]):
                array.append(i-j)
                isSame = True
                break
        if(not isSame):
            array.append(-1)
            
    return array 