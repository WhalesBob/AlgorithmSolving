def solution(citations):
    citations.sort(reverse=True)
    for i in range(0, len(citations)):
        if citations[i] < i+1:
            return i
    return len(citations)    
        