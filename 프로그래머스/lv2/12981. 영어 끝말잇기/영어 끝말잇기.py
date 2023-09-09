def isValid(before, after, s):
    return (after[0] == before[-1]) and (after not in s) and len(after) > 1

def solution(n, words):
    s = set()
    
    person = 1
    s.add(words[0])
    for i in range(1,len(words)):
        if isValid(words[i-1], words[i], s):
            s.add(words[i])
        else:
            return [(person % n) + 1, (person // n) + 1]
        person += 1
    
    return [0,0]