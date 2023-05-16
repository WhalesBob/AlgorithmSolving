def solution(progresses, speeds):
    point = before = 0
    answer = []
    while(point < len(progresses)):
        for i in range(point, len(progresses)):
            progresses[i] += speeds[i]
        
        if progresses[point] >= 100:
            while(point < len(progresses) and progresses[point] >= 100):
                point += 1
            answer.append(point-before)
            before = point    
        
    return answer