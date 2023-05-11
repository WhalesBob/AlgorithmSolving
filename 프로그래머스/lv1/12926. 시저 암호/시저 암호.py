def solution(s, n):
    answer = []
    for t in s:
        if(ord(t) <= 90 and ord(t) + n > 90):
            answer.append(chr(ord(t) + n - 26))
        elif(ord(t) <= 122 and ord(t) + n > 122):
            answer.append(chr(ord(t) + n - 26))
        elif(ord(t) == 32):
            answer.append(' ')
        else:
            answer.append(chr(ord(t) + n))
            
    return ''.join(s for s in answer)        
            