def solution(cacheSize, cities):
    queue=[]
    time = 0
    if(cacheSize == 0):
        return 5 * len(cities)
    
    while(len(cities) != 0):
        element = cities.pop(0).lower()
        if(element not in queue):
            time += 5
            if(len(queue) == cacheSize):
                queue.pop(0)
            queue.append(element)
                
        else:
            time += 1
            queue.remove(element)
            queue.append(element)
    return time            