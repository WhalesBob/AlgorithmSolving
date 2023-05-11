def solution(array, commands):
    answer = []
    for c in commands:
        i,j,k = c
        n = sorted(array[i-1:j])[k-1]
        answer.append(n)
        
    return answer  
        
    