def solution(n):
    array = list(str(n))
    array.reverse()
    answer = []
    for n in array:
        answer.append(int(n))
        
    return answer