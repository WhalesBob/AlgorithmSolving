def solution(s):
    s = s.lower()
    answer = []
    
    index = 0
    for i in range(0,len(s)):
        if(s[i] == ' '):
            index = 0
            answer.append(s[i])
        else:
            if(index % 2 == 0):
                answer.append(s[i].upper())
            else:
                answer.append(s[i])
            index+=1
    return ''.join(s for s in answer)         